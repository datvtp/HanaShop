/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datvtp.controllers;

import datvtp.daos.Tbl_FoodDAO;
import datvtp.daos.Tbl_InvoiceDAO;
import datvtp.daos.Tbl_InvoiceDetailDAO;
import datvtp.dtos.Tbl_FoodDTO;
import datvtp.dtos.Tbl_InvoiceDTO;
import datvtp.models.Cart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author vanth
 */
public class UserPayController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserPayController.class);

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "userViewInvoiceDetail.jsp";
    private static final String INVALID = "UserRecommendFoodController";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("EMAIL");
            String name = (String) session.getAttribute("NAME");
            Cart shoppingCart = (Cart) session.getAttribute("CART");
            int totalPrice = shoppingCart.getTotal();
            Tbl_InvoiceDAO invoiceDAO = new Tbl_InvoiceDAO();
            Tbl_InvoiceDetailDAO invoiceDetailDAO = new Tbl_InvoiceDetailDAO();
            Tbl_FoodDAO foodDAO = new Tbl_FoodDAO();
            boolean valid = true;
            List<Tbl_FoodDTO> listFood_InvalidQuantity = new ArrayList<>();
            List<Tbl_FoodDTO> listFood_InvalidStatus = new ArrayList<>();

            if (!shoppingCart.getShoppingCart().values().isEmpty()) {
                for (Tbl_FoodDTO foodDTO : shoppingCart.getShoppingCart().values()) {
                    Tbl_FoodDTO food = foodDAO.findFoodById(foodDTO.getFoodId());
                    int quantity = food.getQuantity() - foodDTO.getQuantity();
                    if (quantity < 0) {
                        valid = false;
                        listFood_InvalidQuantity.add(food);
                    }
                    if (food.getStatusId() == 2){
                        valid = false;
                        listFood_InvalidStatus.add(food);
                    }
                }
                if (valid) {
                    if (invoiceDAO.insert(totalPrice, email)) {
                        int invoiceId = invoiceDAO.getLastInvoiceId();
                        for (Tbl_FoodDTO foodDTO : shoppingCart.getShoppingCart().values()) {
                            invoiceDetailDAO.insert(foodDTO.getFoodId(), foodDTO.getQuantity(), foodDTO.getPrice() * foodDTO.getQuantity(), invoiceId);
                        }
                        // update quantity food trong database
                        for (Tbl_FoodDTO foodDTO : shoppingCart.getShoppingCart().values()) {
                            Tbl_FoodDTO food = foodDAO.findFoodById(foodDTO.getFoodId());
                            int quantity = food.getQuantity() - foodDTO.getQuantity();
                            foodDAO.updateFoodQuantity(food.getFoodId(), quantity);
                        }

                        Tbl_InvoiceDTO invoiceDTO = invoiceDAO.findInvoiceById(invoiceId);
                        request.setAttribute("INVOICE_DTO", invoiceDTO);
                        Cart newCart = new Cart(name);
                        session.setAttribute("CART", newCart);
                        url = SUCCESS;
                    } else {
                        request.setAttribute("ERROR", "Create invoice failed.");
                    }
                } else {
                    request.setAttribute("INVALID_QUANTITY", listFood_InvalidQuantity);
                    request.setAttribute("INVALID_STATUS", listFood_InvalidStatus);
                    url = INVALID;
                }
            } else {
                request.setAttribute("ERROR", "Cart is empty.");
            }

        } catch (Exception e) {
            logger.error("ERROR at UserPayController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

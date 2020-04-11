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
import datvtp.dtos.Tbl_InvoiceDetailDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
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
public class AdminSearchHistoryController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AdminSearchHistoryController.class);

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

        try {
            HttpSession session = request.getSession();
            String foodName = request.getParameter("txtFoodName");
            String dateFrom = request.getParameter("txtFrom");
            String dateTo = request.getParameter("txtTo");

            Tbl_FoodDAO foodDAO = new Tbl_FoodDAO();
            Tbl_InvoiceDAO invoiceDAO = new Tbl_InvoiceDAO();
            Tbl_InvoiceDetailDAO invoiceDetailDAO = new Tbl_InvoiceDetailDAO();
            List<Tbl_InvoiceDetailDTO> list = invoiceDetailDAO.getListInvoiceDetailForAdmin(foodName, dateFrom, dateTo);
            for (int i = 0; i < list.size(); i++) {
                Tbl_FoodDTO food = foodDAO.findFoodById(list.get(i).getFoodId());
                list.get(i).setFoodName(food.getName());
                list.get(i).setPrice(food.getPrice());
                Tbl_InvoiceDTO invoice = invoiceDAO.findInvoiceById(list.get(i).getInvoiceId());
                list.get(i).setEmail(invoice.getEmail());
            }

            request.setAttribute("LIST", list);
        } catch (SQLException e) {
            logger.error("ERROR SQL at AdminSearchHistoryController: " + e.getMessage());
        } catch (NamingException e) {
            logger.error("ERROR Naming at AdminSearchHistoryController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("adminViewHistory.jsp").forward(request, response);
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

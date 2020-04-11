/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datvtp.controllers;

import datvtp.daos.Tbl_FoodDAO;
import datvtp.dtos.Tbl_FoodDTO;
import datvtp.models.Cart;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
public class UserRecommendFoodController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserRecommendFoodController.class);

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
            String email = (String) session.getAttribute("EMAIL");
            Cart shoppingCart = (Cart) session.getAttribute("CART");
            Tbl_FoodDAO foodDAO = new Tbl_FoodDAO();

            HashMap<Integer, Tbl_FoodDTO> listFoodBuyTogether = new HashMap<>();
            List<Tbl_FoodDTO> tempList = new ArrayList<>();

            if (!shoppingCart.getShoppingCart().values().isEmpty()) {
                for (Tbl_FoodDTO foodDTO : shoppingCart.getShoppingCart().values()) {
                    tempList.addAll(foodDAO.getFoodsBuyTogether(foodDTO.getFoodId()));
                }

                for (int i = 0; i < tempList.size(); i++) {
                    if (!listFoodBuyTogether.containsKey(tempList.get(i).getFoodId())) {
                        if (!shoppingCart.getShoppingCart().containsKey(tempList.get(i).getFoodId())) {
                            listFoodBuyTogether.put(tempList.get(i).getFoodId(), tempList.get(i));
                        }
                    }
                }
            }

            List<Tbl_FoodDTO> listFoodUserLike = foodDAO.getFoodsUserLike(email);

            request.setAttribute("LIST_FOOD_BUY_TOGETHER", listFoodBuyTogether);
            request.setAttribute("LIST_FOOD_USER_LIKE", listFoodUserLike);
        } catch (SQLException e) {
            logger.error("ERROR SQL at UserRecommendFoodController: " + e.getMessage());
        } catch (NamingException e) {
            logger.error("ERROR Naming at UserRecommendFoodController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("userViewCart.jsp").forward(request, response);
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

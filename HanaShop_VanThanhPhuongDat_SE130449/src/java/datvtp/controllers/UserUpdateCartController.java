/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datvtp.controllers;

import datvtp.models.Cart;
import java.io.IOException;
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
public class UserUpdateCartController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserUpdateCartController.class);

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
            Cart shoppingCart = (Cart) session.getAttribute("CART");
            String action = request.getParameter("btnAction");
            if (action.equals("Delete")) {
                // lấy mảng food id
                String[] temp = request.getParameterValues("cbFoodId");
                int[] foodId = new int[temp.length];
                for (int i = 0; i < temp.length; i++) {
                    foodId[i] = Integer.parseInt(temp[i]);
                }
                
                // xóa
                for (int i : foodId) {
                    shoppingCart.removeCart(i);
                }
                session.setAttribute("CART", shoppingCart);
            } else if (action.equals("Update")) {
                // lấy mảng food id
                String[] tempFoodId = request.getParameterValues("txtFoodId");
                int[] foodId = new int[tempFoodId.length];
                for (int i = 0; i < tempFoodId.length; i++) {
                    foodId[i] = Integer.parseInt(tempFoodId[i]);
                }
                // lấy mảng quantity
                String[] tempQuantity = request.getParameterValues("txtQuantity");
                int[] quantity = new int[tempQuantity.length];
                for (int i = 0; i < tempQuantity.length; i++) {
                    quantity[i] = Integer.parseInt(tempQuantity[i]);
                }
                
                
                // update
                for (int i = 0; i < foodId.length; i++) {
                    shoppingCart.updateCart(foodId[i], quantity[i]);
                }
                session.setAttribute("CART", shoppingCart);
            }
        } catch (Exception e) {
            logger.error("ERROR at UserUpdateCartController: " + e.getMessage());
        } finally {
            response.sendRedirect("userRecommendFood");
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

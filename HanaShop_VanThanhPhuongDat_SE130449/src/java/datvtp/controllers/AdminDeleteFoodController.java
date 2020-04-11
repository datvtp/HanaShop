/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datvtp.controllers;

import datvtp.daos.Tbl_FoodDAO;
import java.io.IOException;
import java.sql.SQLException;
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
public class AdminDeleteFoodController extends HttpServlet {
    
    private static final Logger logger = Logger.getLogger(AdminDeleteFoodController.class);
    private static final String ERROR = "error.jsp";
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
            int foodId = Integer.parseInt(request.getParameter("txtFoodId"));
            
            String foodName = request.getParameter("txtFoodName");
            int categoryId = Integer.parseInt(request.getParameter("txtCategory"));
            int minPrice = Integer.parseInt(request.getParameter("txtPriceMin"));
            int maxPrice = Integer.parseInt(request.getParameter("txtPriceMax"));
            int pageNumber = Integer.parseInt(request.getParameter("txtPageNumber"));
            
            
            Tbl_FoodDAO foodDAO = new Tbl_FoodDAO();
            if (foodDAO.deleteFood(foodId)) {
                HttpSession session = request.getSession();
                foodDAO.record(session.getAttribute("EMAIL").toString(), foodId);
                
                url = "AdminSearchController?txtFoodName=" + foodName
                        + "&txtCategory=" + categoryId
                        + "&txtPriceMin=" + minPrice
                        + "&txtPriceMax=" + maxPrice
                        + "&txtPageNumber=" + pageNumber;
            } else {
                request.setAttribute("ERROR", "Delete food failed.");
            }
        } catch (NumberFormatException e) {
            logger.error("ERROR NumberFormat at AdminDeleteFoodController: " + e.getMessage());
        } catch (SQLException e) {
            logger.error("ERROR SQL at AdminDeleteFoodController: " + e.getMessage());
        } catch (NamingException e) {
            logger.error("ERROR Naming at AdminDeleteFoodController: " + e.getMessage());
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

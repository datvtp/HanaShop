/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datvtp.controllers;

import datvtp.daos.Tbl_FoodDAO;
import datvtp.dtos.Tbl_FoodDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author vanth
 */
public class GuestSearchController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(GuestSearchController.class);

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
            String foodName = request.getParameter("txtFoodName");
            int categoryId = Integer.parseInt(request.getParameter("txtCategory"));
            int minPrice = Integer.parseInt(request.getParameter("txtPriceMin"));
            int maxPrice = Integer.parseInt(request.getParameter("txtPriceMax"));
            int pageNumber = Integer.parseInt(request.getParameter("txtPageNumber"));

            Tbl_FoodDAO foodDAO = new Tbl_FoodDAO();
            int totalRecord = foodDAO.getNumberOfFood(foodName, categoryId, minPrice, maxPrice);
            int numberOfPage = totalRecord / 20;//
            if (totalRecord > numberOfPage * 20) {//
                numberOfPage += 1;
            }
            int offsetRecord = (pageNumber - 1) * 20;//
            List<Tbl_FoodDTO> listFood = foodDAO.getListFoodSearch(foodName, categoryId, minPrice, maxPrice, offsetRecord, 20);//
            request.setAttribute("LISTFOOD", listFood);
            request.setAttribute("NUMBEROFPAGE", numberOfPage);

        } catch (NumberFormatException e) {
            logger.error("ERROR NumberFormat at GuestSearchController: " + e.getMessage());
        } catch (SQLException e) {
            logger.error("ERROR SQL at GuestSearchController: " + e.getMessage());
        } catch (NamingException e) {
            logger.error("ERROR Naming at GuestSearchController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("home.jsp").forward(request, response);
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

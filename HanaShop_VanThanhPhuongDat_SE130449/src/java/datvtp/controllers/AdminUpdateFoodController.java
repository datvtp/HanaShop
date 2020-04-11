/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datvtp.controllers;

import com.oreilly.servlet.MultipartRequest;
import datvtp.daos.Tbl_FoodDAO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author vanth
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AdminUpdateFoodController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AdminUpdateFoodController.class);

    private static final long serialVersionUID = 1L;

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "adminUpdateFood.jsp";

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
            String appPath = request.getServletContext().getRealPath("");
            appPath = appPath.replace('\\', '/');
            int indexOfBuild = appPath.indexOf("build");
            appPath = appPath.substring(0, indexOfBuild) + appPath.substring(indexOfBuild + 6);

            String fullSavePath = null;
            if (appPath.endsWith("/")) {
                fullSavePath = appPath + "images";
            } else {
                fullSavePath = appPath + "/" + "images";
            }

            MultipartRequest multiparRequest = new MultipartRequest(request, fullSavePath);

            int foodId = Integer.parseInt(multiparRequest.getParameter("txtFoodId"));
            String foodName = multiparRequest.getParameter("txtFoodName");
            String fileName = null;
            String description = multiparRequest.getParameter("txtDescription");
            int price = Integer.parseInt(multiparRequest.getParameter("txtPrice"));
            int quantity = Integer.parseInt(multiparRequest.getParameter("txtQuantity"));
            int categoryId = Integer.parseInt(multiparRequest.getParameter("txtCategory"));
            int statusId = Integer.parseInt(multiparRequest.getParameter("txtStatus"));

            Enumeration filesEnumeration = multiparRequest.getFileNames();

            while (filesEnumeration.hasMoreElements()) {
                String upload = (String) filesEnumeration.nextElement();
                if (multiparRequest.getOriginalFileName(upload) != null) {
                    File imageFile = new File(multiparRequest.getOriginalFileName(upload));
                    fileName = imageFile.getName();
                }
            }
            Tbl_FoodDAO foodDAO = new Tbl_FoodDAO();
            if (fileName != null) {
                if (foodDAO.updateFood(foodId, foodName, fileName, description, price, quantity, categoryId, statusId)) {
                    HttpSession session = request.getSession();
                    foodDAO.record(session.getAttribute("EMAIL").toString(), foodId);
                    request.setAttribute("FOOD_DTO", foodDAO.findFoodById(foodId));
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Update food failed.");
                }
            } else {
                if (foodDAO.updateFoodNoImage(foodId, foodName, description, price, quantity, categoryId, statusId)) {
                    HttpSession session = request.getSession();
                    foodDAO.record(session.getAttribute("EMAIL").toString(), foodId);
                    request.setAttribute("FOOD_DTO", foodDAO.findFoodById(foodId));
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Update food failed.");
                }
            }

        } catch (IOException e) {
            logger.error("ERROR IO at AdminUpdateFoodController: " + e.getMessage());
        } catch (NumberFormatException e) {
            logger.error("ERROR NumberFormat at AdminUpdateFoodController: " + e.getMessage());
        } catch (SQLException e) {
            logger.error("ERROR SQL at AdminUpdateFoodController: " + e.getMessage());
        } catch (NamingException e) {
            logger.error("ERROR Naming at AdminUpdateFoodController: " + e.getMessage());
        } finally {
//            if (url.equals(SUCCESS)) {
//                response.sendRedirect(url);
//            } else {
//                request.getRequestDispatcher(url).forward(request, response);
//            }
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

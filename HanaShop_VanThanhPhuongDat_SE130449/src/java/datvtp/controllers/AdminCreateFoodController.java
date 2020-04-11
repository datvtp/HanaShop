/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datvtp.controllers;

import com.oreilly.servlet.MultipartRequest;
import datvtp.daos.Tbl_FoodDAO;
import datvtp.utils.DateTimeDataType;
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
import org.apache.log4j.Logger;

/**
 *
 * @author vanth
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AdminCreateFoodController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AdminCreateFoodController.class);
    private static final long serialVersionUID = 1L;

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "adminCreateFood.jsp";

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

            String foodName = multiparRequest.getParameter("txtFoodName");
            String fileName = null;
            String description = multiparRequest.getParameter("txtDescription");
            int price = Integer.parseInt(multiparRequest.getParameter("txtPrice"));
            int quantity = Integer.parseInt(multiparRequest.getParameter("txtQuantity"));
            int categoryId = Integer.parseInt(multiparRequest.getParameter("txtCategory"));
            String createTime = DateTimeDataType.getTimeNow();

            Enumeration filesEnumeration = multiparRequest.getFileNames();

            while (filesEnumeration.hasMoreElements()) {
                String upload = (String) filesEnumeration.nextElement();
                File imageFile = new File(multiparRequest.getOriginalFileName(upload));
                fileName = imageFile.getName();
            }
            Tbl_FoodDAO foodDAO = new Tbl_FoodDAO();
            if (foodDAO.insertFood(foodName, fileName, description, price, quantity, createTime, categoryId)) {
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "Create food failed.");
            }

        } catch (NumberFormatException e) {
            logger.error("ERROR NumberFormat at AdminCreateFoodController: " + e.getMessage());
        } catch (SQLException e) {
            logger.error("ERROR SQL at AdminCreateFoodController: " + e.getMessage());
        } catch (NamingException e) {
            logger.error("ERROR Naming at AdminCreateFoodController: " + e.getMessage());
        } finally {
            if (url.equals(SUCCESS)) {
                response.sendRedirect(url);
            } else {
                request.getRequestDispatcher(url).forward(request, response);
            }
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datvtp.controllers;

import datvtp.daos.Tbl_UserDAO;
import datvtp.dtos.Tbl_UserErrorObject;
import datvtp.models.Cart;
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
public class LoginController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    private static final String ERROR = "error.jsp";
    private static final String INVALID = "login.jsp";
    private static final String ADMIN = "admin.jsp";
    private static final String USER = "user.jsp";

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

            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");

            Tbl_UserErrorObject userErrorObj = new Tbl_UserErrorObject();
            boolean valid = true;

            // check userId
            if (email.isEmpty()) {
                userErrorObj.setEmailError("Email can't be blank.");
                valid = false;
            }
            // check password
            if (password.isEmpty()) {
                userErrorObj.setPasswordError("Password can't be blank.");
                valid = false;
            }

            if (valid) {
                Tbl_UserDAO userDAO = new Tbl_UserDAO();
                int role = userDAO.checkLogin(email, password);

                if (role == 0) {
                    url = INVALID;
                    userErrorObj.setPasswordError("Invalid Email or Password.");
                    request.setAttribute("INVALID", userErrorObj);
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("EMAIL", email);
                    session.setAttribute("NAME", userDAO.getName());
                    session.setAttribute("ROLE", role);

                    if (role == 1) {
                        url = ADMIN;
                    }
                    if (role == 2) {
                        if (session.getAttribute("CART") == null) {
                            Cart shoppingCart = new Cart(userDAO.getName());
                            session.setAttribute("CART", shoppingCart);
                        }
                        url = USER;
                    }
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", userErrorObj);
            }
        } catch (SQLException e) {
            logger.error("ERROR SQL at LoginController: " + e.getMessage());
        } catch (NamingException e) {
            logger.error("ERROR Naming at LoginController: " + e.getMessage());
        } finally {
            if (url.equals(ERROR) || url.equals(INVALID)) {
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                response.sendRedirect(url);
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

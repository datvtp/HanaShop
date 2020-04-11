/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datvtp.controllers;

import datvtp.daos.Tbl_UserDAO;
import datvtp.models.Cart;
import datvtp.utils.GooglePojo;
import datvtp.utils.GoogleUtils;
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
public class LoginGoogleController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LoginGoogleController.class);
    private static final String ERROR = "error.jsp";
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
            String code = request.getParameter("code");

            if (code == null || code.isEmpty()) {
                request.setAttribute("ERROR", "Login with Google failed.");
            } else {
                String accessToken = GoogleUtils.getToken(code);
                GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);

                String email = googlePojo.getEmail();
                String name = googlePojo.getName();

                Tbl_UserDAO userDAO = new Tbl_UserDAO();
                if (userDAO.checkEmailExist(email) == null) {
                    userDAO.insert(email, name, "");
                }

                HttpSession session = request.getSession();
                session.setAttribute("EMAIL", email);
                session.setAttribute("NAME", name);
                session.setAttribute("ROLE", 2);
                if (session.getAttribute("CART") == null) {
                    Cart shoppingCart = new Cart(name);
                    session.setAttribute("CART", shoppingCart);
                }

                url = USER;
            }

        } catch (IOException e) {
            logger.error("ERROR IO at LoginGoogleController: " + e.getMessage());
        } catch (NamingException e) {
            logger.error("ERROR Naming at LoginGoogleController: " + e.getMessage());
        } catch (SQLException e) {
            logger.error("ERROR SQL at LoginGoogleController: " + e.getMessage());
        } finally {
            if (url.equals(ERROR)) {
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

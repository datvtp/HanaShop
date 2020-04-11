/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datvtp.filter;

import datvtp.daos.Tbl_CategoryDAO;
import datvtp.daos.Tbl_StatusDAO;
import datvtp.utils.DateTimeDataType;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author vanth
 */
public class FilterDispatcher implements Filter {

    private static final boolean debug = true;
    private static final Logger logger = Logger.getLogger(FilterDispatcher.class);

    private static final String HOME = "home.jsp";
    private static final String ADMIN = "admin.jsp";
    private static final String USER = "user.jsp";
    
    private static final String LOGIN = "login.jsp";

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public FilterDispatcher() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterDispatcher:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterDispatcher:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        String url = HOME;

        try {
            HttpSession session = req.getSession();
            int lastIndex = uri.lastIndexOf("/");
            String resource = uri.substring(lastIndex + 1);

            if (resource.length() > 0) {
                url = resource.substring(0, 1).toUpperCase()
                        + resource.substring(1)
                        + "Controller";

                if (resource.lastIndexOf(".jsp") > 0 || resource.lastIndexOf(".jpg") > 0 || resource.lastIndexOf(".png") > 0 || resource.lastIndexOf(".jpeg") > 0) {
                    url = resource;
                }
            }

            // check admin
            if (url.contains("admin") || url.contains("Admin")) {
                if (session.getAttribute("ROLE") != null) {
                    int role = Integer.parseInt(session.getAttribute("ROLE").toString());
                    if (role != 1) {
                        url = LOGIN;
                    }
                } else {
                    url = LOGIN;
                }
            }

            // check user
            if (url.contains("user") || url.contains("User")) {
                if (session.getAttribute("ROLE") != null) {
                    int role = Integer.parseInt(session.getAttribute("ROLE").toString());
                    if (role != 2) {
                        url = LOGIN;
                    }
                } else {
                    url = LOGIN;
                }
            }

            if (url != null) {
                Tbl_CategoryDAO categoryDAO = new Tbl_CategoryDAO();
                session.setAttribute("CATEGORY", categoryDAO.getListCategory());
                Tbl_StatusDAO statusDAO = new Tbl_StatusDAO();
                session.setAttribute("STATUS", statusDAO.getListStatus());

                if (url.equals(HOME)) {
                    url = "GuestSearchController?txtFoodName=&txtPageNumber=1&txtCategory=0&txtPriceMin=0&txtPriceMax=100";
                }
                if (url.equals(ADMIN)) {
                    url = "AdminSearchController?txtFoodName=&txtPageNumber=1&txtCategory=0&txtPriceMin=0&txtPriceMax=100";
                }
                if (url.equals(USER)) {
                    url = "UserSearchController?txtFoodName=&txtPageNumber=1&txtCategory=0&txtPriceMin=0&txtPriceMax=100";
                }
                if (url.equals("adminViewHistory.jsp")) {
                    url = "AdminSearchHistoryController?txtFoodName=&txtFrom=" + DateTimeDataType.getDatePast()
                            + "&txtTo=" + DateTimeDataType.getDateFuture();
                }
                if (url.equals("userViewHistory.jsp")) {
                    url = "UserSearchHistoryController?txtFoodName=&txtFrom=" + DateTimeDataType.getDatePast()
                            + "&txtTo=" + DateTimeDataType.getDateFuture();
                }
                if (url.equals("userViewCart.jsp")) {
                    url = "UserRecommendFoodController";
                }

                RequestDispatcher rd = req.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
        } catch (IOException e) {
            logger.error("ERROR IO at FilterDispatcher: " + e.getMessage());
        } catch (ServletException e) {
            logger.error("ERROR Servlet at FilterDispatcher: " + e.getMessage());
        } catch (SQLException e) {
            logger.error("ERROR SQL at FilterDispatcher: " + e.getMessage());
        } catch (NamingException ex) {
            java.util.logging.Logger.getLogger(FilterDispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("FilterDispatcher:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FilterDispatcher()");
        }
        StringBuffer sb = new StringBuffer("FilterDispatcher(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}

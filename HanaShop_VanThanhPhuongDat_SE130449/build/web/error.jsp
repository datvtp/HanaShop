<%-- 
    Document   : error
    Created on : Feb 12, 2020, 3:02:17 PM
    Author     : vanth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HanaShop</title>
    </head>
    <body>
        <h1>Error Page</h1>
        <h2>
            <font color="red">
            ${requestScope.ERROR}
            <font/> <br/>
            <a href="home.jsp">Back to Home</a><br/><br/>
        </h2>
    </body>
</html>

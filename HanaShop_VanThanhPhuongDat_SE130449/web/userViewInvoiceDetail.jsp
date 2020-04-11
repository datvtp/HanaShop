<%-- 
    Document   : userViewInvoiceDetail
    Created on : Feb 28, 2020, 6:43:10 PM
    Author     : vanth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HanaShop</title>
    </head>
    <body>
        <h1>Welcome, ${sessionScope.NAME}</h1>
        <a href="user.jsp">Home</a><br/><br/>

    <center>
        <h1>View Invoice</h1>
        <div style="padding: 20px; margin: 30px; text-align: left; width: 300px; min-height: 200px; background-color: #F5F5F5; border: 1px solid #CDCDCD;">
            <h4>Invoice ID: ${requestScope.INVOICE_DTO.invoiceId}</h4>
            <h4>Create Time: ${requestScope.INVOICE_DTO.createTime}</h4>
            <h4>Total Price: ${requestScope.INVOICE_DTO.totalPrice}</h4>
            <h4>Email: ${requestScope.INVOICE_DTO.email}</h4>
        </div>
    </center>
</body>
</html>

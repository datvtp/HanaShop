<%-- 
    Document   : userViewHistory
    Created on : Feb 29, 2020, 9:10:26 PM
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
        <form action="userSearchHistory" method="POST">
            Food Name: <input type="text" name="txtFoodName" value="${param.txtFoodName}"/> <br/>
            From: <input type="date" name="txtFrom" value="${param.txtFrom}" required="" style="width: 150px;"/> <br/>
            To: <input type="date" name="txtTo" value="${param.txtTo}" required="" style="width: 150px;"/> <br/>
            <input type="submit" name="btnAction" value="Search"/>
        </form>
    </center>

    <center>
        <h1>User History</h1>
        <div style="padding: 20px; margin: 30px; text-align: left; width: 300px; min-height: 200px; background-color: #F5F5F5; border: 1px solid #CDCDCD;">
            <c:if test="${empty requestScope.LIST}">
                <h3>Empty.</h3>
            </c:if>
            <c:if test="${not empty requestScope.LIST}">

                <table border="1">
                    <thead>
                        <tr>
                            <th>Food Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total Price</th>
                            <th>Invoice ID</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.LIST}" var="dto">
                            <tr>
                                <td>${dto.foodName}</td>
                                <td>${dto.price}</td>
                                <td>${dto.quantity}</td>
                                <td>${dto.totalPrice}</td>
                                <td>${dto.invoiceId}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </center>
</body>
</html>

<%-- 
    Document   : userViewCart
    Created on : Feb 27, 2020, 6:23:09 PM
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
        <h1>Shopping Cart</h1>
        <div style="padding: 20px; margin: 30px; text-align: left; width: 500px; min-height: 200px; background-color: #F5F5F5; border: 1px solid #CDCDCD;">
            <c:if test="${empty sessionScope.CART.shoppingCart}">
                <h3>Empty Cart.</h3>
            </c:if>
            <c:if test="${not empty sessionScope.CART.shoppingCart}">
                <form action="userUpdateCart" method="POST">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>Food Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.CART.shoppingCart.values()}" var="food_dto">
                                <tr>
                                    <td>${food_dto.name}</td>
                                    <td>${food_dto.price}</td>
                                    <td>
                                        <input type="hidden" name="txtFoodId" value="${food_dto.foodId}"/>
                                        <input type="number" name="txtQuantity" value="${food_dto.quantity}" min="1" required=""/>
                                    </td>
                                    <td>${food_dto.price * food_dto.quantity}</td>
                                    <td>
                                        <input type="checkbox" name="cbFoodId" value="${food_dto.foodId}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table> <br/>
                    <input type="submit" name="btnAction" value="Delete" onclick="return confirm('Are you sure you want to delete?')"/>
                    <input type="submit" name="btnAction" value="Update"/>
                </form><br/>
                <h3>Total amount of money of this cart: ${sessionScope.CART.total} $</h3> <br/><br/>
                <c:if test="${not empty requestScope.INVALID_QUANTITY}">

                    You only buy: 
                    <c:forEach items="${requestScope.INVALID_QUANTITY}" var="food_dto">
                        [${food_dto.name} : ${food_dto.quantity}];
                    </c:forEach>
                    <br/>
                </c:if>
                <c:if test="${not empty requestScope.INVALID_STATUS}">


                    <c:forEach items="${requestScope.INVALID_STATUS}" var="food_dto">
                        [${food_dto.name}]
                    </c:forEach>
                    are not available.
                    <br/>
                </c:if>
                <form action="userPay" method="POST">
                    <input type="submit" name="btnAction" value="Pay"/>
                </form>
            </c:if>
        </div>

        <c:if test="${not empty requestScope.LIST_FOOD_BUY_TOGETHER}">
            <div style="padding: 10px; margin: 30px; text-align: left; width: 300px; min-height: 200px; background-color: #F5F5F5; border: 1px solid #CDCDCD;">
                <h5>Some foods are "BOUGHT TOGETHER"</h5>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Image</th>
                            <th>Name</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.LIST_FOOD_BUY_TOGETHER.values()}" var="dto">
                            <tr>
                                <td>
                                    <img src="images/${dto.image}" alt="" width="50" height="50"/>
                                </td>
                                <td>${dto.name}</td>
                                <td>${dto.price}$</td>
                                <td>
                                    <form action="userAddToCart" method="POST">
                                        <input type="hidden" name="txtFoodId" value="${dto.foodId}"/>
                                        <input type="submit" name="btnAction" value="Add"/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>

        <c:if test="${not empty requestScope.LIST_FOOD_USER_LIKE}">
            <div style="padding: 10px; margin: 30px; text-align: left; width: 300px; min-height: 200px; background-color: #F5F5F5; border: 1px solid #CDCDCD;">
                <h5>Some foods we think you "LIKE"</h5>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Image</th>
                            <th>Name</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.LIST_FOOD_USER_LIKE}" var="dto">
                            <tr>
                                <td>
                                    <img src="images/${dto.image}" alt="" width="50" height="50"/>
                                </td>
                                <td>${dto.name}</td>
                                <td>${dto.price}$</td>
                                <td>
                                    <form action="userAddToCart" method="POST">
                                        <input type="hidden" name="txtFoodId" value="${dto.foodId}"/>
                                        <input type="submit" name="btnAction" value="Add"/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </center>
</body>
</html>

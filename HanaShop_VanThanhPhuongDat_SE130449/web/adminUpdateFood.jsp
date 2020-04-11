<%-- 
    Document   : adminUpdateFood
    Created on : Feb 26, 2020, 9:27:05 PM
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
        <a href="admin.jsp">Home</a> <br/>

    <center>

        <h1>Update food</h1>
        <div style="padding: 20px; margin: 30px; text-align: left; width: 500px; min-height: 200px; background-color: #F5F5F5; border: 1px solid #CDCDCD;">
            <form action="adminUpdateFood" method="POST" enctype="multipart/form-data">
                <img src="images/${FOOD_DTO.image}" alt="" width="200" height="200"/> <br/>
                Name: <input type="text" name="txtFoodName" value="${FOOD_DTO.name}" required="" maxlength="50"/>
                <br/><br/>
                Image: <input type="file" name="txtImage" value="" accept="image/*"/> <br/><br/>
                Description: <input type="text" name="txtDescription" value="${FOOD_DTO.description}" required="" maxlength="200"/>
                <br/><br/>
                Price: <input type="number" name="txtPrice" value="${FOOD_DTO.price}" required="" min="1"/>
                <br/><br/>
                Quantity: <input type="number" name="txtQuantity" value="${FOOD_DTO.quantity}" required="" min="50"/>
                <br/><br/>
                Category: 
                <select name="txtCategory">
                    <c:forEach items="${sessionScope.CATEGORY}" var="category_DTO">
                        <c:if test="${(FOOD_DTO.categoryId) == (category_DTO.categoryId)}">
                            <option value="${category_DTO.categoryId}" selected="">${category_DTO.categoryName}</option>
                        </c:if>
                        <c:if test="${(FOOD_DTO.categoryId) != (category_DTO.categoryId)}">
                            <option value="${category_DTO.categoryId}">${category_DTO.categoryName}</option>
                        </c:if>
                    </c:forEach>
                </select> <br/><br/>
                Status: 
                <select name="txtStatus">
                    <c:forEach items="${sessionScope.STATUS}" var="status_DTO">
                        <c:if test="${(FOOD_DTO.statusId) == (status_DTO.statusId)}">
                            <option value="${status_DTO.statusId}" selected="">${status_DTO.statusName}</option>
                        </c:if>
                        <c:if test="${(FOOD_DTO.statusId) != (status_DTO.statusId)}">
                            <option value="${status_DTO.statusId}">${status_DTO.statusName}</option>
                        </c:if>
                    </c:forEach>
                </select> <br/><br/><br/>
                <input type="hidden" name="txtFoodId" value="${FOOD_DTO.foodId}"/>
                <input type="submit" name="btnAction" value="Update"/>
            </form>
        </div>
    </center>
</body>
</html>

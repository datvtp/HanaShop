<%-- 
    Document   : adminCreateFood.jsp
    Created on : Feb 24, 2020, 9:51:51 PM
    Author     : vanth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hanashop</title>
    </head>
    <body>
        <h1>Welcome, ${sessionScope.NAME}</h1>
        <a href="admin.jsp">Home</a> <br/>

    <center>
        
        <h1>Create new food</h1>
        <div style="padding: 20px; margin: 30px; text-align: left; width: 500px; min-height: 200px; background-color: #F5F5F5; border: 1px solid #CDCDCD;">
            <form action="adminCreateFood" method="POST" enctype="multipart/form-data">
                Name: <input type="text" name="txtFoodName" value="" required="" maxlength="50"/>
                <br/><br/>
                Image: <input type="file" name="txtImage" value="" required="" accept="image/*"/> <br/><br/>
                Description: <input type="text" name="txtDescription" value="" required="" maxlength="200"/>
                <br/><br/>
                Price: <input type="number" name="txtPrice" value="" required="" min="1"/>
                <br/><br/>
                Quantity: <input type="number" name="txtQuantity" value="" required="" min="50"/>
                <br/><br/>
                Category: 
                <select name="txtCategory">
                    <c:forEach items="${sessionScope.CATEGORY}" var="category_DTO">
                        <option value="${category_DTO.categoryId}">${category_DTO.categoryName}</option>
                    </c:forEach>
                </select> <br/><br/><br/>
                <input type="submit" name="btnAction" value="Create"/>
            </form>
        </div>
    </center>
</body>
</html>

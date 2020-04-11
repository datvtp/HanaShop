<%-- 
    Document   : admin
    Created on : Feb 12, 2020, 2:37:44 PM
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
        <a href="logout">Logout</a> <br/>
        <a href="adminCreateFood.jsp">Create Food</a> <br/>
        <a href="adminViewHistory.jsp">History User</a> <br/>
        
        <c:if test="${requestScope.NUMBEROFPAGE > 1}">
            <c:forEach begin="1" end="${requestScope.NUMBEROFPAGE}" var="i">
                <div style="float: left; padding-left: 20px;s">
                    <c:if test="${(param.txtPageNumber) == i}">
                        <h1><a href="adminSearch?txtFoodName=${param.txtFoodName}&txtPageNumber=${i}&txtCategory=${param.txtCategory}&txtPriceMin=${param.txtPriceMin}&txtPriceMax=${param.txtPriceMax}">${i}</a></h1>
                        </c:if>
                        <c:if test="${(param.txtPageNumber) != i}">
                        <h3><a href="adminSearch?txtFoodName=${param.txtFoodName}&txtPageNumber=${i}&txtCategory=${param.txtCategory}&txtPriceMin=${param.txtPriceMin}&txtPriceMax=${param.txtPriceMax}">${i}</a></h3>
                        </c:if>
                </div>
            </c:forEach>
        </c:if>
        <br/><br/><br/><br/><br/><br/>

    <center>
        <form action="adminSearch" method="POST">
            Name: <input type="text" name="txtFoodName" value="${param.txtFoodName}"/>
            <select name="txtCategory">
                <option value="0">All</option>
                <c:forEach items="${sessionScope.CATEGORY}" var="category_DTO">
                    <option value="${category_DTO.categoryId}">${category_DTO.categoryName}</option>
                </c:forEach>
            </select> <br/>
            Price Min: <input type="number" name="txtPriceMin" value="${param.txtPriceMin}" required="" min="0" style="width: 80px;"/>
            Price Max: <input type="number" name="txtPriceMax" value="${param.txtPriceMax}" required="" min="0" style="width: 80px;"/> <br/>
            <input type="hidden" name="txtPageNumber" value="1"/>
            <input type="submit" name="btnAction" value="Search"/>
        </form>
    </center>

    <center>
        <c:if test="${not empty requestScope.LISTFOOD}" var="check">
            <c:forEach items="${requestScope.LISTFOOD}" var="food_DTO">
                <div style="float: left;padding: 20px; margin: 30px; text-align: left; width: 200px; min-height: 200px; background-color: #F5F5F5; border: 1px solid #CDCDCD;">
                    <img src="images/${food_DTO.image}" alt="" width="200" height="200"/>
                    <h6>Name: ${food_DTO.name}</h6>
                    <h6>Description: ${food_DTO.description}</h6>
                    <h6>Price: ${food_DTO.price}$</h6>
                    <h6>Quantity: ${food_DTO.quantity}</h6>
                    <h6>Create Time: ${food_DTO.createTime}</h6>
                    <h6>
                        Category: <select>
                            <c:forEach items="${sessionScope.CATEGORY}" var="category_DTO">
                                <c:if test="${(food_DTO.categoryId) == (category_DTO.categoryId)}">
                                    <option value="${category_DTO.categoryId}" selected="">${category_DTO.categoryName}</option>
                                </c:if>
                                <c:if test="${(food_DTO.categoryId) != (category_DTO.categoryId)}">
                                    <option value="${category_DTO.categoryId}">${category_DTO.categoryName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </h6>
                    <h6>
                        Status: <select>
                            <c:forEach items="${sessionScope.STATUS}" var="status_DTO">
                                <c:if test="${(food_DTO.statusId) == (status_DTO.statusId)}">
                                    <option value="${status_DTO.statusId}" selected="">${status_DTO.statusName}</option>
                                </c:if>
                                <c:if test="${(food_DTO.statusId) != (status_DTO.statusId)}">
                                    <option value="${status_DTO.statusId}">${status_DTO.statusName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </h6>

                    <form action="adminDeleteFood" method="POST">
                        <input type="hidden" name="txtFoodId" value="${food_DTO.foodId}"/>
                        <input type="hidden" name="txtFoodName" value="${param.txtFoodName}"/>
                        <input type="hidden" name="txtCategory" value="${param.txtCategory}"/>
                        <input type="hidden" name="txtPriceMin" value="${param.txtPriceMin}"/>
                        <input type="hidden" name="txtPriceMax" value="${param.txtPriceMax}"/>
                        <input type="hidden" name="txtPageNumber" value="${param.txtPageNumber}"/>
                        <input type="submit" name="btnAction" value="Delete" onclick="return confirm('Are you sure you want to delete?')" />
                    </form> <br/>

                    <form action="adminEditFood" method="POST">
                        <input type="hidden" name="txtFoodId" value="${food_DTO.foodId}"/>
                        <input type="submit" name="btnAction" value="Edit"/>
                    </form>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${!check}">
            <h3>No Record Found</h3>
        </c:if>
    </center>
</body>
</html>

<%-- 
    Document   : login.jsp
    Created on : Feb 26, 2020, 6:45:52 PM
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
        <h1>Login Page</h1>
        <a href="home.jsp">Home</a><br/><br/>
    <center>
        <h2>Login to continue</h2>
        <div style="padding: 20px; margin: 50px; text-align: left; width: 500px; min-height: 50px; background-color: #F5F5F5; border: 1px solid #CDCDCD;">
            <form action="login" method="POST">
                Email: <input type="text" name="txtEmail" value="${param.txtEmail}"/>
                <font color="red">
                ${requestScope.INVALID.emailError}
                </font>
                <br/>
                Password: <input type="password" name="txtPassword"/>
                <font color="red">
                ${requestScope.INVALID.passwordError}
                </font>
                <br/><br/>
                <input type="submit" name="btnAction" value="Login"/><br/><br/>
                
                <a href="https://accounts.google.com/o/oauth2/auth?scope=email profile&redirect_uri=http://localhost:8080/HanaShop_VanThanhPhuongDat_SE130449/loginGoogle&response_type=code
    &client_id=96412228183-n832q7o703me5esfep37rsn7c5nijjvb.apps.googleusercontent.com&approval_prompt=force"">Login with Google</a><br/><br/>
                
            </form>
        </div>
    </center>
    </body>
</html>

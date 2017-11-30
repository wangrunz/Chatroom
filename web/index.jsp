<%-- 
    Document   : index
    Created on : Nov 29, 2017, 1:45:40 PM
    Author     : wrz19
--%>
<jsp:useBean id="ub" class="rz.UserBean" scope="session" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="mystyle.css"/>
        <title>Chat Room LogIn Page</title>
    </head>
    <body>
        <%if (ub.getusername()==""){%>
        <div id="main" class="textCenter">
            
            <h3>If you do not have account yet, please create account</h3>
            
            <form method="post" action="http://localhost:8080/Chatroom/register" class="formCenter">
                <table>
                    <tr>
                        <td>Email</td>
                        <td><input name="email" type="text"/></td>
                    </tr>
                    <tr>
                        <td>Username</td>
                        <td><input name="username" type="text"/></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input name="password" type="password"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Create Account"/>  </td>
                    </tr>    
                </table>
            </form>
            <h3>If you already have account, please sign in</h3>
            <form method="get" action="http://localhost:8080/Chatroom/signin" class="formCenter">
                <table>
                    <tr>
                        <td>Email</td>
                        <td><input name="email" type="text"/></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input name="password" type="password"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Sign In"/>  </td>
                    </tr>    
                </table>
            </form>
        </div>
        <%}else{
            String redirectURL = "http://localhost:8080/Chatroom/chatroom.jsp";
            response.sendRedirect(redirectURL);}%>
    </body>
</html>

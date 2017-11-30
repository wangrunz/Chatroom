<%-- 
    Document   : chatroom
    Created on : Nov 29, 2017, 1:44:09 PM
    Author     : wrz19
--%>
<jsp:useBean id="ub" class="rz.UserBean" scope="session" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="support.js" type="text/javascript"></script>
        <title>Chat Room</title>
    </head>
    <body>
        <%if (ub.getusername()==""){
            String redirectURL = "http://localhost:8080/Chatroom/index.jsp";
            response.sendRedirect(redirectURL);
        }else{%>
        <div>
            <table>
                <tr>
                    <td>
                        <h1>Hello <jsp:getProperty name="ub" property="username"></jsp:getProperty>!</h1>
                    </td>
                    <td>
                        <form method="get" action="http://localhost:8080/Chatroom/signout" class="formCenter">
                            <input type="submit" value="Sign out"/>
                        </form> 
                    </td>
                </tr>
            </table>
        </div>
        <div id="msgs" style="overflow:auto;height:400px">
            
        </div>
                    <div>
                        <table>
                            <tr>
                                <input id="sendbox" type="text" max_length="150"/>
                            </tr>
                            <tr>
                                <td>
                                    <button id="sendBTN" type="button" onclick="sendMsg()">Send</button>
                                </td>
                                <td>
                                    <button id="refreshBTN" type="button" onclick="refreshMsg(true)">Refresh</button>
                                </td>
                                <td>
                                    <button id="autoBTN" type="button" onclick="toggleAuto()">Auto:OFF</buttoON</butn>
                                </td>
                            </tr>
                        </table>
                    </div>
        <%}%>
    </body>
    <script>
        refreshMsg();
        toggleAuto();
    </script>
</html>

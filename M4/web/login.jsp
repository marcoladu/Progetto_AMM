<%-- 
    Document   : login
    Created on : 27-apr-2017, 17.06.23
    Author     : Marco Ladu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <c:set var="title" value="Login" scope="request"/>
    <jsp:include page="head.jsp"/>
    
    <body>    
        <div id="page">
            
            <jsp:include page="header.jsp"/>
            
            <jsp:include page="barraNav.jsp"/>
            
            <div id="content">
                <div id="formprofilo" class="formlogin">
                    <form action="Login" method="post">
                        <label for="inpuser" class="labelprof labellogin" id="userlogin">Username</label>
                        <input type="text" name="username" id="inpuser" class="inputprof" value="Inserisci Username">
                        <label for="psw" class="labelprof labellogin" id="pswlogin">Password</label>
                        <input type="password" name="psw" id="psw" class="inputprof" value="abcdefghi">
                        
                        <input type="submit" id="bottoneinvio" class="buttonlogin" value="Invio">
                    </form>
                </div>
                
                <c:if test="${invalidLogin == true}">
                    <div id="WarningLogin">
                        I dati inseriti non sono corretti!
                    </div>
                </c:if>
                
                <c:if test="${nonLoggatoWarning == true}">
                    
                    <div id="WarningLogin">
                        Non sei loggato, non puoi effettuare il logout!!
                    </div>
                </c:if>
                
            </div>
            
            <jsp:include page="clearfooter.jsp"/>
            
        </div>
    </body>
</html>

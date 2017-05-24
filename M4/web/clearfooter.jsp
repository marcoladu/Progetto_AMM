<%-- 
    Document   : clearfooter
    Created on : 27-apr-2017, 18.30.34
    Author     : Marco Ladu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="clear" <c:if test="${title=='Login'||title=='Profilo'||title=='Bacheca'}">class="clear${title}"</c:if>>
                
</div>
            
<div id="footer" <c:if test="${title=='Login'}">class="footerLogin"</c:if>>
    Creato da Sfinzz<sup>TM</sup> in una notte di luna piena!
</div>

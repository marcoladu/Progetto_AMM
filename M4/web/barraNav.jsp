<%-- 
    Document   : barraNav
    Created on : 27-apr-2017, 17.28.00
    Author     : Marco Ladu
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="barraNavigazione">
    <nav>
        <ol>
            <li class="titolo">NerdBook</li>
            <li class="<c:if test="${title=='Bacheca'}">navAttuale </c:if>primanav"><a href="Bacheca">Bacheca</a></li>
            <li <c:if test="${title=='Profilo'}">class="navAttuale"</c:if>><a href="Profilo">Profilo</a></li>
            <li class="<c:if test="${title=='Descrizione'}">navAttuale </c:if>terzanav"><a href="descrizione.jsp">Descrizione</a></li>
            <li <c:if test="${title=='Login'}">class="navAttuale"</c:if>><a href="Login">Login</a></li>
            <li class="terzanav"><a href="Logout">Logout</a></li>
        </ol>
    </nav>
</div>

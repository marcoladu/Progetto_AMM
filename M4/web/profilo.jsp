<%-- 
    Document   : profilo
    Created on : 27-apr-2017, 17.06.34
    Author     : Marco Ladu
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <c:set var="title" value="Profilo" scope="request"/>
    <jsp:include page="head.jsp"/>
    
    <body>
        <div id="page">
            
            <jsp:include page="header.jsp"/>
            
            <jsp:include page="barraNav.jsp"/>
                
                <c:if test="${nonLoggato == true}">
                    
                    <div id="content">
                        <div id="nonloggato">
                            Non sei loggato!!
                        </div>
                    </div>
 
                </c:if>
                
                <c:if test="${nonLoggato != true}">
                    
                    <jsp:include page="sidebar.jsp"/>
                    
                    <div id="content">
                        
                        <div id="formprofilo">
                            <form action="Profilo" method="post">

                                <div id="primodiv">
                                    <label for="nome" class="labelprof">Nome Utente</label>
                                    <input type="text" name="nome" id="nome" class="inputprof" value="${user.nomeUtente}">
                                </div>

                                <div>
                                    <label for="cognome" class="labelprof">Cognome Utente</label>
                                    <input type="text" name="cognome" id="cognome" class="inputprof" value="${user.cognomeUtente}">
                                </div>

                                <div>
                                    <label for="url" class="labelprof">URL Immagine Profilo</label>
                                    <input type="url" name="url" id="url" class="inputprof" value="${user.urlImg}">
                                </div>

                                <div>
                                    <label for="frase" class="labelprof">Frase di Benvenuto</label>
                                    <input type="text" name="frase" id="frase" class="inputprof" value="${user.fraseBenv}">
                                </div>

                                <div>
                                    <label for="data" class="labelprof">Data di Nascita</label>
                                    <input type="date" name="data" id="data" class="inputprof" value="${user.data}">
                                </div>

                                <div>
                                    <label for="psw" class="labelprof">Password</label>
                                    <input type="password" name="psw" id="psw" class="inputprof" value="${user.psw}">
                                </div>

                                <div>
                                    <label for="pswr" class="labelprof">Ripeti Password</label>
                                    <input type="password" name="pswr" id="pswr" class="inputprof" value="${user.psw}">
                                </div>

                                <input type="hidden" name="aggiornato" value="aggiornato">

                                <input type="submit" id="bottoneinvio" value="Aggiorna"><br/>
                            </form>
                        </div>
                    </div>
                </c:if>
                
                <c:if test="${modificatoMessage == true}">
                    <div id="modifMsg">
                        I dati sono stati salvati!
                    </div>
                </c:if>        
                        
            
            <jsp:include page="clearfooter.jsp"/>
            
        </div>
        
    </body>
</html>

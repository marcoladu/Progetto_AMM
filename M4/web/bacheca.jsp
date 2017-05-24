<%-- 
    Document   : bacheca
    Created on : 27-apr-2017, 17.05.51
    Author     : Marco Ladu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <c:set var="title" value="Bacheca" scope="request"/>
    <jsp:include page="head.jsp"/>
    
    <body>
        <div id="page">
            
            <jsp:include page="header.jsp"/>
            
            <jsp:include page="barraNav.jsp"/>

            <c:if test="${nonLoggato != true}">
                
                <jsp:include page="sidebar.jsp"/>
                
                <div id="content">
                    
                    <c:if test="${conferma == true}">
                        <div id="conferma">
                            
                            <form action="Bacheca" method="post" id="formBacheca">
                                <p>Repilogo Post:</p>
                                <p>Hai scritto sulla bacheca di ${userRicevi.nomeUtente}</p>
                                <c:if test="${textArea != null}">
                                    <p>${textArea}</p>
                                </c:if>
                                <c:if test="${urlImgPost != null}">
                                    <p>${urlImgPost}</p>
                                </c:if>
                                <c:if test="${urlLink != null}">
                                    <p>${urlLink}</p>
                                </c:if>
                                    
                                    <input type="hidden" name="Completato" value="ok"/>
                                    <input type="hidden" name="textArea" value="${textArea}"/>
                                    <input type="hidden" name="urlImgPost" value="${urlImgPost}"/>
                                    <input type="hidden" name="urlLink" value="${urlLink}"/>
                                    <input type="hidden" name="idBacheca" value="${idBacheca}"/>
                                    <input type="submit" id="finalSubmit" value="Confermo"/>
                            </form>
                        </div>
                    </c:if>
                    
                    <c:if test="${conferma != true}">
                        
                        <div id="inserisciPost">
                        
                            <form action="Bacheca" method="post">

                                <div>
                                    <label for="textArea" class="labelPost">Cosa vuoi scrivere?</label>
                                    <textarea name="textArea" id="textArea" rows="4" cols="50"></textarea>
                                </div>
                                <div>
                                    <label for="urlImgPost" class="labelPost">Url Immagine</label>
                                    <input type="text" name="urlImgPost" id="urlImgPost" class="inputPost"/>
                                </div>
                                <div>
                                    <label for="urlLink" class="labelPost">Url Link</label>
                                    <input type="text" name="urlLink" id="urlLink" class="inputPost"/>
                                </div>

                                <input type="hidden" name="inseriscoPost" value="inserisco"/>
                                <input type="hidden" name="idBacheca" value="${idBacheca}"/>
                                <input type="submit" id="submitPost" value="Aggiungi Post"/>
                            </form>
                        </div>
                    </c:if>
                    
                    <c:if test="${nessunPost != true}">
                        
                        <c:forEach var="post" items="${posts}">
                            <div id="divPost">
                            

                                <div class="imgprofilo"><img src="${post.user.urlImg}" alt="immagine profilo"/></div>
                                <div class="nomeprofilo">${post.user.nomeUtente} ${post.user.cognomeUtente}</div>
                                <div class="datapost">

                                    <c:if test="${post.textPost != null}">
                                        ${n}${post.textPost}<br/>
                                    </c:if>

                                    <c:if test="${post.imgPost != null}">
                                        <img src="${post.imgPost}" class="imgpost"/><br/>
                                    </c:if>

                                        <c:if test="${post.linkPost != null}">
                                            <a href="${post.linkPost}">${post.linkPost}</a><br/>
                                        </c:if>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>

                </div>
                
            </c:if>
            
            <c:if test="${nonLoggato == true}">
                
                <div id="content">
                    <div id="nonloggato">
                        Non sei loggato!!
                    </div>
                </div>
            </c:if>
            
            <jsp:include page="clearfooter.jsp"/>
            
        </div>
    </body>
</html>
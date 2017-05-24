<%-- 
    Document   : descrizione
    Created on : 27-apr-2017, 17.06.06
    Author     : Marco Ladu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <c:set var="title" value="Descrizione" scope="request"/>
    <jsp:include page="head.jsp"/>
    
    <body>        
        <div id="page">
            
            <jsp:include page="header.jsp"/>
            
            <jsp:include page="barraNav.jsp"/>
            
            <div id="sidebar1">
                <div id="navInterno">
                    <nav class="navint">
                        <h2>Link Interni</h2>
                        <ul>
                            <li><a href="#Introduzione">Chi siamo</a></li>
                            <li><a href="#Progetto">Il nostro progetto</a></li>
                            <li><a href="#Destinatario">Il nostro pubblico</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
            
            <div id="content">
                <div id="descr">
                    <div id="divIntroduzione">
                        <a id="Introduzione">
                            <h2>Chi siamo:</h2>
                            <p class="descrizione">Siamo un team di amici che si occupa di programmazione.<br/>
                            Attualmente studiamo all'università di Cagliari, nel corso di informatica.
                            </p>
                            <img src="IMG/palazzo.jpg" class="imgdescr" title="palazzo" alt="palazzo delle scienze"/>
                            <hr/>
                        </a>
                    </div>

                    <div id="divProgretto">
                        <a id='Progetto'>
                            <h2>Il nostro progetto:</h2>
                            <p class="descrizione">
                                Questo progetto consiste nel creare un social network per veri nerd.
                                Il nostro intento è mettere in comunicazione i nerd di tutto il paese e 
                                permettere loro di scambiare file, video, musica o semplicemente un testo.
                                Permettendo la comunicazione tra queste persone puntiamo a creare dei gruppi lavoro, 
                                o anche solo far interagire dei "cervelli" dislocati nel territorio.
                                In più vogliamo i money. E magari aiutare a trovare <b>la nerd</b> che fa per voi!.
                            </p>
                            <hr/>
                        </a>
                    </div>

                    <div id="divDestinatario">
                        <a id="Destinatario">
                            <h2>A chi vogliamo rivolgerci:</h2>
                            <p class="descrizione">Chi è il nerd?</p>
                            <img src="IMG/nerd.png" class="imgdescr" title="nerd" alt="nerd by google"/>

                            <p class="descrizione">
                                <b>Come sempre troppo gentili!</b>
                                Allora il nerd non è detto che sia di "<u><b>modesta prestanza fisica</b></u>".
                                Come non è detto che sia frustrato. Ma è vero che sviluppa ossessioni verso tutto ciò
                                che è tecnologico. Il nerd ha qualche cosa in più. &Egrave; vero che certe volte si isola 
                                ma solo perchè magari vuole finire un boss di <b>DarkSouls</b> o magari vincere una comp su <b>CS GO</b>.
                            </p>
                            <img src="IMG/csgo.jpg" class="imgdescr" title="csgo" alt="csgo"/><br/>
                            <hr/>
                        </a>
                    </div>
                </div>
            </div>
            
            <jsp:include page="clearfooter.jsp"/>
            
        </div>    
    </body>
</html>
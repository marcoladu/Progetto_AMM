/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.Servlet;

import amm.Classi.PostFactory;
import amm.Classi.Post;
import amm.Classi.UtentiFactory;
import amm.Classi.Utenti;
import java.util.ArrayList;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marco Ladu
 */
public class Bacheca extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private static Bacheca temp = new Bacheca();
    
    public static Bacheca getInstance(){
        return temp;
    }
    
    public Bacheca() {
        
    }
    
    //Gestione DB
    private String connectionString;
    
    public void setConnectionString(String s){
	this.connectionString = s;
    }
    
    public String getConnectionString(){
            return this.connectionString;
    }
    //Fine gestione DB
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(false);
        
        if(session!=null && session.getAttribute("loggato")!=null && session.getAttribute("loggato").equals(true)){
            
            Integer idInteger = (Integer)session.getAttribute("loginId");
            int id = idInteger;
            String idBacheca = request.getParameter("idBacheca");
            ArrayList<Post> posts;
            
            if(idBacheca != null){
                Integer idB = Integer.parseInt(idBacheca);
                posts = PostFactory.getInstance().getListaPerUser(idB);
                request.setAttribute("idBacheca", idB);
            }else {
                posts = PostFactory.getInstance().getListaPerUser(id);
                request.setAttribute("idBacheca", id);
            }
            
            if(id >= 0){
                
                 if(posts != null){
                     
                    request.setAttribute("posts", posts);
                    request.getRequestDispatcher("bacheca.jsp").forward(request, response);
                }else {

                    request.setAttribute("nessunPost",true);
                    request.getRequestDispatcher("bacheca.jsp").forward(request, response);
                }
            }
            
            if(request.getParameter("inseriscoPost") != null){
                int idDest = Integer.parseInt(request.getParameter("idBacheca"));
                Utenti userRicevi = UtentiFactory.getInstance().getPerId(idDest);
                
                request.setAttribute("conferma", true);
                request.setAttribute("userRicevi", userRicevi);
                
                if(request.getParameter("textArea") != null){
                    String text = request.getParameter("textArea");
                    request.setAttribute("textArea",text);
                }
                if(request.getParameter("urlImgPost") != null){
                    request.setAttribute("urlImgPost",request.getParameter("urlImgPost"));
                }
                if(request.getParameter("urlLink") != null){
                    request.setAttribute("urlLink",request.getParameter("urlLink"));
                }
                request.setAttribute("idBacheca",idDest);

            }
            
            if(request.getParameter("Completato") != null){
                
                int idTemp = Integer.parseInt(request.getParameter("idBacheca"));
                    
                try{
                        
                        Connection conn = DriverManager.getConnection(connectionString, "amm", "amm");
                        
                        String query = 
                                  "insert into post (post_id, post_idProprietario,"
                                + " post_idBacheca, post_text, post_img, post_link)"
                                + " values "
                                + "(default,"
                                + "?, ?, ?, ?, ?)";

                        // Prepared Statement
                        PreparedStatement stmt = conn.prepareStatement(query);
                        
                        stmt.setInt(1, id);
                        stmt.setInt(2, idTemp);
                        stmt.setString(3, request.getParameter("textArea"));
                        stmt.setString(4, request.getParameter("urlImgPost"));
                        stmt.setString(5, request.getParameter("urlLink"));
                        
                        int rows = stmt.executeUpdate();
                        
                        if(rows == 1){
                            request.getRequestDispatcher("bacheca.jsp").forward(request, response);
                        }
                        
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }
                    
            }
            
            
            
        }
        
        
        /*HttpSession session = request.getSession(false);
        
        if(session!=null && session.getAttribute("loggato")!=null && session.getAttribute("loggato").equals(true)){
            
            Integer idInteger = (Integer)session.getAttribute("loginId");
            int id = idInteger;
            Utenti utente;
            ArrayList<Post> posts;
            Utenti userRicevi = null;
            
            String idBacheca = request.getParameter("idBacheca");
            
            if(idBacheca != null){
                int idOspite = Integer.parseInt(idBacheca);
                userRicevi = UtentiFactory.getInstance().getPerId(idOspite);
                
                if(idOspite != id){
                    utente = UtentiFactory.getInstance().getPerId(idOspite);
                    posts = PostFactory.getInstance().getListaPerUser(idOspite);
                    request.setAttribute("idBacheca", idOspite);
                    
                }else {
                    utente = UtentiFactory.getInstance().getPerId(id);
                    posts = PostFactory.getInstance().getListaPerUser(id);
                    request.setAttribute("idBacheca", id);
                }
            }else {
                
                utente = UtentiFactory.getInstance().getPerId(id);
                posts = PostFactory.getInstance().getListaPerUser(id);
                request.setAttribute("idBacheca", id);
            }
            
            if(request.getParameter("inseriscoPost") != null){
                
                request.setAttribute("conferma", true);
                request.setAttribute("userRicevi", userRicevi);
                if(request.getParameter("textArea") != null){
                    String text = request.getParameter("textArea");
                    request.setAttribute("textArea",text);
                }
                if(request.getParameter("urlImgPost") != null){
                    request.setAttribute("urlImgPost",request.getParameter("urlImgPost"));
                }
                if(request.getParameter("urlLink") != null){
                    request.setAttribute("urlLink",request.getParameter("urlLink"));
                }
                request.setAttribute("idBacheca",request.getParameter("idBacheca"));
                
                if(request.getParameter("idBacheca") != null){
                        int idProprietario = Integer.parseInt(request.getParameter("idBacheca"));
                        posts = PostFactory.getInstance().getListaPerUser(idProprietario);
                }

            }
            
             if(request.getParameter("Completato") != null){
                    
                    Post nuovoPost = new Post();
                    nuovoPost.setUser(UtentiFactory.getInstance().getPerId(id));
                    nuovoPost.setUrlImg(nuovoPost.getUser().getUrlImg());
                    if(request.getParameter("textArea") != null){
                        nuovoPost.setTextPost(request.getParameter("textArea"));
                    }
                    if(request.getParameter("urlImgPost") != null){
                        nuovoPost.setImgPost(request.getParameter("urlImgPost"));
                    }
                    if(request.getParameter("urlLink") != null){
                        nuovoPost.setLinkPost(request.getParameter("urlLink"));
                    }

                        posts.add(nuovoPost);
                    
            }
            
            if(id >= 0 && utente != null){
                
                 if(posts != null){
                     
                    request.setAttribute("utente",utente);
                    request.setAttribute("posts", posts);
                    request.getRequestDispatcher("bacheca.jsp").forward(request, response);
                }else {

                    request.setAttribute("nessunPost",true);
                    request.getRequestDispatcher("bacheca.jsp").forward(request, response);
                }
            }
            
            
        }else {
            
            request.setAttribute("nonLoggato", true);
            request.getRequestDispatcher("bacheca.jsp").forward(request, response);
        }*/
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

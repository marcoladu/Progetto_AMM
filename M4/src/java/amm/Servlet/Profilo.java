/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.Servlet;

import amm.Classi.PostFactory;
import amm.Classi.UtentiFactory;
import amm.Classi.Utenti;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Marco Ladu
 */

@WebServlet(loadOnStartup = 0)
public class Profilo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CLEAN_PATH = "../../web/WEB-INF/db/ammdb";
    private static final String DB_BUILD_PATH = "WEB-INF/db/ammdb";
    
    @Override
   public void init(){
       String dbConnection = "jdbc:derby:" + this.getServletContext().getRealPath("/") + DB_BUILD_PATH;
       try {
           Class.forName(JDBC_DRIVER);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
       }
       UtentiFactory.getInstance().setConnectionString(dbConnection);
       PostFactory.getInstance().setConnectionString(dbConnection);
   }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(false);
        
        if(session!=null && session.getAttribute("loggato")!=null && session.getAttribute("loggato").equals(true)){
            
            Integer idInteger = (Integer)session.getAttribute("loginId");
            int id = idInteger;
            
            Utenti user = UtentiFactory.getInstance().getPerId(id);
            
            if(user != null) {
                
                if(request.getParameter("aggiornato") != null){
                    
                    Utenti temp = new Utenti();
                    temp.setNomeUtente(request.getParameter("nome"));
                    temp.setCognomeUtente(request.getParameter("cognome"));
                    temp.setUrlImg(request.getParameter("url"));
                    temp.setFraseBenv(request.getParameter("frase"));
                    temp.setData(request.getParameter("data"));
                    temp.setPsw(request.getParameter("psw"));
                    
                    UtentiFactory.getInstance().modUtente(temp);
                    
                    request.setAttribute("modificatoMessage", true);
                }
                
                request.setAttribute("user", user);
                
                /*try{
                        // path, username, password
                        Connection conn = DriverManager.getConnection(dbConnection, "amm", "amm");

                        String query = 
                                  "select * from utenti "
                                + "where utenti_id = ?";

                        // Prepared Statement
                        PreparedStatement stmt = conn.prepareStatement(query);

                        // Si associano i valori
                        stmt.setInt(1, id);

                        // Esecuzione query
                        ResultSet res = stmt.executeQuery();

                        // ciclo sulle righe restituite
                        if (res.next()) {
                            Utenti current = new Utenti();
                            current.setId(res.getInt("utenti_id"));
                            current.setNomeUtente(res.getString("utenti_nome"));
                            current.setCognomeUtente(res.getString("razza"));
                            current.setUrlImg(res.getString("utenti_urlImg"));
                            current.setFraseBenv(res.getString("utenti_frase"));
                            current.setData(res.getString("utenti_data"));
                            current.setPsw(res.getString("utenti_password"));

                            stmt.close();
                            conn.close();
                            request.setAttribute("user", current);
                        }

                        stmt.close();
                        conn.close();

                    }catch (SQLException e) {
                        e.printStackTrace();
                    }*/
            
                request.getRequestDispatcher("profilo.jsp").forward(request, response);
            }else {
                
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
            
        }else {
            
            request.setAttribute("nonLoggato", true);
            request.getRequestDispatcher("profilo.jsp").forward(request, response);
        }
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.Servlet;

import amm.Classi.PostFactory;
import amm.Classi.UtentiFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marco Ladu
 */
@WebServlet(loadOnStartup = 0)
public class Login extends HttpServlet {

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
        
        //Avvio la sessione
        HttpSession session = request.getSession();
        
        //Se sono loggato mostro la bacheca
        if(session.getAttribute("loggato")!=null && session.getAttribute("loggato").equals(true)){
            
            request.getRequestDispatcher("Bacheca").forward(request, response);
            return;
            
        }else {
            //altrimenti
            String user = request.getParameter("username");
            String psw = request.getParameter("psw");
            //Controllo che l'username e la password non siano nulli
            if(user != null && psw != null){
                //Prendo l'id dell'utente tramite la getIdPerLogin
                int loginId = UtentiFactory.getInstance().getIdPerLogin(user, psw);
                
                //Se i dati sono corretti loggo
                if(loginId != -1 && loginId >= 0){
                    session.setAttribute("loggato", true);
                    session.setAttribute("loginId", loginId);
                    
                    request.getRequestDispatcher("Bacheca").forward(request, response);
                    //return;
                }else {
                    
                    if(loginId == -1){
                        //Se loginId è -1 i dati non erano corretti
                        request.setAttribute("invalidLogin", true);
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                        return;
                    }else {
                        //Altrimenti -2 significa che il profilo utente è incompleto
                        loginId = loginId*-1;
                        session.setAttribute("loggato", true);
                        session.setAttribute("loginId", loginId);
                        request.getRequestDispatcher("Profilo").forward(request, response);
                    }
                    
                }
            }
        }
        //Tentativo diverso dagli altri
        request.getRequestDispatcher("login.jsp").forward(request, response);
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

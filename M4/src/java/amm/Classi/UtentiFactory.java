/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.Classi;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Marco Ladu
 */
public class UtentiFactory {
    
    private static UtentiFactory singleton;
    
    public static UtentiFactory getInstance(){
        
        if(singleton == null){
            singleton = new UtentiFactory();
        }
        
        return singleton;
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
    
    private ArrayList<Utenti> lista = new ArrayList<>();
    
    public UtentiFactory(){
        
    }
    
    public Utenti getPerId(int id){
        
        try{
            
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "amm", "amm");
            
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
                current.setCognomeUtente(res.getString("utenti_cognome"));
                current.setUrlImg(res.getString("utenti_urlImg"));
                current.setFraseBenv(res.getString("utenti_frase"));
                current.setData(res.getString("utenti_data"));
                current.setPsw(res.getString("utenti_password"));
                
                stmt.close();
                conn.close();
                return current;
            }
            
            stmt.close();
            conn.close();
            
        }catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public int getIdPerLogin(String user, String psw){
        
        try{

                // path, username, password
                Connection conn = DriverManager.getConnection(connectionString, "amm", "amm");

                String query = 
                          "select * from utenti "
                        + "where utenti_nome = ? and utenti_password = ?";

                // Prepared Statement
                PreparedStatement stmt = conn.prepareStatement(query);

                // Si associano i valori
                stmt.setString(1, user);
                stmt.setString(2, psw);

                // Esecuzione query
                ResultSet res = stmt.executeQuery();

                // ciclo sulle righe restituite
                if (res.next()) {
                    Utenti current = new Utenti();
                    current.setId(res.getInt("utenti_id"));
                    current.setNomeUtente(res.getString("utenti_nome"));
                    current.setCognomeUtente(res.getString("utenti_cognome"));
                    current.setUrlImg(res.getString("utenti_urlImg"));
                    current.setFraseBenv(res.getString("utenti_frase"));
                    current.setData(res.getString("utenti_data"));
                    current.setPsw(res.getString("utenti_password"));
                    
                    if(current.utenteIncompleto() == -2){
                        
                        stmt.close();
                        conn.close();
                        return -1*current.getId();
                    }
                    
                    stmt.close();
                    conn.close();
                    return current.getId();
                }

                stmt.close();
                conn.close();

            }catch (SQLException e) {
                e.printStackTrace();
            }

            return -1;
    }
    
    public void modUtente(Utenti utente, int id){
        
        try{
                        
            Connection conn = DriverManager.getConnection(connectionString, "amm", "amm");
                        
            String query = 
                            "update utenti set "
                            + "utenti_nome = ?,"
                            + "utenti_cognome = ?,"
                            + "utenti_urlImg = ?,"
                            + "utenti_frase = ?,"
                            + "utenti_data = ?,"
                            + "utenti_password = ? "
                            + "where utenti_id = ?";

                        // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
                        
            stmt.setString(1, utente.getNomeUtente());
            stmt.setString(2, utente.getCognomeUtente());
            stmt.setString(3, utente.getUrlImg());
            stmt.setString(4, utente.getFraseBenv());
            stmt.setString(5, utente.getData());
            stmt.setString(6, utente.getPsw());
            stmt.setInt(7, id);
                        
            int rows = stmt.executeUpdate();
            
            stmt.close();
            conn.close();
                        
        }catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
}

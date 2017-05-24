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
public class PostFactory {
    
    private static PostFactory singleton;
    
    public static PostFactory getInstance(){
        if(singleton == null){
            singleton = new PostFactory();
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
    
    ArrayList<Post> lista = new ArrayList<>();
    
    public PostFactory(){
 
    }
    
    public Post getPostPerId(int id){
        
        try{
            
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "amm", "amm");
            
            String query = 
                      "select * from post "
                    + "where post_id = ?";
            
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            
            // Si associano i valori
            stmt.setInt(1, id);
            
            // Esecuzione query
            ResultSet res = stmt.executeQuery();
            
            // ciclo sulle righe restituite
            if (res.next()) {
                int idPropr = res.getInt("idProprietario");
                
                Utenti user = UtentiFactory.getInstance().getPerId(idPropr);
                
                Post current = new Post();
                current.setId(res.getInt("post_id"));
                current.setUser(user);
                current.setUrlImg(user.getUrlImg());
                current.setTextPost(res.getString("post_text"));
                current.setImgPost(res.getString("post_img"));
                current.setLinkPost(res.getString("post_link"));
                
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
    
    public ArrayList<Post> getListaPerUser(int bachecaId){
        
        ArrayList<Post> tmp = new ArrayList<>();
        
        try{
            
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "amm", "amm");
            
            String query = 
                      "select * from post "
                    + "where post_idBacheca = ?";
            
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            
            // Si associano i valori
            stmt.setInt(1, bachecaId);
            
            // Esecuzione query
            ResultSet res = stmt.executeQuery();
            
            int idPropr;
            Utenti user;
            Post current;
            
            // ciclo sulle righe restituite
            while (res.next()) {
                idPropr = res.getInt("post_idProprietario");
                
                user = UtentiFactory.getInstance().getPerId(idPropr);
                
                current = new Post();
                current.setId(res.getInt("post_id"));
                current.setUser(user);
                current.setUrlImg(user.getUrlImg());
                current.setTextPost(res.getString("post_text"));
                current.setImgPost(res.getString("post_img"));
                current.setLinkPost(res.getString("post_link"));
                
                tmp.add(current);
            }
            
            stmt.close();
            conn.close();
            
            
        }catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tmp;
    }
    
    public void addPost(Post post){
        
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
            
            stmt.setInt(1, post.getUser().getId());
            stmt.setInt(2, post.getIdBacheca());
            stmt.setString(3, post.getTextPost());
            stmt.setString(4, post.getUrlImg());
            stmt.setString(5, post.getLinkPost());
            
            int rows = stmt.executeUpdate();
            
            stmt.close();
            conn.close();
                        
        }catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
}

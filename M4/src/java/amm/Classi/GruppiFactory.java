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
public class GruppiFactory {
    
    private static GruppiFactory singleton;
    
    public static GruppiFactory getInstance(){
        if(singleton == null){
            singleton = new GruppiFactory();
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
    
    private ArrayList<Gruppi> lista = new ArrayList<>();
    
    public GruppiFactory(){
 
    }
    
    public Gruppi getPerId(int id){
        for(Gruppi gruppo: this.lista){
            if(gruppo.getId() == id){
                return gruppo;
        }
    }
    
    return null;
    
    }
    
    public ArrayList<Gruppi> getLista(){
        return this.lista;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.Classi;

/**
 *
 * @author Marco Ladu
 */
public class Gruppi {
            
    private int id;
    private String nome;
    
    public Gruppi(){
        this.id = 0;
        this.nome = "";
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
}

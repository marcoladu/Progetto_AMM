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
public class Utenti {
    private String nomeUtente;
    private String cognomeUtente;
    private int id;
    private String urlImg;
    private String fraseBenv;
    private String data;
    private String password;
    
    public Utenti() {
        
        this.id = 0;
        this.nomeUtente = "";
        this.cognomeUtente = "";
        this.urlImg = "";
        this.fraseBenv = "";
        this.data = "";
        this.password = "";
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getNomeUtente(){
        return this.nomeUtente;
    }
    
    public void setNomeUtente(String nome){
        this.nomeUtente = nome;
    }
    
    public String getCognomeUtente(){
        return this.cognomeUtente;
    }
    
    public void setCognomeUtente(String cognome){
        this.cognomeUtente = cognome;
    }
    
    public String getUrlImg(){
        return this.urlImg;
    }
    
    public void setUrlImg(String url){
        this.urlImg = url;
    }
    
    public String getFraseBenv(){
        return this.fraseBenv;
    }
    
    public void setFraseBenv(String frase){
        this.fraseBenv = frase;
    }
    
    public String getData(){
        return this.data;
    }
    
    public void setData(String data){
        this.data = data;
    }
    
    public String getPsw(){
        return this.password;
    }
    
    public void setPsw(String psw){
        this.password = psw;
    }
    
    public int utenteIncompleto(){
        String flag = "";
        if(this.getNomeUtente().equals(flag)) return -2;
        if(this.getCognomeUtente().equals(flag)) return -2;
        if(this.getUrlImg().equals(flag)) return -2;
        if(this.getFraseBenv().equals(flag)) return -2;
        if(this.getData().equals(flag)) return -2;
        if(this.getPsw().equals(flag)) return -2;
        
        return -3;
    }
    
}

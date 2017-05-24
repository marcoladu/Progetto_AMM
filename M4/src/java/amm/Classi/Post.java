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
public class Post {
    
    private int id;
    private Utenti user;
    private String urlImg;
    private String textPost;
    private String imgPost;
    private String linkPost;
    private String linkPostText;
    private int idBacheca;
    
    public Post(){
        this.id = 0;
        this.user = null;
        this.urlImg = null;
        this.textPost = null;
        this.imgPost = null;
        this.linkPost = null;
        this.linkPostText = null;
        this.idBacheca = 0;
    }
    
    public int getIdBacheca(){
        return this.idBacheca;
    }
    
    public void setIdBacheca(int id){
        this.idBacheca = id;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public Utenti getUser(){
        if(this.user != null){
            return this.user;
        }else {
            return null;
        }
    }
    
    public void setUser(Utenti user){
        this.user = user;
    }
    
    public String getUrlImg(){
        if(this.urlImg != null){
            return this.urlImg;
        }else {
            return null;
        }
    }
    
    public void setUrlImg(String url){
        this.urlImg = url;
    }
    
    public String getTextPost(){
        if(this.textPost != null){
            return this.textPost;
        }else {
            return null;
        }
    }
    
    public void setTextPost(String text){
        this.textPost = text;
    }
    
    public String getImgPost(){
        if(this.imgPost != null){
            return this.imgPost;
        }else {
            return null;
        }
    }
    
    public void setImgPost(String url){
        this.imgPost = url;
    }
    
    public String getLinkPost(){
        if(this.linkPost != null){
            return this.linkPost;
        }else {
            return null;
        }
    }
    
    public void setLinkPost(String url){
        this.linkPost = url;
    }
    
    public String getLinkPostText(){
        if(this.linkPostText != null){
            return this.linkPostText;
        }else {
            return null;
        }
    }
    
    public void setLinkPostText(String text){
        this.linkPostText = text;
    }
    
    
}

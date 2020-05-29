/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.Usuario;
import facade.LoginFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author hiago
 */
@Named
@SessionScoped
public class LoginMB implements Serializable {
    
    private String email;
    private String senha;
    
    public LoginMB() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    public void realizaLogin(){
        try{
            MessageDigest m;
            m = MessageDigest.getInstance("MD5");
            m.update(this.senha.getBytes(),0,this.senha.length());
            String senhaC = new BigInteger(1,m.digest()).toString(16);
            Usuario usuario = LoginFacade.buscarLogin(email, senhaC);
            
            if(usuario!=null){
                switch(usuario.getTipo().getDescricao()){
                    case "Juiz":
                        FacesContext.getCurrentInstance().getExternalContext().redirect("juiz/home.xhtml");
                        break;
                    default:
                        break;
                }
            }
            
        } catch (Exception ex) {
            Logger.getLogger(LoginMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

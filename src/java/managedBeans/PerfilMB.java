/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.Processo;
import beans.Usuario;
import facade.CadastroFacade;
import facade.LoginFacade;
import facade.ProcessoFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import util.SessionContext;

/**
 *
 * @author hiago
 */
@Named
@RequestScoped
public class PerfilMB implements Serializable {
    
    private String email;
    private String nome;
    private String senha;
    private String senha2;
    private Usuario usuario;
    
    public PerfilMB() {
    }
    
    @PostConstruct
    public void init(){
        usuario = (Usuario) SessionContext.getInstance().getAttribute("usuarioLogado");
        email = usuario.getEmail();
        nome = usuario.getNome();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha2() {
        return senha2;
    }

    public void setSenha2(String senha2) {
        this.senha2 = senha2;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String alterarPerfil(){
        if(!email.equals(usuario.getEmail())){
            long resultado = CadastroFacade.verificaEmail(email);
            if(resultado!=0){
                System.out.println("Email ja cadastrado");
                return "/juiz/home.xhtml";
            }
        }
        MessageDigest m;
        try {
            if(!this.senha.isEmpty()){
                m = MessageDigest.getInstance("MD5");            
                m.update(this.senha.getBytes(),0,this.senha.length());
                String senhaC = new BigInteger(1,m.digest()).toString(16);
                usuario.setSenha(senhaC);
            }
            usuario.setEmail(email);
            usuario.setNome(nome);
            CadastroFacade.alterar(usuario);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PerfilMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/juiz/profile.xhtml";
    }
}

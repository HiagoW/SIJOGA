/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.Processo;
import beans.Usuario;
import facade.LoginFacade;
import facade.ProcessoFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import util.SessionContext;

/**
 *
 * @author hiago
 */
@Named
@SessionScoped
public class LoginMB implements Serializable {
    
    private String email;
    private String senha;
    private List<Processo> processos = new ArrayList<>();
    private long processosAtivos;
    
    public LoginMB() {
    }
    
    /**
     * Retorna usuario logado
     * */
    public Usuario getUser() {
       return (Usuario) SessionContext.getInstance().getUsuarioLogado();
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

    public List<Processo> getProcessos() {
        return processos;
    }

    public void setProcessos(List<Processo> processos) {
        this.processos = processos;
    }

    public long getProcessosAtivos() {
        return processosAtivos;
    }

    public void setProcessosAtivos(long processosAtivos) {
        this.processosAtivos = processosAtivos;
    }
    
    
    
    public String realizaLogin(){
        try{
            MessageDigest m;
            m = MessageDigest.getInstance("MD5");
            m.update(this.senha.getBytes(),0,this.senha.length());
            String senhaC = new BigInteger(1,m.digest()).toString(16);
            Usuario usuario = LoginFacade.buscarLogin(email, senhaC);
            
            if(usuario!=null){
                switch(usuario.getTipo().getDescricao()){
                    case "Juiz":
                        processos = ProcessoFacade.buscaProcessos(usuario);
                        processosAtivos = ProcessoFacade.processosAtivos(usuario);
                        SessionContext.getInstance().setAttribute("usuarioLogado", usuario);
                        return "/juiz/home.xhtml?faces-redirect=true";
                    default:
                        return "";
                }
            }else{
                FacesContext.getCurrentInstance().validationFailed();
                return "";
            }
            
        } catch (Exception ex) {
            Logger.getLogger(LoginMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public String doLogout() throws IOException{
        SessionContext.getInstance().encerrarSessao();
        return "/login.xhtml?faces-redirect=true";
    }
}

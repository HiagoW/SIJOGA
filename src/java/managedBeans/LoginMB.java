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
    private Usuario usuario;
    private List<Processo> processos = new ArrayList<>();
    private List<Processo> processosPromovente = new ArrayList<>();
    private List<Processo> processosPromovido = new ArrayList<>();
    private long processosAtivos = 0;
    private long processosAguardandoIntimacao = 0;
    private long processosEncerrados = 0;
    
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
    
    public List<Processo> getProcessosPromovente() {
        return processosPromovente;
    }

    public void setProcessosPromovente(List<Processo> processos) {
        this.processosPromovente = processos;
    }
    
    public List<Processo> getProcessosPromovido() {
        return processosPromovido;
    }

    public void setProcessosPromovido(List<Processo> processos) {
        this.processosPromovido = processos;
    }

    public long getProcessosAtivos() {
        return processosAtivos;
    }

    public void setProcessosAtivos(long processosAtivos) {
        this.processosAtivos = processosAtivos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public long getProcessosAguardandoIntimacao() {
        return processosAguardandoIntimacao;
    }

    public void setProcessosAguardandoIntimacao(long processosAguardandoIntimacao) {
        this.processosAguardandoIntimacao = processosAguardandoIntimacao;
    }

    public long getProcessosEncerrados() {
        return processosEncerrados;
    }

    public void setProcessosEncerrados(long processosEncerrados) {
        this.processosEncerrados = processosEncerrados;
    }
    
    
    
    public String realizaLogin(){
        try{
            MessageDigest m;
            m = MessageDigest.getInstance("MD5");
            m.update(this.senha.getBytes(),0,this.senha.length());
            String senhaC = new BigInteger(1,m.digest()).toString(16);
            usuario = LoginFacade.buscarLogin(email, senhaC);
            
            if(usuario!=null){
                switch(usuario.getTipo().getDescricao()){
                    case "Juiz":
                        processos = ProcessoFacade.buscaProcessos(usuario);
                        for(Processo p : processos){
                            if(p.getFaseAtual().getFase().getId() == 6){
                                processosEncerrados+=1;
                            }else if(p.getFaseAtual().getFase().getId() == 5){
                                processosAguardandoIntimacao+=1;
                            }else{
                                processosAtivos+=1;
                            }
                        }
                        SessionContext.getInstance().setAttribute("usuarioLogado", usuario);
                        return "/juiz/home.xhtml?faces-redirect=true";
                    case "Advogado":
                        processosPromovente = ProcessoFacade.buscaProcessosAdvPromovente(usuario);
                        processosPromovido = ProcessoFacade.buscaProcessosAdvPromovido(usuario);
                        /*processosAtivos = ProcessoFacade.processosAtivos(usuario);*/
                        SessionContext.getInstance().setAttribute("usuarioLogado", usuario);
                        return "/advogado/home.xhtml?faces-redirect=true";
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

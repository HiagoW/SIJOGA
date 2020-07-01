/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.FaseProcesso;
import beans.Processo;
import beans.ProcessoFase;
import beans.Usuario;
import facade.CadastroFacade;
import facade.FaseProcessoFacade;
import facade.LoginFacade;
import facade.ProcessoFacade;
import facade.ProcessoFaseFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import util.SessionContext;

/**
 *
 * @author hiago
 */
@Named
@SessionScoped
public class CriaProcessoMB implements Serializable {
    
    private List<Usuario> partes;
    private List<Usuario> partes2 = new ArrayList();
    private List<Usuario> partesBackup = new ArrayList();
    private Usuario cliente = null;
    private Usuario parte = null;
    
    public CriaProcessoMB() {
    }
    
    @PostConstruct
   public void init(){
       partes = CadastroFacade.buscarPartes();
       partes2.addAll(partes);
       partesBackup.addAll(partes);
   }

    public List<Usuario> getPartes() {
        return partes;
    }

    public void setPartes(List<Usuario> partes) {
        this.partes = partes;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public List<Usuario> getPartes2() {
        return partes2;
    }

    public void setPartes2(List<Usuario> partes2) {
        this.partes2 = partes2;
    }

    public Usuario getParte() {
        return parte;
    }

    public void setParte(Usuario parte) {
        this.parte = parte;
    }
    
    
    
    public String criar(){
        Processo p = new Processo();
        Usuario adv = (Usuario) SessionContext.getInstance().getAttribute("usuarioLogado");
        p.setAdvogadoPromovente(adv);
        p.setPromovente(cliente);
        p.setPromovido(parte);
        p.setData(new Date());
        p.setStatus(1);
        Usuario juiz = CadastroFacade.buscarJuizComMenosProcesso();
        Usuario advParte = CadastroFacade.buscarAdvogadoAleatorio();
        p.setAdvogadoPromovido(advParte);
        p.setJuiz(juiz);
        ProcessoFacade.cadastrar(p);
        FacesMessage mensagem = new FacesMessage("Processo cadastrado","");
        mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
        return "/advogado/home.xhtml";
    }
    
    public void atualizaPartes(){
       partes.clear();
       partes.addAll(partesBackup);
       partes2.clear();
       partes2.addAll(partesBackup);
       if(cliente!=null){
           partes2.remove(cliente);
       }
       if(parte!=null){
           partes2.remove(parte);
       }
    }
}

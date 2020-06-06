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
@SessionScoped
public class ProcessoMB implements Serializable {
    
    private Processo processo;
    private List<String> opcoesJuiz;
    private String acaoJuiz;
    
    public ProcessoMB() {
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public List<String> getOpcoesJuiz() {
        opcoesJuiz = new ArrayList<>();
        if(this.processo.getFaseAtual().getFase().getId()==2){
            opcoesJuiz.add("Aceitar Pedido");
            opcoesJuiz.add("Negar Pedido");
        }
        opcoesJuiz.add("Intimação");
            opcoesJuiz.add("Encerrar");
        return opcoesJuiz;
    }

    public String getAcaoJuiz() {
        return acaoJuiz;
    }

    public void setAcaoJuiz(String acaoJuiz) {
        this.acaoJuiz = acaoJuiz;
    }
    
    public void processaAcao(){
        System.out.println(acaoJuiz);
    }
    
    public void setOpcoesJuiz(List<String> opcoesJuiz) {
        this.opcoesJuiz = opcoesJuiz;
    }

    
    
    
   public String visualizar(long id){
       processo = ProcessoFacade.buscarProcesso(id);
       return "/juiz/encaminhamento.xhtml";
   }
}

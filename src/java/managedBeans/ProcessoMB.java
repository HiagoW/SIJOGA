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
@RequestScoped
public class ProcessoMB implements Serializable {
    
    private Processo processo;
    private boolean necessitaResposta;
    
    public ProcessoMB() {
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public boolean isNecessitaResposta() {
        return this.processo.getFaseAtual().getFase().getId()==2;
    }

    public void setNecessitaResposta(boolean necessitaResposta) {
        this.necessitaResposta = necessitaResposta;
    }
    
    
   public String visualizar(long id){
       processo = ProcessoFacade.buscarProcesso(id);
       return "/juiz/encaminhamento.xhtml";
   }
}

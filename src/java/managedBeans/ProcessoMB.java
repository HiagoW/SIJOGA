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
    private String justificativa;
    
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

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }
    
    
    
    public String processaAcao(){
        Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("usuarioLogado");
        ProcessoFase fase = new ProcessoFase();
        fase.setData(new Date());
        fase.setProcesso(processo);
        fase.setResponsavel(usuario);
        FaseProcesso faseProcesso = null;
        switch(acaoJuiz){
            case "Aceitar Pedido":
                faseProcesso = FaseProcessoFacade.buscarFase("Aceito");
                break;
            case "Negar Pedido":
                fase.setResposta(justificativa);
                faseProcesso = FaseProcessoFacade.buscarFase("Negado");
                break;
            case "Intimação":
                faseProcesso = FaseProcessoFacade.buscarFase("Intimacao");
                break;
            case "Encerrar":
                fase.setResposta(justificativa);
                faseProcesso = FaseProcessoFacade.buscarFase("Encerramento");
                break;
            default:
                faseProcesso = FaseProcessoFacade.buscarFase("Informativa");
                break;
        }
        fase.setFase(faseProcesso);
        ProcessoFaseFacade.criarFase(fase);
        return "/juiz/home.xhtml";
    }
    
    public void setOpcoesJuiz(List<String> opcoesJuiz) {
        this.opcoesJuiz = opcoesJuiz;
    }

    
    
    
   public String visualizar(long id){
       processo = ProcessoFacade.buscarProcesso(id);
       return "/juiz/encaminhamento.xhtml";
   }
   
   public String visualizarParte(long id){
       processo = ProcessoFacade.buscarProcesso(id);
       return "/parte/detalhesProcesso.xhtml";
   }
}

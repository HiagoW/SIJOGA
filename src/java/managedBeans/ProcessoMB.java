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
public class ProcessoMB implements Serializable {
    
    private Processo processo;
    private List<String> opcoesJuiz;
    private List<FaseProcesso> opcoesAdvogado;
    private String acaoJuiz;
    private String justificativa;
    private FaseProcesso faseAdv;
    
    public ProcessoMB() {
    }
    
    @PostConstruct
    public void init(){
        Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("usuarioLogado");
        if(usuario.getTipo().getDescricao().equals("Advogado")){
            opcoesAdvogado = FaseProcessoFacade.buscarFasesAdv();
        }
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
        FacesMessage mensagem = null;
        switch(acaoJuiz){
            case "Aceitar Pedido":
                mensagem = new FacesMessage("Pedido aceito","");
                faseProcesso = FaseProcessoFacade.buscarFase("Aceito");
                break;
            case "Negar Pedido":
                fase.setResposta(justificativa);
                mensagem = new FacesMessage("Pedido negado","");
                faseProcesso = FaseProcessoFacade.buscarFase("Negado");
                break;
            case "Intimação":
                faseProcesso = FaseProcessoFacade.buscarFase("Intimacao");
                break;
            case "Encerrar":
                fase.setResposta(justificativa);
                mensagem = new FacesMessage("Processo encerrado","");
                faseProcesso = FaseProcessoFacade.buscarFase("Encerramento");
                break;
            default:
                faseProcesso = FaseProcessoFacade.buscarFase("Informativa");
                break;
        }
        mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
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
   
   public String visualizarAdvogado(long id){
       processo = ProcessoFacade.buscarProcesso(id);
       return "/advogado/detalhesProcesso.xhtml";
   }
   
   public String visualizarParte(long id){
       processo = ProcessoFacade.buscarProcesso(id);
       return "/parte/detalhesProcesso.xhtml";
   }

    public List<FaseProcesso> getOpcoesAdvogado() {
        return opcoesAdvogado;
    }

    public void setOpcoesAdvogado(List<FaseProcesso> opcoesAdvogado) {
        this.opcoesAdvogado = opcoesAdvogado;
    }

    public FaseProcesso getFaseAdv() {
        return faseAdv;
    }

    public void setFaseAdv(FaseProcesso fase) {
        this.faseAdv = fase;
    }
   
    public String criaFase(){
        Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("usuarioLogado");
        ProcessoFase fase = new ProcessoFase();
        fase.setData(new Date());
        fase.setProcesso(processo);
        fase.setResponsavel(usuario);
        fase.setFase(faseAdv);
        fase.setResposta(justificativa);
        FacesMessage mensagem = new FacesMessage("Fase criada","");
        mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
        ProcessoFaseFacade.criarFase(fase);
        return "/advogado/home.xhtml";
    }
   
}

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
public class ProcessosMB implements Serializable {
    
    private List<Processo> processos = new ArrayList<>();
    private List<Processo> processosParte = new ArrayList<>();
    private List<Processo> processosPromovente = new ArrayList<>();
    private List<Processo> processosPromovido = new ArrayList<>();
    private long processosAtivos = 0;
    private long processosAguardandoIntimacao = 0;
    private long processosEncerrados = 0;
    
    public ProcessosMB() {
    }
    
    @PostConstruct
    public void init(){
        Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("usuarioLogado");
        if(usuario.getTipo().getId()==1){
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
        }else if(usuario.getTipo().getId()==2){
            processosPromovente = ProcessoFacade.buscaProcessosAdvPromovente(usuario);
            processosPromovido = ProcessoFacade.buscaProcessosAdvPromovido(usuario);
            /*processosAtivos = ProcessoFacade.processosAtivos(usuario);*/
        }else if(usuario.getTipo().getId()==3){
            processosParte = ProcessoFacade.buscaProcessosParte(usuario);
        }
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

    public List<Processo> getProcessosParte() {
        return processosParte;
    }

    public void setProcessosParte(List<Processo> processosParte) {
        this.processosParte = processosParte;
    }
    
    
}

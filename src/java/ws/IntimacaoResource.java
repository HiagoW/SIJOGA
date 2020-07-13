/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import DTOs.ExecucaoIntimacaoDTO;
import beans.Processo;
import beans.ProcessoFase;
import beans.FaseProcesso;
import beans.Usuario;
import facade.FaseProcessoFacade;
import facade.ProcessoFacade;
import facade.ProcessoFaseFacade;
import java.util.Date;

/**
 * REST Web Service
 *
 * @author hiago
 */
@Path("intimacao")
public class IntimacaoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of IntimacaoResource
     */
    public IntimacaoResource() {
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postIntimacao(ExecucaoIntimacaoDTO intimacao) {
        if(criaFase(intimacao)){
            return Response.ok().build();
        }else{
            return Response.noContent().build();
        }
    }
    
    public boolean criaFase(ExecucaoIntimacaoDTO intimacao){
        Processo processo = ProcessoFacade.buscarProcesso(intimacao.getIdProcesso());
        if(processo == null){
            return false;
        }
        ProcessoFase faseAtual = processo.getFaseAtual();
        Usuario usuario = faseAtual.getResponsavel();
        ProcessoFase fase = new ProcessoFase();
        fase.setData(new Date());
        fase.setProcesso(processo);
        fase.setResponsavel(usuario);
        FaseProcesso faseProcesso = FaseProcessoFacade.buscarFase("Informativa");
        fase.setFase(faseProcesso);
        String desc = "Status da intimação: " + intimacao.getStatus() + "; Data: " + intimacao.getData();
        fase.setResposta(desc);
        ProcessoFaseFacade.criarFase(fase);
        return true;
    }
}

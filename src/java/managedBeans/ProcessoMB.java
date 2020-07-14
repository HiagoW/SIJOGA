/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import DTOs.DadosIntimacaoDTO;
import beans.FaseProcesso;
import DTOs.OficialDTO;
import beans.Processo;
import beans.ProcessoFase;
import beans.Usuario;
import facade.FaseProcessoFacade;
import facade.LoginFacade;
import facade.ProcessoFacade;
import facade.ProcessoFaseFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonObject;
import javax.servlet.http.Part;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
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
    private Part arquivo; 
    private Long oficial;
    private String nomeOficial;
    private List<OficialDTO> oficiais = new ArrayList<>();
    private Usuario intimado;
    private List<Usuario> partes = new ArrayList<>();
    
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
        if(acaoJuiz.equals("Intimação")){
            return redirectIntimacao();
        }
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
        return "/juiz/home.xhtml?faces-redirect=true";
    }
    
    public String redirectIntimacao(){
        Client client = ClientBuilder.newClient();
         Response resp = client
                 .target("http://localhost:8080/SOSIFOD/webresources/lista")
                 .request(MediaType.APPLICATION_JSON)
                 .get();
        
         if(resp.getStatus() < 200 || resp.getStatus() > 299){
             FacesMessage mensagem = new FacesMessage("Erro ao buscar lista de oficiais","");
             mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
                return "/juiz/home.xhtml?faces-redirect=true";
         }
         
        oficiais = resp.readEntity(new GenericType<List<OficialDTO>>(){});
        
        partes.add(processo.getPromovente());
        partes.add(processo.getPromovido());
        return "/juiz/intima.xhtml";
    }
    
    public String intimar(){
        for(OficialDTO o : oficiais){
            if(o.getId() == oficial){
                nomeOficial = o.getNome();
                break;
            }
        }
        
        DadosIntimacaoDTO dadosIntimacaoDTO = new DadosIntimacaoDTO();
        
        
        dadosIntimacaoDTO.setIdProcesso(processo.getId());
        dadosIntimacaoDTO.setIdOficial(oficial);
        dadosIntimacaoDTO.setNomeIntimado(intimado.getNome());
        dadosIntimacaoDTO.setCpfIntimado(intimado.getCpf());
        dadosIntimacaoDTO.setEndereco(intimado.getEndereco());
        dadosIntimacaoDTO.setCidade(intimado.getCidade().getNome());
        dadosIntimacaoDTO.setEstado(intimado.getCidade().getEstado().getSigla());
        
        Client client = ClientBuilder.newClient();
        
        Response resp = client
                 .target("http://localhost:8080/SOSIFOD/webresources/intimacao")
                 .request(MediaType.APPLICATION_JSON)
                 .post(Entity.json(dadosIntimacaoDTO));
        
        if(resp.getStatus() < 200 || resp.getStatus() > 299){
             FacesMessage mensagem = new FacesMessage("Erro ao retornar intimação para SOSIFOD","");
             mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
                return "/juiz/home.xhtml?faces-redirect=true";
         }
        
        Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("usuarioLogado");
        ProcessoFase fase = new ProcessoFase();
        fase.setData(new Date());
        fase.setProcesso(processo);
        fase.setResponsavel(usuario);
        FaseProcesso faseProcesso = FaseProcessoFacade.buscarFase("Intimacao");
        FacesMessage mensagem = new FacesMessage("Intimação solicitada","");
        fase.setResposta("Oficial designado: " + nomeOficial + "; Intimado: " + intimado.getNome());
        mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
        fase.setFase(faseProcesso);
        ProcessoFaseFacade.criarFase(fase);
        
        return "/juiz/home.xhtml?faces-redirect=true";
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
        String arq = null;
        try{
            if(arquivo != null){
                arq = importar();
            }
        }catch(IOException e){
                FacesMessage mensagem = new FacesMessage("Erro no upload do arquivo","");
                mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
                return "/advogado/detalhesProcesso.xhtml";
            }
        Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("usuarioLogado");
        ProcessoFase fase = new ProcessoFase();
        if(arq!=null){
            fase.setArquivo(arq);
        }
        fase.setData(new Date());
        fase.setProcesso(processo);
        fase.setResponsavel(usuario);
        fase.setFase(faseAdv);
        fase.setResposta(justificativa);
        FacesMessage mensagem = new FacesMessage("Fase criada","");
        mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
        ProcessoFaseFacade.criarFase(fase);
        return "/advogado/home.xhtml?faces-redirect=true";
    }
    
    public String importar() throws IOException{
                    InputStream input = arquivo.getInputStream();
			String fileName = processo.getId().toString()+"_"+String.valueOf(System.currentTimeMillis())+"_"+arquivo.getSubmittedFileName();
                        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/arquivos");
                        String[] sPath = path.split(Pattern.quote(File.separator));
                        path = "";
                        for(String p : sPath){
                            path += p+"\\";
                            if(p.equals("SIJOGA")){
                                break;
                            }
                        }
                        path += "web\\arquivos";
                        
                        File file = new File(path,fileName);
	           Files.copy(input, file.toPath());
                   input.close();
                   return file.toPath().toString();
    }

    public Part getArquivo() {
        return arquivo;
    }

    public void setArquivo(Part arquivo) {
        this.arquivo = arquivo;
    }
    
    public void download(String arquivo, Date data) throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        
        ec.responseReset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
        ec.setResponseContentType(FacesContext.getCurrentInstance().getExternalContext().getMimeType(arquivo)); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ExternalContext#getMimeType() for auto-detection based on filename.
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + processo.getId().toString().replace(" ", "_").replace(".","_") + "_" + data.toString() + "." + arquivo.split("\\.")[1] + "\""); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.

        OutputStream output = ec.getResponseOutputStream();
        Files.copy(Paths.get(arquivo), output);
        fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
    }

    public Long getOficial() {
        return oficial;
    }

    public void setOficial(Long oficial) {
        this.oficial = oficial;
    }

    public String getNomeOficial() {
        return nomeOficial;
    }

    public void setNomeOficial(String nomeOficial) {
        this.nomeOficial = nomeOficial;
    }

    

    public List<OficialDTO> getOficiais() {
        return oficiais;
    }

    public void setOficiais(List<OficialDTO> oficiais) {
        this.oficiais = oficiais;
    }

    

    public Usuario getIntimado() {
        return intimado;
    }

    public void setIntimado(Usuario intimado) {
        this.intimado = intimado;
    }

    public List<Usuario> getPartes() {
        return partes;
    }

    public void setPartes(List<Usuario> partes) {
        this.partes = partes;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.Cidade;
import beans.Estado;
import beans.Processo;
import beans.Usuario;
import facade.CadastroFacade;
import facade.EstadoFacade;
import facade.LoginFacade;
import facade.ProcessoFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import util.SessionContext;

/**
 *
 * @author hiago
 */
@Named
@ViewScoped
public class PerfilMB implements Serializable {
    
    private String email;
    private String nome;
    private String senha;
    private String senha2;
    private Estado estado;
    private Cidade cidade;
    private String cpf;
    private List<Estado> estados;
    private List<Cidade> cidades;
    private Usuario usuario;
    private String endereco;
    
    public PerfilMB() {
    }
    
    @PostConstruct
    public void init(){
        usuario = (Usuario) SessionContext.getInstance().getAttribute("usuarioLogado");
        email = usuario.getEmail();
        nome = usuario.getNome();
        endereco = usuario.getEndereco();
        cidade = usuario.getCidade();
        estado = cidade.getEstado();
        estados = EstadoFacade.buscarEstados();
        cidades = estado.getCidades();
        cpf = usuario.getCpf();
    }
    
    public void atualizaCidades(){
        cidades = estado.getCidades();
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha2() {
        return senha2;
    }

    public void setSenha2(String senha2) {
        this.senha2 = senha2;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    
    
    public String alterarPerfil(){
        if(!email.equals(usuario.getEmail())){
            long resultado = CadastroFacade.verificaEmail(email);
            if(resultado!=0){
                System.out.println("Email ja cadastrado");
                switch(usuario.getTipo().getDescricao()){
                    case "Juiz":
                        return "/juiz/home.xhtml";
                    case "Advogado":
                        return "/advogado/home.xhtml";
                    case "Parte":
                        return "/parte/home.xhtml";
                }
            }
        }
        MessageDigest m;
        try {
            if(!this.senha.isEmpty()){
                m = MessageDigest.getInstance("MD5");            
                m.update(this.senha.getBytes(),0,this.senha.length());
                String senhaC = new BigInteger(1,m.digest()).toString(16);
                usuario.setSenha(senhaC);
            }
            usuario.setEmail(email);
            usuario.setCpf(cpf);
            usuario.setNome(nome);
            usuario.setCidade(cidade);
            usuario.setEndereco(endereco);
            CadastroFacade.alterar(usuario);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PerfilMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch(usuario.getTipo().getDescricao()){
            case "Juiz":
                return "/juiz/profile.xhtml";
            case "Advogado":
                return "/advogado/profile.xhtml";
            case "Parte":
                return "/parte/profile.xhtml";
            default:
                return "/login.xhtml";
        }
    }
}

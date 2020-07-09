/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.Cidade;
import beans.Estado;
import beans.Processo;
import beans.TipoUsuario;
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
public class CadastroMB implements Serializable {
    
    private String email;
    private String cpf;
    private String nome;
    private String senha;
    private String senha2;
    private TipoUsuario tipo;
    private Estado estado;
    private Cidade cidade;
    private String endereco;
    private List<TipoUsuario> tipos;
    private List<Estado> estados;
    private List<Cidade> cidades;
    
    @PostConstruct
    public void init(){
        tipos = CadastroFacade.buscarTipos();
        estados = EstadoFacade.buscarEstados();
        cidades = estados.get(0).getCidades();
    }
    
    public void atualizaCidades(){
        cidades = estado.getCidades();
    }
    public CadastroMB() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public List<TipoUsuario> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoUsuario> tipos) {
        this.tipos = tipos;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
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
    
    
    
    public String cadastrar(){
        long resultado = CadastroFacade.verificaEmail(email);
        if(resultado!=0){
            System.out.println("Email ja cadastrado");
            return "login.xhtml";
        }
        Usuario usuario = new Usuario();
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");            
            m.update(this.senha.getBytes(),0,this.senha.length());
            String senhaC = new BigInteger(1,m.digest()).toString(16);
            usuario.setSenha(senhaC);
            usuario.setCpf(cpf);
            usuario.setEmail(email);
            usuario.setNome(nome);
            usuario.setTipo(tipo);
            usuario.setCidade(cidade);
            usuario.setEndereco(endereco);
            CadastroFacade.cadastrar(usuario);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CadastroMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "login.xhtml";
    }
    
    public String cadastrarParte(){
        long resultado = CadastroFacade.verificaEmail(email);
        if(resultado!=0){
            System.out.println("Email ja cadastrado");
            return "/advogado/cadastroParte.xhtml";
        }
        Usuario usuario = new Usuario();
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");            
            m.update(this.senha.getBytes(),0,this.senha.length());
            String senhaC = new BigInteger(1,m.digest()).toString(16);
            usuario.setSenha(senhaC);
            usuario.setCpf(cpf);
            usuario.setEmail(email);
            usuario.setNome(nome);
            for(TipoUsuario t : tipos){
                if(t.getDescricao().equals("Parte")){
                    usuario.setTipo(t);
                    break;
                }
            }
            usuario.setCidade(cidade);
            usuario.setEndereco(endereco);
            CadastroFacade.cadastrar(usuario);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CadastroMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/advogado/home.xhtml";
    }
}

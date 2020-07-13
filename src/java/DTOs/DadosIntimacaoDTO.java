/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author hiago
 */
public class DadosIntimacaoDTO implements Serializable{
    private long idProcesso;
    private long idOficial;
    private String nomeIntimado;
    private String cpfIntimado;
    private String endereco;
    private String cidade;
    private String estado;

    public long getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(long idProcesso) {
        this.idProcesso = idProcesso;
    }

    public long getIdOficial() {
        return idOficial;
    }

    public void setIdOficial(long idOficial) {
        this.idOficial = idOficial;
    }

    public String getNomeIntimado() {
        return nomeIntimado;
    }

    public void setNomeIntimado(String nomeIntimado) {
        this.nomeIntimado = nomeIntimado;
    }

    public String getCpfIntimado() {
        return cpfIntimado;
    }

    public void setCpfIntimado(String cpfIntimado) {
        this.cpfIntimado = cpfIntimado;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    

    
    
    
    
}

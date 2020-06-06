/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author hiago
 */
@Entity
@Table(name = "processo_fase")
@SequenceGenerator(name = "seq_processo_fase", sequenceName = "processo_fase_id_seq")
public class ProcessoFase implements Comparable<ProcessoFase>{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_processo_fase")
    Long id;
    
    @ManyToOne()
    @JoinColumn(name = "processo")
    Processo processo;
    @ManyToOne
    @JoinColumn(name = "responsavel")
    Usuario responsavel;
    @ManyToOne
    @JoinColumn(name = "fase")
    FaseProcesso fase;
    Date data;
    String arquivo;
    String resposta;
    Long oficial;
    public ProcessoFase() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public FaseProcesso getFase() {
        return fase;
    }

    public void setFase(FaseProcesso fase) {
        this.fase = fase;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Long getOficial() {
        return oficial;
    }

    public void setOficial(Long oficial) {
        this.oficial = oficial;
    }
    
    @Override
    public int compareTo(ProcessoFase o) {
        return getData().compareTo(o.getData());
    }
}

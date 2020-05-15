/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author hiago
 */
@Entity
@Table(name = "processos")
@SequenceGenerator(name = "seq_processo", sequenceName = "processos_id_seq")
public class Processo implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_processo")
    Long id;
    @ManyToOne
    @JoinColumn(name = "juiz")
    Usuario juiz;
    @ManyToOne
    @JoinColumn(name = "advogadoPromovente")
    Usuario advogadoPromovente;
    @ManyToOne
    @JoinColumn(name = "promovente")
    Usuario promovente;
    @ManyToOne
    @JoinColumn(name = "advogadoPromovido")
    Usuario advogadoPromovido;
    @ManyToOne
    @JoinColumn(name = "promovido")
    Usuario promovido;

    public Processo() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getJuiz() {
        return juiz;
    }

    public void setJuiz(Usuario juiz) {
        this.juiz = juiz;
    }

    public Usuario getAdvogadoPromovente() {
        return advogadoPromovente;
    }

    public void setAdvogadoPromovente(Usuario advogadoPromovente) {
        this.advogadoPromovente = advogadoPromovente;
    }

    public Usuario getPromovente() {
        return promovente;
    }

    public void setPromovente(Usuario promovente) {
        this.promovente = promovente;
    }

    public Usuario getAdvogadoPromovido() {
        return advogadoPromovido;
    }

    public void setAdvogadoPromovido(Usuario advogadoPromovido) {
        this.advogadoPromovido = advogadoPromovido;
    }

    public Usuario getPromovido() {
        return promovido;
    }

    public void setPromovido(Usuario promovido) {
        this.promovido = promovido;
    }
    
    
}

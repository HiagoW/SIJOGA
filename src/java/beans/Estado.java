/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author hiago
 */
@Entity
@Table(name = "estados")
@SequenceGenerator(name = "seq_estados", sequenceName = "estados_id_seq")
public class Estado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_estados")
    private Long id;
    private String nome;
    private String sigla;
    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cidade> cidades;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

   
    
    @Override
    public boolean equals(Object e) {
        return (this.sigla.equalsIgnoreCase(((Estado)e).getSigla()));
    }

    @Override
    public int hashCode() {
        return this.sigla.hashCode();
    }
}

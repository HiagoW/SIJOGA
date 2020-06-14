/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author hiago
 */
@Entity
@Table(name = "tipos_usuarios")
@SequenceGenerator(name = "seq_tipo_usuario", sequenceName = "tipos_usuarios_id_seq", initialValue=4)
public class TipoUsuario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tipo_usuario")
    private Long id;
    private String descricao;
    @OneToMany(mappedBy = "tipo", fetch = FetchType.LAZY)
    private List<Usuario> usuarios;

    public TipoUsuario() {
    }
    
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    @Override
    public boolean equals(Object e) {
        return (this.descricao.equalsIgnoreCase(((TipoUsuario)e).getDescricao()));
    }

    @Override
    public int hashCode() {
        return this.descricao.hashCode();
    }
    
}

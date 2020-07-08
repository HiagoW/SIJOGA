/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import beans.TipoUsuario;
import beans.Cidade;
import dao.CadastroDAO;
import dao.CidadeDAO;
import java.util.List;
import org.hibernate.NonUniqueResultException;

/**
 *
 * @author hiago
 */
public class CidadeFacade {
    
    static CidadeDAO cidadeDAO = new CidadeDAO();
    
    public static Cidade buscarCidade(Long id){
        return cidadeDAO.buscarCidade(id);
    }
}

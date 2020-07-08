/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import beans.TipoUsuario;
import beans.Estado;
import dao.CadastroDAO;
import dao.EstadoDAO;
import java.util.List;
import org.hibernate.NonUniqueResultException;

/**
 *
 * @author hiago
 */
public class EstadoFacade {
    
    static EstadoDAO estadoDAO = new EstadoDAO();
    
    public static Estado buscarEstado(String sigla){
        return estadoDAO.buscarEstado(sigla);
    }
    
    public static List<Estado> buscarEstados(){
        return estadoDAO.buscarEstados();
    }
    
}

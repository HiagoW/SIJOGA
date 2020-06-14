/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import beans.TipoUsuario;
import beans.Usuario;
import dao.CadastroDAO;
import java.util.List;
import org.hibernate.NonUniqueResultException;

/**
 *
 * @author hiago
 */
public class CadastroFacade {
    
    static CadastroDAO cadastroDAO = new CadastroDAO();
    public static long verificaEmail(String email) throws NonUniqueResultException{
        return cadastroDAO.verificaEmail(email);
    }
    
    public static void cadastrar(Usuario usuario) {
        cadastroDAO.cadastrar(usuario);
    }
    
    public static List<TipoUsuario> buscarTipos(){
        return cadastroDAO.buscarTipos();
    }
    
    public static TipoUsuario buscarTipo(String desc){
        return cadastroDAO.buscarTipo(desc);
    }
    
    public static void alterar(Usuario usuario){
        cadastroDAO.alterar(usuario);
    }
}

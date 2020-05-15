/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import beans.Usuario;
import dao.CadastroDAO;
import org.hibernate.NonUniqueResultException;

/**
 *
 * @author hiago
 */
public class LoginFacade {
    
    static CadastroDAO cadastroDAO = new CadastroDAO();
    public static Usuario buscarLogin(String email, String senha) throws NonUniqueResultException{
        return cadastroDAO.buscarLogin(email, senha);
    }
}

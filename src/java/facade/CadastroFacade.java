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
public class CadastroFacade {
    
    static CadastroDAO cadastroDAO = new CadastroDAO();
    public static long verificaEmail(String email) throws NonUniqueResultException{
        return cadastroDAO.verificaEmail(email);
    }
}

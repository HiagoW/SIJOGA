/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author hiago
 */
public class CadastroDAO {

    public CadastroDAO() {
    }
    
    public Usuario buscarLogin(String email, String senha){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("from Usuario where email = :email and senha = :senha");
        query.setParameter("email", email);
        query.setParameter("senha", senha);
        List<Usuario> results = query.list();
        Usuario usuario = null;
        for(Usuario result : results){
            usuario = result;
            break;
        }
        session.close();
        
        
        return usuario;
    }
}

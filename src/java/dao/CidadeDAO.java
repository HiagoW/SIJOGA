/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.TipoUsuario;
import beans.Cidade;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author hiago
 */
public class CidadeDAO {

    public CidadeDAO() {
    }
    
    public Cidade buscarCidade(Long id){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("from Cidade where id = :id");
        query.setParameter("id", id);
        
        Cidade cidade = (Cidade) query.uniqueResult();
        session.close();
        
        return cidade;
    }
}

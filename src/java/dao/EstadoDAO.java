/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.TipoUsuario;
import beans.Estado;
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
public class EstadoDAO {

    public EstadoDAO() {
    }
    
    public Estado buscarEstado(String sigla){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("from Estado where sigla = :sigla");
        query.setParameter("sigla", sigla);
        
        Estado estado  = (Estado) query.uniqueResult();
        session.close();
        
        return estado;
    }
    
    public List<Estado> buscarEstados(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("from Estado");
        
        List<Estado> estados = query.list();
        session.close();
        
        return estados;
    }
}

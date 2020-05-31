/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Processo;
import beans.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author hiago
 */
public class ProcessoDAO {

    public ProcessoDAO() {
    }
    
    public List<Processo> buscarProcessos(Usuario juiz) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("from Processo where juiz = :juiz ");
        query.setParameter("juiz", juiz);
        
        List<Processo> processos = query.list();
        session.close();
        
        return processos;
    }
    
    public long processosAtivos(Usuario juiz){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("select count(*) from Processo where juiz = :juiz and status = 1");
        query.setParameter("juiz", juiz);
        
        long total = (long) query.uniqueResult();
        session.close();
        
        return total;
    }
}

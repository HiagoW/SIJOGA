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
    
    public List<Processo> buscarProcessosAdvPromovente(Usuario advogado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("from Processo where advogadoPromovente = :advogado ");
        query.setParameter("advogado", advogado);
        
        List<Processo> processosPromovente = query.list();
        session.close();
        
        return processosPromovente;
    }
    
    public List<Processo> buscarProcessosAdvPromovido(Usuario advogado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("from Processo where advogadoPromovido = :advogado ");
        query.setParameter("advogado", advogado);
        
        List<Processo> processosPromovido = query.list();
        session.close();
        
        return processosPromovido;
    }
    
    public Processo buscarProcesso(long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery(" from Processo where id = :id");
        query.setParameter("id", id);
        
        Processo processo  = (Processo) query.uniqueResult();
        session.close();
        
        return processo;
    }
}

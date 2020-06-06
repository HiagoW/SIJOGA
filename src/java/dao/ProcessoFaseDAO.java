/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Processo;
import beans.ProcessoFase;
import beans.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author hiago
 */
public class ProcessoFaseDAO {

    public ProcessoFaseDAO() {
    }
    
    public ProcessoFase buscarFaseAtual(Processo processo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("from ProcessoFase where processo = :processo order by data desc");
        query.setMaxResults(1);
        query.setParameter("processo", processo);
        
        ProcessoFase processoFase = (ProcessoFase) query.uniqueResult();
        session.close();
        
        return processoFase;
    }
    
    public void criarFase(ProcessoFase fase){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Processo p = fase.getProcesso();
        session.merge(p);
        session.flush();
        fase.setProcesso(p);
        session.persist(fase);
        session.getTransaction().commit();
        session.close();
    }
}

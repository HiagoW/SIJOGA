/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Processo;
import beans.FaseProcesso;
import beans.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author hiago
 */
public class FaseProcessoDAO {

    public FaseProcessoDAO() {
    }
    
    public FaseProcesso buscarFase(String descricao) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("from FaseProcesso where descricao = :descricao");
        query.setParameter("descricao", descricao);
        
        FaseProcesso fase = (FaseProcesso) query.uniqueResult();
        session.close();
        
        return fase;
    }
    
}

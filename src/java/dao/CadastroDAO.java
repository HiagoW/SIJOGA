/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.TipoUsuario;
import beans.Usuario;
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
public class CadastroDAO {

    public CadastroDAO() {
    }
    
    public Usuario buscarLogin(String email, String senha) throws NonUniqueResultException{
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("from Usuario where email = :email and senha = :senha");
        query.setParameter("email", email);
        query.setParameter("senha", senha);
        
        try{
            Usuario usuario = (Usuario) query.uniqueResult();
            return usuario;
        }catch(NonUniqueResultException e){
            throw e;
        }finally{
            session.close();
        }
    }
    
    public long verificaEmail(String email){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("select count(*) from Usuario where email = :email");
        query.setParameter("email", email);
        
            long resultado = (long) query.uniqueResult();
        
        
            session.close();
            
            return resultado;
    }
    
    public void cadastrar(Usuario usuario){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(usuario);
        session.getTransaction().commit();
        session.close();
    }
    
    public void alterar(Usuario usuario){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Usuario merged = (Usuario) session.merge(usuario);
        session.saveOrUpdate(merged);
        session.getTransaction().commit();
        session.close();
    }
    
    public List<TipoUsuario> buscarTipos(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("from TipoUsuario where id=1 or id=2");
        
        List<TipoUsuario> tipos = query.list();
        session.close();
        
        return tipos;
    }
    
    public TipoUsuario buscarTipo(String descricao){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("from TipoUsuario where descricao = :descricao");
        query.setParameter("descricao", descricao);
        
        TipoUsuario tipoUsuario = (TipoUsuario) query.uniqueResult();
        session.close();
        
        return tipoUsuario;
    }
    
    public Usuario buscarUsuario(String email){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("from Usuario where email = :email");
        query.setParameter("email", email);
        
        Usuario usuario = (Usuario) query.uniqueResult();
        session.close();
        
        return usuario;
    }
    
    public List<Usuario> buscarPartes(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("from Usuario where tipo.id = 3");
        
        List<Usuario> usuarios = query.list();
        session.close();
        
        return usuarios;
    }
    
    public Usuario buscarAdvogadoAleatorio(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("from Usuario where tipo.id = 2");
        List<Usuario> usuarios = query.list();
        int tamanho = usuarios.size();
        //Gerar numero aleatório
        int randomNum = ThreadLocalRandom.current().nextInt(0, tamanho);
        
        Usuario usuario = usuarios.get(randomNum);
        session.close();
        
        return usuario;
    }
    
    //DESENVOLVER A LOGICA
    public Usuario buscarJuizComMenosProcessos(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query = session.createQuery("select u from Usuario u, Processo p where u.tipo.descricao='Juiz' and u = p.juiz group by(u) order by count(p) asc");
        query.setMaxResults(1);
        Usuario usuario = (Usuario) query.uniqueResult();
        session.close();
        
        return usuario;
    }
}

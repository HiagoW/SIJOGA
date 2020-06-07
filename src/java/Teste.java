
import beans.FaseProcesso;
import beans.Processo;
import beans.ProcessoFase;
import beans.Usuario;
import java.util.Date;
import org.hibernate.Session;
import util.HibernateUtil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hiago
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        
        Usuario juiz = (Usuario) session.get(Usuario.class, Long.valueOf("1"));
        Usuario promovente = (Usuario) session.get(Usuario.class, Long.valueOf("2"));
        Usuario advogadoPromovente = (Usuario) session.get(Usuario.class, Long.valueOf("3"));
        Usuario advogadoPromovido = (Usuario) session.get(Usuario.class, Long.valueOf("4"));
        Usuario promovido = (Usuario) session.get(Usuario.class, Long.valueOf("5"));
        
        Processo processo = new Processo();
        processo.setJuiz(juiz);
        processo.setAdvogadoPromovente(advogadoPromovente);
        processo.setAdvogadoPromovido(advogadoPromovido);
        processo.setPromovente(promovente);
        processo.setPromovido(promovido);
        
        FaseProcesso faseProcesso = (FaseProcesso) session.get(FaseProcesso.class,Long.valueOf("1"));
        ProcessoFase processoFase = new ProcessoFase();
        processoFase.setData(new Date());
        processoFase.setProcesso(processo);
        processoFase.setResponsavel(advogadoPromovente);
        processoFase.setFase(faseProcesso);
        
        session.persist(processoFase);
        
        session.getTransaction().commit();
        session.close();
        
        HibernateUtil.getSessionFactory().close();
    }
    
}

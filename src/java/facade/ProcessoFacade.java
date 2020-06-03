/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import beans.Processo;
import beans.Usuario;
import dao.ProcessoDAO;
import java.util.List;

/**
 *
 * @author hiago
 */
public class ProcessoFacade {
    static ProcessoDAO processoDAO = new ProcessoDAO();
    public static List<Processo> buscaProcessos(Usuario juiz){
        return processoDAO.buscarProcessos(juiz);
    }
    
    public static long processosAtivos(Usuario juiz){
        return processoDAO.processosAtivos(juiz);
    }
    
    public static Processo buscarProcesso(long id){
        return processoDAO.buscarProcesso(id);
    } 
}

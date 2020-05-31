/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import beans.Processo;
import beans.ProcessoFase;
import beans.Usuario;
import dao.ProcessoDAO;
import dao.ProcessoFaseDAO;
import java.util.List;

/**
 *
 * @author hiago
 */
public class ProcessoFaseFacade {
    static ProcessoFaseDAO processoFaseDAO = new ProcessoFaseDAO();
    public static ProcessoFase buscarFaseAtual(Processo processo){
        return processoFaseDAO.buscarFaseAtual(processo);
    }
}

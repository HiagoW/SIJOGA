/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import beans.Processo;
import beans.FaseProcesso;
import beans.Usuario;
import dao.FaseProcessoDAO;
import dao.ProcessoDAO;
import dao.ProcessoFaseDAO;
import java.util.List;

/**
 *
 * @author hiago
 */
public class FaseProcessoFacade {
    static FaseProcessoDAO faseProcessoDAO = new FaseProcessoDAO();
    public static FaseProcesso buscarFase(String descricao){
        return faseProcessoDAO.buscarFase(descricao);
    }
    
    public static List<FaseProcesso> buscarFasesAdv(){
        return faseProcessoDAO.buscarFasesAdv();
    }
}

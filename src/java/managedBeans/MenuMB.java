/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.FaseProcesso;
import beans.Processo;
import beans.ProcessoFase;
import beans.Usuario;
import facade.FaseProcessoFacade;
import facade.LoginFacade;
import facade.ProcessoFacade;
import facade.ProcessoFaseFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import util.SessionContext;

/**
 *
 * @author hiago
 */
@Named
@RequestScoped
public class MenuMB implements Serializable {

    public MenuMB() {
    }
    
    public String perfil(){
        Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("usuarioLogado");
        switch(usuario.getTipo().getId().intValue()){
            case 1:
                return "/juiz/profile.xhtml";
            case 2:
                return "/advogado/profile.xhtml";
            default:
                return "login.xhtml";
        }
    }
    
    public String home(){
        Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("usuarioLogado");
        switch(usuario.getTipo().getId().intValue()){
            case 1:
                return "/juiz/home.xhtml";
            case 2:
                return "/advogado/home.xhtml";
            default:
                return "login.xhtml";
        }
    }
    
}

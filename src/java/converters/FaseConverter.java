/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import beans.FaseProcesso;
import facade.CadastroFacade;
import facade.FaseProcessoFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author hiago
 */
@FacesConverter(value = "faseConverter", forClass=beans.FaseProcesso.class)
public class FaseConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return FaseProcessoFacade.buscarFase(string);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((FaseProcesso) o).getDescricao();
    }
    
}

package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Condominio;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named(value = "converterCondominio")
@RequestScoped
public class ConverterCondominio implements Serializable, Converter {

    @PersistenceContext(unitName = "Modelo2_WebPU")
    protected EntityManager em;    
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Condominio.class, Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        if (t == null){
            return null;
        }
        Condominio obj = (Condominio) t;
        return obj.getId().toString();
    }

}
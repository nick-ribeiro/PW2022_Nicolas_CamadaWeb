package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Condominio;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class CondominioDAO<TIPO> extends DAOGenerico<Condominio> implements Serializable {
    
    public CondominioDAO() {
        super();
        classePersistente = Condominio.class;
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        ordemAtual = listaOrdem.get(1);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
    public Condominio getObjetcByID(Object id) throws Exception {
        Condominio obj = em.find(Condominio.class, id);
        obj.getUnidadesCondominais().size();
        return obj;
    }
}

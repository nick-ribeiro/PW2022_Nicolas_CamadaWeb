package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Aluguel;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class AluguelDAO<TIPO> extends DAOGenerico<Aluguel> implements Serializable {
    
    public AluguelDAO() {
        super();
        classePersistente = Aluguel.class;
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("valor", "Valor", "like"));
        ordemAtual = listaOrdem.get(1);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
    public Aluguel getObjetcByID(Object id) throws Exception {
        Aluguel obj = em.find(Aluguel.class, id);
        obj.getMensalidades().size();
        return obj;
    }
}

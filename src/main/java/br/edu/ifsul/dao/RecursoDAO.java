package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Recurso;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class RecursoDAO<TIPO> extends DAOGenerico<Recurso> implements Serializable {
    
    public RecursoDAO() {
        super();
        classePersistente = Recurso.class;
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("descricao", "Descricao", "like"));
        ordemAtual = listaOrdem.get(1);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
}

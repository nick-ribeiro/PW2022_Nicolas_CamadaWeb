package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.UnidadeCondominal;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class UnidadeCondominalDAO<TIPO> extends DAOGenerico<UnidadeCondominal> implements Serializable {
    
    public UnidadeCondominalDAO() {
        super();
        classePersistente = UnidadeCondominal.class;
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("numero", "Numero", "like"));
        ordemAtual = listaOrdem.get(1);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
}

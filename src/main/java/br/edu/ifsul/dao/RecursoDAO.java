package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Recurso;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class RecursoDAO<TIPO> extends DAOGenerico<Recurso> implements Serializable {
    
    public RecursoDAO() {
        super();
        classePersistente = Recurso.class;
    }
}

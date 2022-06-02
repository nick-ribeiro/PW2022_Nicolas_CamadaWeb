package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Condominio;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class CondominioDAO<TIPO> extends DAOGenerico<Condominio> implements Serializable {
    
    public CondominioDAO() {
        super();
        classePersistente = Condominio.class;
    }
}

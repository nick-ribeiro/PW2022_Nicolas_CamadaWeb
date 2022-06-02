package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Locatario;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class LocatarioDAO<TIPO> extends DAOGenerico<Locatario> implements Serializable {
    
    public LocatarioDAO() {
        super();
        classePersistente = Locatario.class;
    }
}

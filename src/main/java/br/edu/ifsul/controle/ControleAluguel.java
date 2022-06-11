package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AluguelDAO;
import br.edu.ifsul.dao.LocatarioDAO;
import br.edu.ifsul.dao.UnidadeCondominalDAO;
import br.edu.ifsul.modelo.Aluguel;
import br.edu.ifsul.modelo.Locatario;
import br.edu.ifsul.modelo.Mensalidades;
import br.edu.ifsul.modelo.UnidadeCondominal;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "controleAluguel")
@ViewScoped
public class ControleAluguel implements Serializable{
    
    @EJB
    private AluguelDAO<Aluguel> dao;
    private Aluguel objeto;
    
    @EJB
    private UnidadeCondominalDAO<UnidadeCondominal> daoUnidadeCondominal;
    
    @EJB
    private LocatarioDAO<Locatario> daoLocatario;
    
    private Mensalidades mensalidade;
    private Boolean novaMensalidade;

    public ControleAluguel() {
        
    }
    
    public void novaMensalidade() {
        mensalidade = new Mensalidades();
        novaMensalidade = true;
    }
    
    public void alterarMensalidade(int index) {
        mensalidade = objeto.getMensalidades().get(index);
        novaMensalidade = false;
    }
    
    public void salvarMensalidade() {
        if (novaMensalidade) {
            objeto.adicionarMensalidades(mensalidade);
        }
        Util.mensagemInformacao("Mensalidade adicionada ou alterada com sucesso!");
    }
    
    public void removerMensalide(int index) {
        objeto.removerMensalidades(index);
        Util.mensagemInformacao("Mensalidade removida com sucesso!");
    }
    
    public String listar() {
        return "/privado/aluguel/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Aluguel();
    }
    
    public void alterar(Object id) {
        try{
            objeto = dao.getObjetcByID(id);
        }catch(Exception e) {
            Util.mensagemInformacao("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void remover(Object id) {
        try{
            objeto = dao.getObjetcByID(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        }catch(Exception e) {
            Util.mensagemInformacao("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void salvar() {
        try{
            if(objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        }catch(Exception e) {
            Util.mensagemInformacao("Erro ao salvar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public AluguelDAO<Aluguel> getDao() {
        return dao;
    }

    public void setDao(AluguelDAO<Aluguel> dao) {
        this.dao = dao;
    }

    public Aluguel getObjeto() {
        return objeto;
    }

    public void setObjeto(Aluguel objeto) {
        this.objeto = objeto;
    }

    public UnidadeCondominalDAO<UnidadeCondominal> getDaoUnidadeCondominal() {
        return daoUnidadeCondominal;
    }

    public void setDaoUnidadeCondominal(UnidadeCondominalDAO<UnidadeCondominal> daoUnidadeCondominal) {
        this.daoUnidadeCondominal = daoUnidadeCondominal;
    }

    public LocatarioDAO<Locatario> getDaoLocatario() {
        return daoLocatario;
    }

    public void setDaoLocatario(LocatarioDAO<Locatario> daoLocatario) {
        this.daoLocatario = daoLocatario;
    }

    public Mensalidades getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(Mensalidades mensalidade) {
        this.mensalidade = mensalidade;
    }

    public Boolean getNovaMensalidade() {
        return novaMensalidade;
    }

    public void setNovaMensalidade(Boolean novaMensalidade) {
        this.novaMensalidade = novaMensalidade;
    }
    
}

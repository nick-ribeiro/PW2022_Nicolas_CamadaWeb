package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CondominioDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.dao.UnidadeCondominalDAO;
import br.edu.ifsul.modelo.Condominio;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.UnidadeCondominal;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "controleUnidadeCondominal")
@ViewScoped
public class ControleUnidadeCondominal implements Serializable{
    
    @EJB
    private UnidadeCondominalDAO<UnidadeCondominal> dao;
    private UnidadeCondominal objeto;
    
    @EJB
    private PessoaDAO<Pessoa> daoPessoa;
    
    @EJB
    private CondominioDAO<Condominio> daoCondominio;

    public ControleUnidadeCondominal() {
        
    }
    
    public String listar() {
        return "/privado/unidadeCondominal/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new UnidadeCondominal();
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
    
    public UnidadeCondominalDAO<UnidadeCondominal> getDao() {
        return dao;
    }

    public void setDao(UnidadeCondominalDAO<UnidadeCondominal> dao) {
        this.dao = dao;
    }

    public UnidadeCondominal getObjeto() {
        return objeto;
    }

    public void setObjeto(UnidadeCondominal objeto) {
        this.objeto = objeto;
    }

    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }

    public CondominioDAO<Condominio> getDaoCondominio() {
        return daoCondominio;
    }

    public void setDaoCondominio(CondominioDAO<Condominio> daoCondominio) {
        this.daoCondominio = daoCondominio;
    }
}

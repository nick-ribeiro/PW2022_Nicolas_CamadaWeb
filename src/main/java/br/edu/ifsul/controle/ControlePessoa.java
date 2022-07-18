package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PermissaoDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Permissao;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "controlePessoa")
@ViewScoped
public class ControlePessoa implements Serializable{
    
    @EJB
    private PessoaDAO<Pessoa> dao;
    private Pessoa objeto;
    
    @EJB
    private PermissaoDAO<Permissao> daoPermissao;
    private Permissao permissao;
    private int abaAtiva;

    public ControlePessoa() {
        
    }
    
    public void imprimePessoas(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatoriosPessoa", parametros, dao.getListaTodos());
    }
    
    public void imprimePessoa(Object id){
        try {
            objeto = dao.getObjetcByID(id);
            List<Pessoa> lista = new ArrayList<>();
            lista.add(objeto);
            HashMap parametros = new HashMap();
            UtilRelatorios.imprimeRelatorio("relatoriosPessoa", parametros,lista);            
        } catch (Exception e){
            Util.mensagemInformacao("Erro ao imprimir: " + Util.getMensagemErro(e));
        }
    } 
    
    public void removerPermissao(Permissao obj) {
        objeto.getPermissoes().remove(obj);
        Util.mensagemInformacao("Permissão removida com sucesso!");
    }
    
    public void adicionarPermissao() {
        if (!objeto.getPermissoes().contains(permissao)) {
            objeto.getPermissoes().add(permissao);
            Util.mensagemInformacao("Permissão adicionada com sucesso!");
        } else {
            Util.mensagemErro("Usuário já possui esta permissão");
        }
    }
    
    public String listar() {
        return "/privado/pessoa/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Pessoa();
        abaAtiva = 0;
    }
    
    public void alterar(Object id) {
        try{
            objeto = dao.getObjetcByID(id);
            abaAtiva = 0;
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
            if(objeto.getCpf() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        }catch(Exception e) {
            Util.mensagemInformacao("Erro ao salvar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public PessoaDAO<Pessoa> getDao() {
        return dao;
    }

    public void setDao(PessoaDAO<Pessoa> dao) {
        this.dao = dao;
    }

    public Pessoa getObjeto() {
        return objeto;
    }

    public void setObjeto(Pessoa objeto) {
        this.objeto = objeto;
    }

    public PermissaoDAO<Permissao> getDaoPermissao() {
        return daoPermissao;
    }

    public void setDaoPermissao(PermissaoDAO<Permissao> daoPermissao) {
        this.daoPermissao = daoPermissao;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public int getAbaAtiva() {
        return abaAtiva;
    }

    public void setAbaAtiva(int abaAtiva) {
        this.abaAtiva = abaAtiva;
    } 
}

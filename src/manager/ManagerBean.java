package manager;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import entity.Produto;
import persistence.ProdutoDao;

@ManagedBean(name = "mb")
@RequestScoped
public class ManagerBean {
	
	private Produto produto; // ENTRADE
	private List<Produto> produtos; // LISTA
	
	public ManagerBean() {
		this.produto = new Produto();
	}

	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	// LISTA POPULADA...
	public List<Produto> getProdutos() {
		try {
			produtos = new ProdutoDao().findAll();
		}
		catch (Exception ex) {
			
		}
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public void gravar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			new ProdutoDao().create(produto);
			this.produto = new Produto();
			fc.addMessage(null, new FacesMessage("Dados gravados com sucesso!"));
		}
		catch (Exception ex) {
			ex.printStackTrace();
			fc.addMessage(null, new FacesMessage("Erro! " + ex.getMessage()));
		}
	}
	
	public void excluir() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			new ProdutoDao().delete(produto.getId());
			this.produto = new Produto();
			fc.addMessage(null, new FacesMessage("Dados excluidos com sucesso!"));
		}
		catch (Exception ex) {
			ex.printStackTrace();
			fc.addMessage(null, new FacesMessage("Erro!" + ex.getLocalizedMessage()));
		}
	}
	
	public void alterar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			new ProdutoDao().update(produto);
			this.produto = new Produto();
			fc.addMessage(null, new FacesMessage("Dados alterados com sucesso!"));
		}
		 catch (Exception ex) {
			ex.printStackTrace();
			fc.addMessage(null, new FacesMessage("Erro!" + ex.getLocalizedMessage()));
		}
	}
	
	

}

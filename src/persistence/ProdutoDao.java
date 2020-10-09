package persistence;

import java.util.ArrayList;
import java.util.List;

import entity.Produto;

public class ProdutoDao extends Dao {
	
	public void create(Produto p) throws Exception {
		open();
		stmt = con.prepareStatement("insert into produto values (null,?,?,?)");
		stmt.setString(1, p.getNome());
		stmt.setString(2, p.getAutor());
		stmt.setDouble(3, p.getPreco());
		stmt.execute();
		close();
	}
	
	public List<Produto> findAll() throws Exception {
		open();
		stmt = con.prepareStatement("select * from produto");
		rs = stmt.executeQuery();
		List<Produto> lista = new ArrayList<Produto>();
		while(rs.next()) {
			Produto p = new Produto();
			p.setId(rs.getInt(1));
			p.setNome(rs.getString(2));
			p.setAutor(rs.getString(3));
			p.setPreco(rs.getDouble(4));
			lista.add(p);
		}
		close();
		return lista;
	}
	
	public Produto findByCode(Integer cod) throws Exception {
		open();
		stmt = con.prepareStatement("select * from produto where id = ?");
		stmt.setInt(1, cod);
		rs = stmt.executeQuery();
		Produto produto = null;
		if(rs.next()) {
			produto = new Produto(
					rs.getInt("id"),
					rs.getString("nome"),
					rs.getString("autor"),
					rs.getDouble("preco")
					);
		}
		close();
		return produto;
	}
	
	public void update(Produto p) throws Exception {
		open();
		stmt = con.prepareStatement("update produto set nome=?, autor=?, preco=? where id=?");
		stmt.setString(1, p.getNome());
		stmt.setString(2, p.getAutor());
		stmt.setDouble(3, p.getPreco());
		stmt.setInt(4, p.getId());
		stmt.execute();
		close();
	}
	
	public void delete(Integer cod) throws Exception {
		open();
		stmt = con.prepareStatement("delete from produto where id=?");
		stmt.setInt(1, cod);
		stmt.execute();
		close();
	}
	
	// PRA VER SE ESTÁ FUNCIONANDO...
	public static void main(String[] args) {

		try {
			Produto p = new Produto(null, "Psicologia", "Andreia", 120.);
			ProdutoDao dao = new ProdutoDao();
			dao.create(p);
			dao.findAll().stream().forEach(x -> {
				System.out.println(x.getNome() + ", " + x.getAutor() + ", " + x.getPreco());
			});
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		

	}

}

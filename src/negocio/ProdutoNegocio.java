package negocio;

import dao.ProdutoDAO;
import dominio.Produto;
import java.sql.SQLException;
import java.util.List;

public class ProdutoNegocio {
    
    private ProdutoDAO produtoDao = new ProdutoDAO();
    
    public List<Produto> searchAll() throws SQLException {
        return produtoDao.searchAll();
    }
    
    public List<Produto> searchByCategory(String categoria) throws SQLException{
        return produtoDao.searchByCategory(categoria);
    }

    public void insertProduct(Produto produto) throws SQLException {
        produtoDao.insertProduct(produto);
    }

    public int searchId(int codigo) throws SQLException {
        return produtoDao.searchId(codigo);
    }

}

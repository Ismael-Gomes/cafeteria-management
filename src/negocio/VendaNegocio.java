package negocio;

import dao.ProdutoDAO;
import dao.VendaDAO;
import dominio.Produto;
import dominio.Venda;

import java.sql.SQLException;
import java.sql.Timestamp;

public class VendaNegocio {
    private VendaDAO vendaDAO;
    private ProdutoDAO produtoDAO;

    public VendaNegocio() {
        this.vendaDAO = new VendaDAO();
        this.produtoDAO = new ProdutoDAO();
    }

    public void insertSale(int produtoId, int produtoCodigo, int quantidade) throws SQLException {
        Timestamp dataVenda = new Timestamp(System.currentTimeMillis());
        Venda venda = new Venda(0, produtoId, quantidade, dataVenda);
        if (produtoDAO.updateInventory(produtoCodigo, quantidade)){
            vendaDAO.insertSale(venda);
            System.out.println("\nComprado com Sucesso!");
        }
    }

}
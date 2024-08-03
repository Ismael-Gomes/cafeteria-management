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

    public void updateInventory(int produtoId, int quantidadeVendida) throws SQLException {
        produtoDao.updateInventory(produtoId, quantidadeVendida);
    }


    /*try {
        //listagem de produtos em sequencia.
            List<Produto> produtos = produtoNegocio.buscarPorCategoria("Lanche");
            for(Produto produto : produtos){
                GerenciadorDeProdutos gerenciadorProdutos = new GerenciadorDeProdutos();
                gerenciadorProdutos.exibirProdutosComSequencia();
            }

        //insert de produtos
            Produto novoProduto = new Produto(0,
                                                "Lanche",
                                                "Coxinha",
                                                "Coxinha de Frango",
                                                new BigDecimal("3.00"),
                                                30,
                                                new Timestamp(System.currentTimeMillis()));
            produtoNegocio.inserir(novoProduto);

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }*/

}

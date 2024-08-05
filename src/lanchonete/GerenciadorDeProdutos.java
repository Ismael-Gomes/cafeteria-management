package lanchonete;

import dominio.Produto;
import java.sql.SQLException;
import java.util.List;
import negocio.ProdutoNegocio;

public class GerenciadorDeProdutos {

    public void viewProductsSequenceLanche() {
        sequencia("Lanche");
    }

    public void viewProductsSequenceAcompanhamento() {
        sequencia("Acompanhamento");
    }

    public void viewProductsSequenceBebida() {
        sequencia("Bebida");
    }

    public boolean viewProducts(int codigo) {
        ProdutoNegocio produtoNegocio = new ProdutoNegocio();
        List<Produto> produtos;

        try {
            produtos = produtoNegocio.searchAll();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os produtos: " + e.getMessage());
            return false;
        }

        boolean encontrado = false;
        for (Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Produto não encontrado!");
            return false;
        }

    return true;

    }

    public void sequencia(String categoria){
        ProdutoNegocio produtoNegocio = new ProdutoNegocio();
        List<Produto> produtos;

        try {
            produtos = produtoNegocio.searchByCategory(categoria);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os produtos: " + e.getMessage());
            return;
        }

        int sequencia = 1;
        for (Produto produto : produtos) {
            System.out.println("\n#### Produto " + sequencia);
            System.out.println("=============================");
            System.out.println(produto.toString());
            sequencia++;
        }

    }

}

package dominio;

import java.sql.Timestamp;

public class Venda {
    
    private int id;
    private int produtoId;
    private int quantidade;
    private Timestamp dataVenda;

    public Venda(int id, int produtoId, int quantidade, Timestamp dataVenda) {
        this.id = id;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.dataVenda = dataVenda;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public int getProdutoId() {

        return produtoId;
    }

    public void setProdutoId(int produtoId) {

        this.produtoId = produtoId;
    }

    public int getQuantidade() {

        return quantidade;
    }

    public void setQuantidade(int quantidade) {

        this.quantidade = quantidade;
    }

    public Timestamp getDataVenda() {

        return dataVenda;
    }

    public void setDataVenda(Timestamp dataVenda) {

        this.dataVenda = dataVenda;
    }
    
    
}

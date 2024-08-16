package dominio;

import java.sql.Date;
import java.sql.Timestamp;

public class Venda {
    
    private int id;
    private int produtoId;
    private int quantidade;
    private Timestamp dataVenda;
    private String acao;
    private String detalhe;

    public Venda(int id, int produtoId, int quantidade, Timestamp dataVenda) {
        this.id = id;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.dataVenda = dataVenda;
    }

    public Venda(int produtoId, String acao, Timestamp dataVenda, String detalhe){
        this.produtoId = produtoId;
        this.acao = acao;
        this.dataVenda = dataVenda;
        this.detalhe = detalhe;
    }

    public String getAcao(){
        return acao;
    }

    public String getDetalhe(){
        return detalhe;
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

    @Override
    public String toString() {
        return  "#### ID do Produto: "+ produtoId +
                "\n#### Ação: " + acao +
                "\n#### Data da Venda: " + dataVenda +
                "\n#### Detalhes: \n" + detalhe +
                "\n=============================";
    }
    
}

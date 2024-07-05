package dominio;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Produto {
    private int id;
    private String categoria;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private int quantidade;
    private Timestamp dataCriacao;

    public Produto(int id, String categoria, String nome, String descricao, BigDecimal preco, int quantidade, Timestamp dataCriacao) {
        this.id = id;
        this.categoria = categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.dataCriacao = dataCriacao;
    }

    public String getCategoria() {

        return categoria;
    }

    public void setCategoria(String categoria) {

        this.categoria = categoria;
    }
    
    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getDescricao() {

        return descricao;
    }

    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }

    public BigDecimal getPreco() {

        return preco;
    }

    public void setPreco(BigDecimal preco) {

        this.preco = preco;
    }

    public int getQuantidade() {

        return quantidade;
    }

    public void setQuantidade(int quantidade) {

        this.quantidade = quantidade;
    }

    public Timestamp getDataCriacao() {

        return dataCriacao;
    }

    public void setDataCriacao(Timestamp dataCriacao) {

        this.dataCriacao = dataCriacao;
    }

    @Override
    public String toString() {
        return  "Nome: " + nome +
                "\nDescrição: " + descricao + 
                "\nPreço: " + preco +
                "\nEstoque: " + quantidade +
                "\n=============================";
    }
}
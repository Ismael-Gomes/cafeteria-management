package dominio;


public class Produto {
    private int id;
    private String categoria;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;
    private int codigo;

    public Produto(int id, int codigo, String categoria, String nome, String descricao, double preco, int quantidade) {
        this.id = id;
        this.categoria = categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.codigo = codigo;
    }

    public Produto(int codigo, String categoria, String nome, String descricao, double preco, int quantidade) {
        this.categoria = categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.codigo = codigo;
    }

    public Produto(int id, String nome, String descricao, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public Double getPreco() {

        return preco;
    }

    public void setPreco(Double preco) {

        this.preco = preco;
    }

    public int getQuantidade() {

        return quantidade;
    }

    public void setQuantidade(int quantidade) {

        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return  "#### Codigo: "+ codigo +
                "\n#### Nome: " + nome +
                "\n#### Descrição: " + descricao +
                "\n#### Preço: " + preco +
                "\n#### Estoque: " + quantidade +
                "\n=============================";
    }
}
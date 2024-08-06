package dominio;

public class Suporte {
    private String cpf_suporte;
    private String cpf_cliente;
    private String nome;
    private String descricao_problema;

    public Suporte(String cpf_suporte, String cpf_cliente, String nome, String descricao_problema) {
        this.cpf_suporte = cpf_suporte;
        this.cpf_cliente = cpf_cliente;
        this.nome = nome;
        this.descricao_problema = descricao_problema;
    }

    public String getCpf_suporte() {
        return cpf_suporte;
    }

    public void setCpf_suporte(String cpf_suporte) {
        this.cpf_suporte = cpf_suporte;
    }

    public String getCpf_cliente() {
        return cpf_cliente;
    }

    public void setCpf_cliente(String cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao_problema() {
        return descricao_problema;
    }

    public void setDescricao_problema(String descricao_problema) {
        this.descricao_problema = descricao_problema;
    }

}

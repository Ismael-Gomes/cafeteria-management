package dominio;

public class Funcionario {
    private String nome, email, senha, cpf, sitacao, numeroCelular;
    private int quantidadeVendas;
    double salario;

    public Funcionario(String nome, String email, String senha, String cpf, double salario, String numeroCelular, String sitacao, int quantidadeVendas) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.salario = salario;
        this.numeroCelular = numeroCelular;
        this.sitacao = sitacao;
        this.quantidadeVendas = quantidadeVendas;
    }

    public Funcionario(String nome, String email, String cpf, String sitacao, int quantidadeVendas, double salario, String numeroCelular) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.sitacao = sitacao;
        this.salario = salario;
        this.numeroCelular = numeroCelular;
        this.quantidadeVendas = quantidadeVendas;
    }

    public double getSalario(){
        return salario;
    }

    public String getNumeroCelular(){
        return numeroCelular;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSitacao() {
        return sitacao;
    }

    public int getQuantidadeVendas() {
        return quantidadeVendas;
    }
}

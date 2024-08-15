package dominio;

public class Funcionario {
    private String nome, email, senha, cpf, situacao, numeroCelular;
    private int quantidadeVendas;
    double salario;

    public Funcionario(String nome, String email, String senha, String cpf, double salario, String numeroCelular, String situacao, int quantidadeVendas) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.salario = salario;
        this.numeroCelular = numeroCelular;
        this.situacao = situacao;
        this.quantidadeVendas = quantidadeVendas;
    }

    public Funcionario(String nome, String email, String cpf, String situacao, int quantidadeVendas, double salario, String numeroCelular) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.situacao = situacao;
        this.salario = salario;
        this.numeroCelular = numeroCelular;
        this.quantidadeVendas = quantidadeVendas;
    }

    public Funcionario(String nome, String email, String senha, String cpf, double salario, String numeroCelular) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.salario = salario;
        this.numeroCelular = numeroCelular;
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

    public String getSituacao() {
        return situacao;
    }

    public int getQuantidadeVendas() {
        return quantidadeVendas;
    }

    @Override
    public String toString() {
        return  "\n========================"
                + "\nSituação: " + situacao
                + "\n========================\n"
                + "\nNome: " + nome
                + "\nE-mail: " + email
                + "\nNúmero de Celular: " + numeroCelular
                + "\nCPF: " + cpf
                + "\nSalário: " + salario
                + "\nQuantidade de Vendas: " + quantidadeVendas
                + "\n================================";
    }
}

package dominio;

import java.sql.Date;

public class Cliente {

    private int codigo;
    private String nome, nomeConta, email, senha, cpf, sexo;
    private Date data_nascimento;

    public Cliente(int codigo, String nome, String sexo, Date data_nascimento, String cpf, String nomeConta, String email, String senha) {
        this.codigo = codigo;
        this.nome = nome;
        this.nomeConta = nomeConta;
        this.email = email;
        this.cpf = cpf;
        this.sexo = sexo;
        this.senha = senha;
        this.data_nascimento = data_nascimento;
    }

    public String getNome() {

        return nome;
    }

    public String getNomeConta() {

        return nomeConta;
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

    public String getSexo() {

        return sexo;
    }

    public Date getData_nascimento() {

        return data_nascimento;
    }
    
    @Override
    public String toString() {
        return "Nome: " + nome 
                + "\nSexo: " + sexo 
                + "\nCPF: " + cpf 
                + "\nData de nascimento: " + data_nascimento 
                + "\nUSER: " + nomeConta 
                + "\nEmail: " + email 
                + "\nSenha: " + senha 
                + "\n================================";
    }
    
}
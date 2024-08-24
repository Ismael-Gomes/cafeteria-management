package dominio;

import java.sql.Date;

public class Cliente {

    private String nome, nomeConta, email, senha, cpf, sexo, data_nascimento;

    public Cliente(String cpf, String nome, String nomeConta, String sexo, String data_nascimento, String email, String senha) {
        this.nome = nome;
        this.nomeConta = nomeConta;
        this.email = email;
        this.cpf = cpf;
        this.sexo = sexo;
        this.senha = senha;
        this.data_nascimento = data_nascimento;
    }

    public Cliente(String nome, String nomeConta, String senha, String cpf) {
        this.nome = nome;
        this.nomeConta = nomeConta;
        this.senha = senha;
        this.cpf = cpf;
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

    public String getData_nascimento() {

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
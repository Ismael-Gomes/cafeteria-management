package dominio;

public class Admin {
    private String matricula;
    private String senha;
    private String nome;
    private String email;
    private String acesso;
    public Admin(String matricula, String email, String nome, String acesso, String senha) {
        this.matricula = matricula;
        this.senha = senha;
        this.nome = nome;
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAcesso() {
        return acesso;
    }
    public void setAcesso(String acesso) {
        this.acesso = acesso;
    }

    @Override
    public String toString() {
        return  "Nome: " + nome +
                "\nMatricula: " + matricula +
                "\nEmail: " + email +
                "\nPermissões: " + acesso +
                "\n=============================";
    }
}

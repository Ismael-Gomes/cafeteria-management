package dao;

import dominio.Funcionario;
import dominio.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FuncionarioDAO {
    private static final Logger LOGGER = Logger.getLogger(FuncionarioDAO.class.getName());
    private String url = "jdbc:mysql://localhost:3306/lanchonete";
    private String usuario = "ismael";
    private String senha = "IsmaeL123";

    public Connection conection() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }

    public List<Funcionario> searchAll() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario";
        try(Connection conexao = conection();
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                String situacao = rs.getString("situacao");
                if (situacao.equals("Ativo")) {
                    String nome = rs.getString("nome");
                    String email = rs.getString("email");
                    String cpf = rs.getString("cpf");
                    String numeroCelular = rs.getString("numero_tele");
                    double salario = rs.getDouble("salario");
                    int quantidadeVendas = rs.getInt("quantidadeVendas");
                    Funcionario func = new Funcionario(nome, email, cpf, situacao, quantidadeVendas, salario, numeroCelular);
                    funcionarios.add(func);
                }
            }
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE, "Erro ao buscar todos os clientes", e);
        }
        return funcionarios;

    }

    public List<Funcionario> searchByCPF(String cpf) throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario WHERE cpf = ?";
        try (Connection conexao = conection();
             PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String situacao = rs.getString("situacao");
                if (situacao.equals("Ativo")) {
                    String nome = rs.getString("nome");
                    String email = rs.getString("email");
                    String numeroCelular = rs.getString("numero_tele");
                    double salario = rs.getDouble("salario");
                    int quantidadeVendas = rs.getInt("quantidadeVendas");
                    Funcionario func = new Funcionario(nome, email, rs.getString("cpf"), situacao, quantidadeVendas, salario, numeroCelular);
                    funcionarios.add(func);
                }
            }
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE, "Erro ao buscar o cardapio", e);
        }
        return funcionarios;
    }
}

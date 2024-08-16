package dao;

import dominio.Funcionario;

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

    public void insertFuncionario(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionario (id_func, nome, email, senha, cpf, salario, numero_tele, situacao, quantidadeVendas, dataChegada) VALUES (NULL, ?, ?, ?, ?, ?, ?, 'Ativo', NULL, CURRENT_TIMESTAMP)";
        try (Connection conexao = conection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getEmail());
            ps.setString(3, funcionario.getSenha());
            ps.setString(4, funcionario.getCpf());
            ps.setDouble(5, funcionario.getSalario());
            ps.setString(6, funcionario.getNumeroCelular());
            ps.executeUpdate();
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE, "Erro ao inserir funcionário", e);
        }
    }

    public void updateFuncionario(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE funcionario SET nome = ?, email = ?, senha = ?, numero_tele = ?, salario = ? WHERE cpf = ?";
        try (Connection conexao = conection();
             PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getEmail());
            ps.setString(3, funcionario.getSenha());
            ps.setString(4, funcionario.getNumeroCelular());
            ps.setDouble(5, funcionario.getSalario());
            ps.setString(6, funcionario.getCpf());
            ps.executeUpdate();
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE, "Erro ao atualizar Funcionario.", e);
        }
    }

    public void dropFuncionario(String cpf) throws SQLException {
        String sql = "UPDATE funcionario SET situacao = 'Desligado', dataSaida = CURRENT_TIMESTAMP WHERE cpf = ?";
        try (Connection conexao = conection();
             PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setString(1, cpf);
            ps.executeUpdate();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao demitir o funcionario", e);
        }
    }

    public void vendaFuncionario(String cpf) throws SQLException {
        String sql = "UPDATE funcionario SET quantidadeVendas = quantidadeVendas+ 1 WHERE cpf = ?";
        try (Connection conexao = conection();
             PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setString(1, cpf);
            ps.executeUpdate();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao Vender com esse Funcionario", e);
        }
    }

}

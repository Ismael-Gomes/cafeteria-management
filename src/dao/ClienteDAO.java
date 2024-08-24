package dao;

import dominio.Cliente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO {
  
    private static final Logger LOGGER = Logger.getLogger(ClienteDAO.class.getName());
    private String url = "jdbc:mysql://localhost:3306/lanchonete";
    private String usuario = "ismael";
    private String senha = "IsmaeL123";
  
    public Connection conection() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }

    public List<Cliente> searchAll() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try(Connection conexao = conection();
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
          while (rs.next()) {
            String cpf = rs.getString("cpf");
            String nome = rs.getString("nome");
            String sexo = rs.getString("sexo");
            String data_nascimento = rs.getString("data_nascimento");
            String nomeConta = rs.getString("nome_conta");
            String email = rs.getString("email");
            String senhaUsu = rs.getString("senha");
            Cliente clien = new Cliente(cpf, nome, nomeConta, sexo, data_nascimento, email, senhaUsu);
            clientes.add(clien);
          }
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE, "Erro ao buscar todos os clientes", e);
        }
        return clientes;
    }

    public String searchNameByCPF(String cpf) {
        String sql = "SELECT nome FROM cliente WHERE cpf = ?";
        try (Connection conexao = conection();
             PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nome");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar nome por CPF", e);
        }
        return null;
    }

    public void insertClien(Cliente cliente) {
        String sql = "INSERT INTO cliente ?, ?, ?, ?, ?, ?, ?";
        try(Connection conexao = conection();
        PreparedStatement ps = conection().prepareStatement(sql)){
            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getNomeConta());
            ps.setString(4, cliente.getSexo());
            ps.setString(5, cliente.getData_nascimento());
            ps.setString(6, cliente.getEmail());
            ps.setString(7, cliente.getSenha());
            ps.executeUpdate();
        }catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao inserir cliente", e);
        }
    }

    public void updateClien(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, nome_conta = ?, senha = ? where cpf = ?";
        String verificaNomeContaSql = "SELECT COUNT(*) FROM cliente WHERE nome_conta = ?";
        try(Connection conexao = conection();
            PreparedStatement ps1 = conection().prepareStatement(verificaNomeContaSql);
            PreparedStatement ps = conection().prepareStatement(sql)){
            ps.setString(1, cliente.getNome());
            ps1.setString(1, cliente.getNomeConta());
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    System.out.println("O nome da conta já existe, e não pode ser alterado.");
                }else{
                    ps.setString(2, cliente.getNomeConta());
                    ps.setString(3, cliente.getSenha());
                    ps.setString(4, cliente.getCpf());
                    ps.executeUpdate();
                }
            }
        }catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao inserir cliente", e);
        }
    }

    public void dropClien(String cpf) {
        String sql = "DELETE FROM cliente WHERE cpf = ?";
        try(Connection conexao = conection();
        PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setString(1, cpf);
            ps.executeUpdate();
        }catch (SQLException e){
            LOGGER.log(Level.SEVERE, "Erro ao deletar cliente", e);
        }
    }

}
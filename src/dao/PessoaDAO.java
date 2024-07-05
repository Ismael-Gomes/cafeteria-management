package dao;

import dominio.Pessoa;
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

public class PessoaDAO {
  
    private static final Logger LOGGER = Logger.getLogger(PessoaDAO.class.getName());
    private String url = "jdbc:mysql://localhost:3306/lanchonete";
    private String usuario = "ismael";
    private String senha = "IsmaeL123";
  
    public Connection conection() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }

    public List<Pessoa> searchAll() {
        List<Pessoa> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try(Connection conexao = conection();
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
          while (rs.next()) {
            int codigo = rs.getInt("cod_cliente");
            String nome = rs.getString("nome");
            String sexo = rs.getString("sexo");
            Date data_nascimento = rs.getDate("data_nascimento");
            String cpf = rs.getString("cpf");
            String nomeConta = rs.getString("nome_conta");
            String email = rs.getString("email");
            String senhaUsu = rs.getString("senha");
            Pessoa clien = new Pessoa(codigo, nome, sexo, data_nascimento, cpf, nomeConta, email, senhaUsu);
            clientes.add(clien);
          }
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE, "Erro ao buscar todos os clientes", e);
        }
        return clientes;

    }

    /*public void inserir(Empregado empregado) throws SQLException {
        Connection conexao = conectar();
        String sql = "INSERT INTO empregado (nome, salario) VALUES(?,?);";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setString(1, empregado.getNome());
        ps.setDouble(2, empregado.getSalario());
        ps.executeUpdate();
        desconectar(conexao);

    }

    public void alterarPorCodigo(Empregado empregado) throws SQLException {
        Connection conexao = conectar();
        String sql = "UPDATE empregado SET nome = ?, salario = ? WHERE codigo = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setString(1, empregado.getNome());
        ps.setDouble(2, empregado.getSalario());
        ps.setInt(3, empregado.getCodigo());
        ps.executeUpdate();
        desconectar(conexao);

    }

    public void excluirPorCodigo(int codigo) throws SQLException {
        Connection conexao = conectar();
        String sql = "DELETE FROM empregado WHERE codigo = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, codigo);
        ps.executeUpdate();
        desconectar(conexao);

    }
    
    public Empregado buscarPorCodigo(int codigo) throws SQLException{
        Empregado e = null;
        Connection conexao = conectar();
        String sql = "SELECT * FROM empregado WHERE codigo = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            String nome = rs.getString("nome");
            Double salario = rs.getDouble("salario");
            e = new Empregado(codigo, nome, salario);
        }
        desconectar(conexao);
        return e;
    }
    
    public int contarEmpregados() throws SQLException {
        Connection conexao = conectar();
        String sql = "SELECT COUNT(codigo) FROM empregado;";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        desconectar(conexao);
        return contarEmpregados();
    }
*/

}
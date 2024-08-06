package dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SuporteDAO {

    private static final Logger LOGGER = Logger.getLogger(ClienteDAO.class.getName());
    private String url = "jdbc:mysql://localhost:3306/lanchonete";
    private String usuario = "ismael";
    private String senha = "IsmaeL123";

    //TODO
    public Connection conection() throws SQLException {
        try {
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao conectar com o banco de dados", e);
            throw e;
        }
    }

    public void insertSuport(String cpfSuporte, String cpfCliente, String nomeCliente, String descricaoProblema) throws SQLException {
        String sql = "INSERT INTO suporte (cpf_suporte, cpf_cliente, nome_cliente, descricao_problema) VALUES (?, ?, ?, ?)";
        try (Connection conexao = conection();
             PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setString(1, cpfSuporte);
            ps.setString(2, cpfCliente);
            ps.setString(3, nomeCliente);
            ps.setString(4, descricaoProblema);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao inserir Suporte", e);
            throw e;
        }
    }


}


package dao;

import dominio.Venda;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendaDAO {
    
    private static final Logger LOGGER = Logger.getLogger(PessoaDAO.class.getName());
    private String url = "jdbc:mysql://localhost:3306/lanchonete";
    private String usuario = "ismael";
    private String senha = "IsmaeL123";

    //TODO
    public Connection conection() throws SQLException {
        try{
        return DriverManager.getConnection(url, usuario, senha);
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE, "Erro ao conectar com o banco de dados", e);
            throw e;
        }
    }
    
    public void insertVale(Venda venda) throws SQLException {
        String sql = "INSERT INTO venda (produto_id, quantidade, data_venda) VALUES (?, ?, ?)";
        try (Connection conexao = conection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, venda.getProdutoId());
            ps.setInt(2, venda.getQuantidade());
            ps.setTimestamp(3, venda.getDataVenda());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao inserir venda", e);
            throw e;
        }
    }
    
}

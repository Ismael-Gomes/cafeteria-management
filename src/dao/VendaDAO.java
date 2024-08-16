
package dao;

import dominio.Produto;
import dominio.Venda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendaDAO {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteDAO.class.getName());
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
    
    public void insertSale(Venda venda) throws SQLException {
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

    public List<Venda> relVenda() throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT id_produto, acao, data_acao, detalhes FROM produto_auditoria";
        try (Connection conexao = conection();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int idProduto = rs.getInt("id_produto");
                String acao = rs.getString("acao");
                Timestamp dataAcao = rs.getTimestamp("data_acao");
                String detalhe = rs.getString("detalhes");
                Venda venda = new Venda(idProduto, acao, dataAcao, detalhe);
                vendas.add(venda);
            }
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE, "Erro ao buscar as vendas", e);
        }
        return vendas;
    }

}

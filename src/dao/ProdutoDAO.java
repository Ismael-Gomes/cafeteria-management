package dao;

import dominio.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.*;

public class ProdutoDAO {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteDAO.class.getName());
    private String url = "jdbc:mysql://localhost:3306/lanchonete";
    private String usuario = "ismael";
    private String senha = "IsmaeL123";

    private Connection conection() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }
    
    public List<Produto> searchAll() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        try (Connection conexao = conection();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int codigo = rs.getInt("codigo");
                String categoria = rs.getString("categoria");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");
                Produto produto = new Produto(id, codigo, categoria, nome, descricao, preco, quantidade);
                produtos.add(produto);
            }
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE, "Erro ao buscar todos", e);
        }
        return produtos;
    }
    
    public List<Produto> searchByCategory(String categoria) throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE categoria = ?";
        try (Connection conexao = conection();
            PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setString(1, categoria);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");
                Produto produto = new Produto(id, codigo, rs.getString("categoria"), nome, descricao, preco, quantidade);
                produtos.add(produto);
            }
                
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE, "Erro ao buscar o cardapio", e);
        }
        return produtos;
    }

    public void insertProduct(Produto produto) throws SQLException {
        String sql = "INSERT INTO produto (id, data_criacao, codigo, categoria, nome, descricao, preco, quantidade) VALUES (NULL, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?, ?)";
        try (Connection conexao = conection();
            PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, produto.getCodigo());
            ps.setString(2, produto.getCategoria());
            ps.setString(3, produto.getNome());
            ps.setString(4, produto.getDescricao());
            ps.setDouble(5, produto.getPreco());
            ps.setInt(6, produto.getQuantidade());
            ps.executeUpdate();
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE, "Erro ao inserir produto", e);
        }
    }
        
    public void updateProduct(Produto produto) throws SQLException {
        String sql = "UPDATE produto SET nome = ?, descricao = ?, preco = ?, quantidade = ? WHERE id = ?";
        try (Connection conexao = conection();
            PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDescricao());
            ps.setDouble(3, produto.getPreco());
            ps.setInt(4, produto.getQuantidade());
            ps.setInt(5, produto.getId());
            ps.executeUpdate();
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE, "Erro ao atualizar produto", e);
        }
    }
    
    public boolean updateInventory(int produtoCodigo, int quantidadeVendida) throws SQLException {
        String sql = "UPDATE produto SET quantidade = quantidade - ? WHERE codigo = ?";
        String sqlQuantity = "SELECT quantidade FROM produto WHERE codigo = ?";
        try (Connection conexao = conection();
             PreparedStatement ps = conexao.prepareStatement(sql);
             PreparedStatement psQuantity = conexao.prepareStatement(sqlQuantity)){
            psQuantity.setInt(1, produtoCodigo);
            try (ResultSet rs = psQuantity.executeQuery()) {
                if (rs.next()) {
                    int quantidadeAtual = rs.getInt("quantidade");
                    if (quantidadeAtual >= quantidadeVendida){
                        ps.setInt(1, quantidadeVendida - quantidadeVendida);
                        ps.setInt(2, produtoCodigo);
                        ps.executeUpdate();
                        return true;
                    } else {
                        LOGGER.log(Level.SEVERE, "Estoque insuficiente!");
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao atualizar estoque", e);
            throw e;
        }
        return false;
    }

    public int searchId(int produtoCodigo) throws SQLException {
        String sql = "SELECT id FROM produto WHERE codigo = ?";
        try (Connection conexao = conection();
             PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setInt(1, produtoCodigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    throw new SQLException("Produto não encontrado");
                }
            }
        } catch (SQLException e){
            LOGGER.log(Level.SEVERE, "Erro ao buscar produto", e);
            throw e;
        }
    }
    
}
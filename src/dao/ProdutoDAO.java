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
import java.math.BigDecimal;

public class ProdutoDAO {
    
    private static final Logger LOGGER = Logger.getLogger(PessoaDAO.class.getName());
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
                String categoria = rs.getString("categoria");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                BigDecimal preco = rs.getBigDecimal("preco");
                int quantidade = rs.getInt("quantidade");
                Timestamp dataCriacao = rs.getTimestamp("data_criacao");
                Produto produto = new Produto(id, categoria, nome, descricao, preco, quantidade, dataCriacao);
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
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                BigDecimal preco = rs.getBigDecimal("preco");
                int quantidade = rs.getInt("quantidade");
                Timestamp dataCriacao = rs.getTimestamp("data_criacao");
                Produto produto = new Produto(id, rs.getString("categoria"), nome, descricao, preco, quantidade, dataCriacao);
                produtos.add(produto);
            }
                
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE, "Erro ao buscar o cardapio", e);
        }
        return produtos;
    }

    public void insertProduct(Produto produto) throws SQLException {
        String sql = "INSERT INTO produto (categoria, nome, descricao, preco, quantidade) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexao = conection();
            PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, produto.getCategoria());
            ps.setString(2, produto.getNome());
            ps.setString(3, produto.getDescricao());
            ps.setBigDecimal(4, produto.getPreco());
            ps.setInt(5, produto.getQuantidade());
            ps.executeUpdate();
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE, "Erro ao inserir produto", e);
        }
    }
        
    public void updateProduct(Produto produto) throws SQLException {
        String sql = "UPDATE produto SET categoria = ?, nome = ?, descricao = ?, preco = ?, quantidade = ? WHERE id = ?";
        try (Connection conexao = conection();
            PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setString(1, produto.getCategoria());
            ps.setString(2, produto.getNome());
            ps.setString(3, produto.getDescricao());
            ps.setBigDecimal(4, produto.getPreco());
            ps.setInt(5, produto.getQuantidade());
            ps.setInt(6, produto.getId());
            ps.executeUpdate();
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE, "Erro ao atualizar produto", e);
        }
    }
    
    public void dropProduct(int id) throws SQLException {
        String sql = "DELETE FROM produto WHERE id = ?";
        try (Connection conexao = conection();
            PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao deletar produto", e);
        }
    }
    
    public void updateInventory(int produtoId, int quantidadeVendida) throws SQLException {
        String sql = "UPDATE produto SET quantidade = quantidade - ? WHERE id = ?";
        try (Connection conexao = conection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, quantidadeVendida);
            ps.setInt(2, produtoId);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao atualizar estoque", e);
            throw e;
        }
    }
    
}
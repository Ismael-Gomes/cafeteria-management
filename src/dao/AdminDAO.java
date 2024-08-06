package dao;

import dominio.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class AdminDAO {

    private static final Logger LOGGER = Logger.getLogger(ClienteDAO.class.getName());
    private String url = "jdbc:mysql://localhost:3306/lanchonete";
    private String usuario = "ismael";
    private String senha = "IsmaeL123";

    private Connection conection() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }

    public List<Admin> searchAdmins() throws SQLException {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT * FROM admin";
        try (Connection conexao = conection();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String matricula = rs.getString("matricula");
                String email = rs.getString("email");
                String nome = rs.getString("nome");
                String acesso = rs.getString("acesso");
                String senha = rs.getString("senha");
                Admin admin = new Admin(matricula, email, nome, acesso, senha);
                admins.add(admin);
            }
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE, "Erro ao buscar todos", e);
        }
        return admins;
    }

    public void insertAdmin(Admin admin) throws SQLException {
        String sql = "INSERT INTO admin (matricula, email, nome, acesso, senha) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexao = conection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, admin.getMatricula());
            ps.setString(2, admin.getEmail());
            ps.setString(3, admin.getNome());
            ps.setString(4, admin.getAcesso());
            ps.setString(5, admin.getSenha());
            ps.executeUpdate();
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE, "Erro ao inserir admin", e);
        }
    }

    public void updateAdmin(Admin admin) throws SQLException {
        String sql = "UPDATE admin SET matricula = ?, email = ?, nome = ?, acesso = ?, senha = ? WHERE matricula = ?";
        try (Connection conexao = conection();
             PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setString(1, admin.getMatricula());
            ps.setString(2, admin.getEmail());
            ps.setString(3, admin.getNome());
            ps.setString(4, admin.getAcesso());
            ps.setString(5, admin.getSenha());
            ps.setString(6, admin.getMatricula());
            ps.executeUpdate();
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE, "Erro ao atualizar o admin", e);
        }
    }

    public void dropAdmin(String matricula) throws SQLException {
        String sql = "DELETE FROM admin WHERE matricula = ?";
        try (Connection conexao = conection();
             PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setString(1, matricula);
            ps.executeUpdate();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao deletar admin", e);
        }
    }

    public boolean verificarMatricula(String matricula) {
        String sql = "SELECT * FROM admin WHERE matricula = ?";
        try (Connection conexao = conection();
            PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setString(1, matricula);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro na matricula!", e);
            return false;
        }
    }

    public boolean verificarSenha(String matricula, String senha) {
        String sql = "SELECT * FROM admin WHERE BINARY matricula = ? AND BINARY senha = ?";
        try (Connection conexao = conection();
            PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setString(1, matricula);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro na senha!", e);;
            return false;
        }
    }

}
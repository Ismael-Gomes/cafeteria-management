package negocio;

import dao.AdminDAO;
import dominio.Admin;
import java.sql.SQLException;
import java.util.List;

public class AdminNegocio {

    private AdminDAO adminDAO = new AdminDAO();

    public List<Admin> searchAdmins() throws SQLException{
        return adminDAO.searchAdmins();
    }

    public void insertAdmin(Admin admin) throws SQLException {
        adminDAO.insertAdmin(admin);
    }

    public void updateAdmin(Admin admin) throws SQLException {
        adminDAO.updateAdmin(admin);
    }

    public void dropAdmin(String matricula) throws SQLException {
        adminDAO.dropAdmin(matricula);
    }

    public AdminNegocio() {
        this.adminDAO = new AdminDAO();
    }

    public boolean verificarCredenciais(String matricula, String senha) {
        if (adminDAO.verificarMatricula(matricula)) {
            return adminDAO.verificarSenha(matricula, senha);
        } else {
            System.out.println("Matrícula não encontrada.");
            return false;
        }
    }

    public boolean verificarMatricula(String matricula) {
        return adminDAO.verificarMatricula(matricula);
    }

}

package negocio;

import dao.FuncionarioDAO;
import dominio.Funcionario;

import java.sql.SQLException;
import java.util.List;

public class FuncionarioNegocio {
    private FuncionarioDAO funcionarioDao = new FuncionarioDAO();

    public List<Funcionario> searchAll() throws SQLException {
        return funcionarioDao.searchAll();
    }

    public List<Funcionario> searchById(String cpf) throws SQLException{
        return funcionarioDao.searchById(cpf);
    }

    public void insertFuncionario(Funcionario funcionario) throws SQLException{
        funcionarioDao.insertFuncionario(funcionario);
    }

    public void updateFuncionario(Funcionario funcionario) throws SQLException{
        funcionarioDao.updateFuncionario(funcionario);
    }

    public void dropFuncionario(String cpf) throws SQLException{
        funcionarioDao.dropFuncionario(cpf);
    }

    public void vendaFuncionario(String cpf) throws SQLException{
        funcionarioDao.vendaFuncionario(cpf);
    }
}

package negocio;

import dao.ClienteDAO;
import dominio.Cliente;
import java.sql.SQLException;
import java.util.List;

public class ClienteNegocio {
 
    private ClienteDAO clienteDao = new ClienteDAO();

    public List<Cliente> searchAll() throws SQLException {
        return clienteDao.searchAll();
    }

    public String searchNameByCPF(String cpf) {
        return clienteDao.searchNameByCPF(cpf);
    }

    public void insertClien(Cliente cliente) throws SQLException {
        clienteDao.insertClien(cliente);
    }

    public void updateClien(Cliente cliente) throws SQLException {
        clienteDao.updateClien(cliente);
    }

    public void deleteClien(String cpf) throws SQLException {
        clienteDao.dropClien(cpf);
    }
    
}
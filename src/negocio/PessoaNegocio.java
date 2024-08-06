package negocio;

import dao.ClienteDAO;
import dominio.Cliente;
import java.sql.SQLException;
import java.util.List;

public class PessoaNegocio {
 
    private ClienteDAO clienteDao = new ClienteDAO();

    public List<Cliente> searchAll() throws SQLException {
        return clienteDao.searchAll();
    }

    /*
        //listagem de clientes em sequencia
        GerenciadorDeClientes gerenciadorCliente = new GerenciadorDeClientes();
        gerenciadorCliente.exibirClientesComSequencia();
    */
    
}
package negocio;

import dao.PessoaDAO;
import dominio.Pessoa;
import java.sql.SQLException;
import java.util.List;

public class PessoaNegocio {
 
    private PessoaDAO pessoaDao = new PessoaDAO();

    public List<Pessoa> searchAll() throws SQLException {
        return pessoaDao.searchAll();
    }

    /*
        //listagem de clientes em sequencia
        GerenciadorDeClientes gerenciadorCliente = new GerenciadorDeClientes();
        gerenciadorCliente.exibirClientesComSequencia();
    */
    
}
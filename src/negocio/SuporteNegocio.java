package negocio;

import dao.SuporteDAO;
import dao.ClienteDAO;
import dominio.Suporte;
import java.sql.SQLException;

public class SuporteNegocio {

    private ClienteDAO clienteDAO;
    private SuporteDAO suporteDAO;

    public SuporteNegocio() {
        this.clienteDAO = new ClienteDAO();
        this.suporteDAO = new SuporteDAO();
    }


    public void insertSuport(String cpfSuporte, String cpfCliente, String descricaoProblema, String nomeCliente) throws SQLException {
        String nomeEncontrado = clienteDAO.obterNomePorCPF(cpfCliente);
        if (nomeEncontrado != null) {
            suporteDAO.insertSuport(cpfSuporte, cpfCliente, nomeEncontrado, descricaoProblema);
        } else {
            suporteDAO.insertSuport(cpfSuporte, cpfCliente, nomeCliente, descricaoProblema);
        }
    }

}

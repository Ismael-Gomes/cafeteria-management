package lanchonete;

import dominio.Cliente;
import negocio.PessoaNegocio;
import java.sql.SQLException;
import java.util.List;

public class GerenciadorDeClientes {

    public void exibirClientesComSequencia() {
        PessoaNegocio pessoaNegocio = new PessoaNegocio();
        List<Cliente> clientes;
        
        try {
            clientes = pessoaNegocio.searchAll();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar clientes: " + e.getMessage());
            return;
        }

        int sequencia = 1;
        for (Cliente cliente : clientes) {
            System.out.println("\nCliente " + sequencia);
            System.out.println("==============================");
            System.out.println(cliente.toString());
            sequencia++;
        }
    }
    
    public static void main(String[] args) {
        GerenciadorDeClientes gerenciadorClientes = new GerenciadorDeClientes();
        gerenciadorClientes.exibirClientesComSequencia();
    }
}

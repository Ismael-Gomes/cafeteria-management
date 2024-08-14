package lanchonete;

import dominio.Funcionario;
import negocio.FuncionarioNegocio;

import java.sql.SQLException;
import java.util.List;

public class GerenciadorDeFuncionarios {
    public void exibirFuncionariosComSequencia() {
        FuncionarioNegocio funcionarioNegocio = new FuncionarioNegocio();
        List<Funcionario> funcionarios;

        try {
            funcionarios = funcionarioNegocio.searchAll();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar clientes: " + e.getMessage());
            return;
        }

        int sequencia = 1;
        for (Funcionario funcionario : funcionarios) {
            System.out.println("\nFuncionário " + sequencia);
            System.out.println("==============================");
            System.out.println(funcionario.toString());
            sequencia++;
        }
    }

    public static void main(String[] args) {
        GerenciadorDeClientes gerenciadorClientes = new GerenciadorDeClientes();
        gerenciadorClientes.exibirClientesComSequencia();
    }
}

package lanchonete;

import dominio.Venda;
import negocio.VendaNegocio;

import java.sql.SQLException;
import java.util.List;

public class GerenciadorDeVendas {

    public void sequenciaTodos(){
        VendaNegocio vendaNegocio = new VendaNegocio();
        List<Venda> vendas;

        try {
            vendas = vendaNegocio.relVenda();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar as vendas: " + e.getMessage());
            return;
        }

        int sequencia = 1;
        for (Venda venda : vendas) {
            System.out.println("\n#### Venda " + sequencia);
            System.out.println("=============================");
            System.out.println(venda.toString());
            sequencia++;
        }

    }

}

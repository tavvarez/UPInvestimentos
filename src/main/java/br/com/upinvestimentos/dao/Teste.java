package br.com.upinvestimentos.dao;


import java.sql.SQLException;
import java.util.List;

import br.com.upinvestimentos.model.ControleGastoGeralModel;


public class Teste {
	
	public static void main(String[] args) throws SQLException {


     
        
        System.out.println("********************************");
		System.out.println("CONEXÃO COM O BANCO DE DADOS");
		
		

	
		ControleGastoGeralDao daoConsulta = new ControleGastoGeralDao();
        List<ControleGastoGeralModel> ListaControleGastosGeral = daoConsulta.ControleGastoGeralConsulta();
        
        for (ControleGastoGeralModel item : ListaControleGastosGeral) {
            System.out.println(item.getCdTransacao() + " " + item.getItUser() + " " + item.getValorSaldo());
        } 
		
		System.out.println("********************************");
		System.out.println("CONEXÃO COM O BANCO DE DADOS");
		

		
	}
}


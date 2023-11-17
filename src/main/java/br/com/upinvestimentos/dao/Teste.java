package br.com.upinvestimentos.dao;

import java.sql.SQLException;
import java.util.List;


public class Teste {

	public static void main(String[] args) throws SQLException {

		System.out.println("********************************");
		System.out.println("CONEXÃO COM O BANCO DE DADOS");

		
        GastosDAO daoConsulta = new GastosDAO(0, null, 0);
        List<GastosDAO> listaControleGastosGeral = daoConsulta.getAll();

        for (GastosDAO item : listaControleGastosGeral) {
            System.out.println(item.getCdTransacao() + " " + item.getItUser() + " " + item.getValorSaldo());
        }

        System.out.println("********************************");
        System.out.println("CONEXÃO COM O BANCO DE DADOS");

	}
}

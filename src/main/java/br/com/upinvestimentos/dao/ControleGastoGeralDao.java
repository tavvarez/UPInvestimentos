package br.com.upinvestimentos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.upinvestimentos.bd.ConexaoOracle;
import br.com.upinvestimentos.model.ControleGastoGeralModel;

public class ControleGastoGeralDao {
	private Connection conexaoDB;
	private int cdTransacao; // Código da transação  
	private Double valorSaldo; //Saldo atual
	private int idUser;
	
	
	public ControleGastoGeralDao() {
		
	}
	
	public void ControleGastosGeralModel() {
		
	}

	public List<ControleGastoGeralDao> ControleGastoGeralConsulta() {
		List<ControleGastoGeralModel> ListaControleGastosGeral = new ArrayList<ControleGastoGeralModel>();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexaoDB = ConexaoOracle.realizarConexao();
			stmt = conexaoDB.prepareStatement("SELECT * FROM T_CTRL_GASTO"); // CD_TRANSACAO, VL_CRTL_SALDO, CD_USER
			rs = stmt.executeQuery();

			// Percorre todos os registros encontrados
			while (rs.next()) {
				int cdTransacao = rs.getInt("CD_TRANSACAO");
				Double valorSaldo = rs.getDouble("VL_CRTL_SALDO");
				int idUser = rs.getInt("CD_USER");
				// Cria um objeto novoUsuario com as informações encontradas
				ControleGastoGeralModel novoGasto = new ControleGastoGeralModel(cdTransacao, valorSaldo, idUser);
				// Adiciona o gasto na lista
				ListaControleGastosGeral.add(novoGasto);

			}
		} catch (SQLException e) {
			if (e.getErrorCode() == 942) {
				System.out.printf("Tabela inexistente!", e);
			}
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexaoDB.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ListaControleGastosGeral;
	}

	public int getCdTransacao() {
		// TODO Auto-generated method stub
		return cdTransacao;
	}


	public int getItUser() {
		// TODO Auto-generated method stub
		return idUser;
	}


	public Double getValorSaldo() {
		// TODO Auto-generated method stub
		return valorSaldo;
	}	
	
}
package br.com.upinvestimentos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.upinvestimentos.bd.ConexaoOracle;
import br.com.upinvestimentos.model.ControleGastosEntradasModel;

public class GastosSaidasDAO {

	private Connection conexaoDB;
	private int cdTransacao;// Código da transação
	private double valorSaida;// Valor de saida
	private String descricaoSaida; // Descrição da saída de valores

	public GastosSaidasDAO(int cdTransacao, Double valorSaida, String descricaoSaida) {
		this.cdTransacao = cdTransacao;
		this.valorSaida = valorSaida;
		this.descricaoSaida = descricaoSaida;
	}

	public GastosSaidasDAO() {

	}

	public List<GastosSaidasDAO> getAll() {
		List<GastosSaidasDAO> lista = new ArrayList<GastosSaidasDAO>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexaoDB = ConexaoOracle.realizarConexao();
			stmt = conexaoDB.prepareStatement("SELECT * FROM T_CTRL_GASTO_SAIDA"); // CD_TRANSACAO, VL_CRTL_SALDO,
																					// CD_USER
			rs = stmt.executeQuery();

			// Percorre todos os registros encontrados
			while (rs.next()) {
				int numTransacoes = rs.getInt("CD_TRANSACAO");
				Double valorSaidas = rs.getDouble("VL_VL_SAIDA");
				String descricaoSaidas = rs.getString("DS_SAIDA");

				// Cria um objeto novoEntrasa com as informações encontradas
				GastosSaidasDAO novoSaida = new GastosSaidasDAO(numTransacoes, valorSaidas, descricaoSaidas);
				lista.add(novoSaida);

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
		return lista;
	}

	// Inserir um novo registro de saída de gastos
	public void inserirSaidaGastos(GastosSaidasDAO saidaGastos) {
		PreparedStatement stmt = null;

		try {
			conexaoDB = ConexaoOracle.realizarConexao();
			stmt = conexaoDB.prepareStatement(
					"INSERT INTO T_CTRL_GASTO_SAIDA (CD_TRANSACAO, VL_SAIDA, DS_SAIDA) VALUES (?, ?, ?)");

			// Define os valores dos parâmetros na query
			stmt.setInt(1, saidaGastos.getCdTransacao());
			stmt.setDouble(2, saidaGastos.getValorSaida());
			stmt.setString(3, saidaGastos.getDescricaoSaida());

			// Executa a inserção
			stmt.executeUpdate();

			System.out.println("Registro de saída de gastos inserido com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				conexaoDB.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public String getDescricaoSaida() {
		return descricaoSaida;
	}

	public int getCdTransacao() {
		// TODO Auto-generated method stub
		return cdTransacao;
	}

	public Double getValorSaida() {
		// TODO Auto-generated method stub
		return valorSaida;
	}

}

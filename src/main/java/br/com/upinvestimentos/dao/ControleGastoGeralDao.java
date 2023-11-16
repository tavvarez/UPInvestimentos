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

	public List<ControleGastoGeralModel> ControleGastoGeralConsulta() {
		List<ControleGastoGeralModel> ListaControleGastosGeral = new ArrayList<ControleGastoGeralModel>();

		PreparedStatement stmt = null;
		ResultSet rstm = null;
		try {
			conexaoDB = ConexaoOracle.realizarConexao();
			stmt = conexaoDB.prepareStatement("SELECT * FROM T_USUARIO");
			rstm = stmt.executeQuery();

			// Percorre todos os registros encontrados
			while (rstm.next()) {
				int idUsuario = rstm.getInt("ID_USUARIO"); // Verificar a tabela do banco e preencher com a coluna correta
				String nome = rstm.getString("DS_NOME"); // Verificar a tabela do banco e preencher com a coluna correta
				String sobrenome = rstm.getString("DS_SOBRENOME"); // Verificar a tabela do banco e preencher com a coluna correta
				String email = rstm.getString("DS_EMAIL"); // Verificar a tabela do banco e preencher com a coluna correta
				String senha = rstm.getString("DS_SENHA"); // Verificar a tabela do banco e preencher com a coluna correta
				java.sql.Date data = rstm.getDate("DT_CADASTRO"); // Verificar a tabela do banco e preencher com a coluna correta
				Calendar dataCadastro = Calendar.getInstance();
				dataCadastro.setTimeInMillis(data.getTime());
				// Cria um objeto novoUsuario com as informações encontradas
				ControleGastoGeralModel novoGasto = new ControleGastoGeralModel();
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
				rstm.close();
				conexaoDB.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ListaControleGastosGeral;
	}
}
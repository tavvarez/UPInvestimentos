package br.com.upinvestimentos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.troca.model.ProdutoModel;
import br.com.upinvestimentos.bd.ConexaoOracle;

public class GastosEntradasDAO {
		private Connection conexaoDB;
		private int cdTransacao;// Código da transação
		private double valorEntrada;// Valor da entrada   
		private String descricaoEntrada; //Descrição da entrada de valores
			
		
		public GastosEntradasDAO(int cdTransacao, Double valorEntrada, String descricaoEntrada) {
			this.cdTransacao = cdTransacao;
			this.valorEntrada = valorEntrada;
			this.descricaoEntrada = descricaoEntrada;
		}
		
		public GastosEntradasDAO() {

		}
		

		public List<GastosEntradasDAO> getAll() {
			List<GastosEntradasDAO> lista = new ArrayList<GastosEntradasDAO>();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				conexaoDB = ConexaoOracle.realizarConexao();
				stmt = conexaoDB.prepareStatement("SELECT * FROM T_CTRL_GASTO_ENTRADA"); // CD_TRANSACAO, VL_CRTL_SALDO, CD_USER
				rs = stmt.executeQuery();

				// Percorre todos os registros encontrados
				while (rs.next()) {
					int numTransacoes = rs.getInt("CD_TRANSACAO");
					Double valorEntradas = rs.getDouble("VL_VL_ENTRADA");
					String descricaoEntradas = rs.getString("DS_ENTRADA");
					
					// Cria um objeto novoEntrasa com as informações encontradas
					GastosEntradasDAO novoEntrada = new GastosEntradasDAO(numTransacoes, valorEntradas, descricaoEntradas);
	                lista.add(novoEntrada);

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

		
		// Inserir um novo produto
	    public void inserirEntradaGastos(ControleGastosEntradaModel entradaGastos) {
	        int novoId = produtos.size() + 1;
	        produto.setProdutoId(novoId);
	        produtos.add(produto);
		
		
		public int getCdTransacao() {
			// TODO Auto-generated method stub
			return cdTransacao;
		}

		public Double getValorEntrada() {
			// TODO Auto-generated method stub
			return valorEntrada;
		}	
	
	
	

}

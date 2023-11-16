package br.com.upinvestimentos.model;

import java.util.Date;

public class ControleGastoGeralModel {

	private int cd_transacao; // Código da transação  
	private Double vl_ctrl_saldo; //Saldo atual
	    
	    // Construtor padrão sem argumentos
		public ControleGastoGeralModel() {
			super();
		}
	

		// Construtor com dados obrigatórios (exceto a senha)
		public ControleGastoGeralModel(int cd_transacao, Double vl_ctrl_saldo) {
			super();
			this.cd_transacao = cd_transacao;
			this.vl_ctrl_saldo = vl_ctrl_saldo;
		}	
		
		
}

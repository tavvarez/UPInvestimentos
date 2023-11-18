package br.com.upinvestimentos.model;

import java.util.Date;

// Classe que representa um modelo de usuário
public class UsuarioModel {
	
    private int cdUsuario; // Identificador do usuário
    
    private String nomeUsuario; // Nome do usuário
    
    private String descricaoEmail; // Email do usuário
    
    private String numeroCPF; // Numero de CPF do usuário
    
    private Date dataNasc; // Data de nascimento do usuário

    // Construtor padrão sem argumentos
	public UsuarioModel() {
	}

    // Construtor com dados obrigatórios (exceto a senha)
	public UsuarioModel(int cdUsuario, String nomeUsuario, String numeroCPF, Date dataNasc, String descricaoEmail) {
		this.cdUsuario = cdUsuario;
		this.nomeUsuario = nomeUsuario;
		this.numeroCPF = numeroCPF;
		this.dataNasc = dataNasc;
		this.descricaoEmail =descricaoEmail;

	}

    // Métodos de acesso para o campo usuarioId
	public int getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUser(int cdUsuario) {
		this.cdUsuario = cdUsuario;
	}

    // Métodos de acesso para o campo nome
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

    // Métodos de acesso para o campo CPF
	public String getNumeroCPF() {
		return numeroCPF;
	}

	public void setNumeroCPF(String numeroCPF) {
		this.numeroCPF = numeroCPF;
	}

    // Métodos de acesso para o campo email
	public String getDescricaoEmail() {
		return descricaoEmail;
	}
	
	public void setDescricaoEmail(String descricaoEmail) {
	    this.descricaoEmail = descricaoEmail;
	}

	
	 // Métodos de acesso para o campo dt_nasc
		public Date getDataNasc() {
			return dataNasc;
		}

		public void setDataNasc(Date dataNasc) {
			this.dataNasc = dataNasc;
		}


    // Sobrescrita do método toString para exibir os detalhes do usuário
	@Override
	public String toString() {
		return "UsuarioModel [cd_user=" + cd_user + ", nm_user=" + nm_user + ", ds_email=" + ds_email + ", nr_CPF=" + nr_CPF
				+ ", dt_nasc=" + dt_nasc + "]";
	}
}

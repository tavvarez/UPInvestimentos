package br.com.upinvestimentos.model;

import java.util.Date;

// Classe que representa um modelo de usuário
public class UsuarioModel {
	
    private int cd_user; // Identificador do usuário
    
    private String nm_user; // Nome do usuário
    
    private String ds_email; // Email do usuário
    
    private String nr_CPF; // Numero de CPF do usuário
    
    private Date dt_nasc; // Data de nascimento do usuário

    // Construtor padrão sem argumentos
	public UsuarioModel() {
		super();
	}

    // Construtor com dados obrigatórios (exceto a senha)
	public UsuarioModel(int cd_user, String nm_user, String ds_email, String nr_CPF, Date dt_nasc) {
		super();
		this.cd_user = cd_user;
		this.nm_user = nm_user;
		this.ds_email = ds_email;
		this.nr_CPF = nr_CPF;
		this.dt_nasc = dt_nasc;
	}

    // Métodos de acesso para o campo usuarioId
	public int getcd_user() {
		return cd_user;
	}

	public void setcd_user(int cd_user) {
		this.cd_user = cd_user;
	}

    // Métodos de acesso para o campo nome
	public String getnm_user() {
		return nm_user;
	}

	public void setnm_user(String nm_user) {
		this.nm_user = nm_user;
	}

    // Métodos de acesso para o campo CPF
	public String getnr_CPF() {
		return nr_CPF;
	}

	public void setnr_CPF(String nr_CPF) {
		this.nr_CPF = nr_CPF;
	}

    // Métodos de acesso para o campo email
	public String getds_email() {
		return nr_CPF;
	}

	
	 // Métodos de acesso para o campo dt_nasc
		public Date getdt_nasc() {
			return (dt_nasc);
		}

		public void setdt_nasc(Date dt_nasc) {
			this.dt_nasc = dt_nasc;
		}


    // Sobrescrita do método toString para exibir os detalhes do usuário
	@Override
	public String toString() {
		return "UsuarioModel [cd_user=" + cd_user + ", nm_user=" + nm_user + ", ds_email=" + ds_email + ", nr_CPF=" + nr_CPF
				+ ", dt_nasc=" + dt_nasc + "]";
	}
}

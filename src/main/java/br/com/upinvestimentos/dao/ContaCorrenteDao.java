package br.com.upinvestimentos.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.upinvestimentos.model.ContaCorrenteModel;
import br.com.upinvestimentos.model.UsuarioModel;

public class ContaCorrenteDao {

	
	private List<ContaCorrenteModel> ContasCorrentes;
	
	public ContaCorrenteDao() {
		ContasCorrentes = new ArrayList<>();
	}
	
	
	 // Listar todos as contas correntes
    public List<ContaCorrenteModel> listarContasCorrentes() {
        return ContasCorrentes;
    }

  	
	
	
	
}

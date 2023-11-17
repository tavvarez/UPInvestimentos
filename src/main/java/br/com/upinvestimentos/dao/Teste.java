package br.com.upinvestimentos.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.upinvestimentos.model.UsuarioModel;


public class Teste {

	public static void main(String[] args) throws Exception {

		System.out.println("********************************");
		System.out.println("CONEXÃO COM O BANCO DE DADOS");

		
//        GastosDAO daoConsulta = new GastosDAO(0, null, 0);
//        List<GastosDAO> listaControleGastosGeral = daoConsulta.getAll();
//
//        for (GastosDAO item : listaControleGastosGeral) {
//            System.out.println(item.getCdTransacao() + " " + item.getIdUser() + " " + item.getValorSaldo());
//        }

        System.out.println("********************************");
        System.out.println("CONEXÃO COM O BANCO DE DADOS");
        UsuarioModel usuarioModel = new UsuarioModel(); // Instancia um objeto UsuarioModel
        UsuarioDAO usuarioDao = new UsuarioDAO(1, "Gabriel Tavares", "111111111", null, "xxxx@gmail.com");
        usuarioDao.cadastrarUsuario(usuarioModel); // Agora está passando um objeto UsuarioModel

        
//        cd_user, nm_user, nr_CPF, dt_nasc, ds_email
        

	}
}

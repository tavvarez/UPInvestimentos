package br.com.upinvestimentos.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
        
        UsuarioModel usuarioModel = new UsuarioModel(2, "Gabriel Tavares", "111111111", null, "xxxx@gmail.com", 2);
        UsuarioDAO usuarioDao = new UsuarioDAO();
//        usuarioDao.cadastrarUsuario(usuarioModel); // Agora está passando um objeto UsuarioModel
        
//        usuarioDao.delUsuario(usuarioModel);
        
        try {
            System.out.println("Atualizando usuário com ID: " + usuarioModel.getCdUsuario());
            usuarioDao.atualizarUsuario(usuarioModel);
            System.out.println("Usuário atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário:");
            e.printStackTrace();
        }
        
        //usuarioDao.atualizarUsuario(usuarioModel);

        
//        cd_user, nm_user, nr_CPF, dt_nasc, ds_email
        

	}
}

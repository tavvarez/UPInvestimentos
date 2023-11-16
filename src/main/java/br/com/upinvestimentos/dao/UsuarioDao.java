/**
 * 
 */
package br.com.upinvestimentos.dao;
import java.util.ArrayList;
import java.util.List;

import br.com.upinvestimentos.model.UsuarioModel;

public class UsuarioDao {
	
    private List<UsuarioModel> cd_users;

    public UsuarioDao() {
    	cd_users = new ArrayList<>();
   //cd_users.add(new cd_userModel(1, "Usuário1", "usuario@example.com", "senha123", "admin"));
   //cd_users.add(new cd_userModel(2, "Usuário2", "usuario@example.com", "senha456", "user"));
    } 
   

    // Listar todos os usuários
    public List<UsuarioModel> listarcd_user() {
        return cd_users;
    }

    // Buscar um usuário por ID
    public UsuarioModel buscarcd_userPorId(int cd_user) {
        for (UsuarioModel nm_user : cd_users) {
            if (nm_user.getcd_user() == cd_user) {
                return nm_user;
            }
        }
        return null;
    }

}
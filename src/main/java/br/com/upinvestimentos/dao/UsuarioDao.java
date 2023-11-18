package br.com.upinvestimentos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import br.com.upinvestimentos.bd.ConexaoOracle;
import br.com.upinvestimentos.model.UsuarioModel;

public class UsuarioDAO {
	
	UsuarioModel usuarioModel = new UsuarioModel();
	
	public void UsuarioModel(int cdUsuario, String nomeUsuario, String numeroCPF, Date dataNasc, String descricaoEmail) {
		
	}

    public UsuarioDAO(int cdUsuario, String nomeUsuario, String numeroCPF, Date dataNasc, String descricaoEmail) {
    	
	}

	public UsuarioDAO() {
		
	}

	public void cadastrarUsuario(UsuarioModel usuario) throws Exception {
        Connection conexao = null;
        PreparedStatement stmt = null;
		
		
//CADASTRAR USUÁRIO
        try {
            conexao = ConexaoOracle.realizarConexao();
            String sql = "INSERT INTO T_USER (cd_user, nm_user, nr_CPF, dt_nasc, ds_email) VALUES (?,?,?,?,?)";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, usuario.getCdUsuario());
            stmt.setString(2, usuario.getNomeUsuario());
            stmt.setString(3, usuario.getNumeroCPF());
            java.sql.Date dataNascimentoSql = new java.sql.Date(new java.util.Date().getTime());
            stmt.setDate(4, dataNascimentoSql);
            stmt.setString(5, usuario.getDescricaoEmail());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Erro ao cadastrar usuário.");
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
}

//LISTAR USUÁRIOS
//
//    
//
//	public List<Usuario> listarUsuarios() throws DBException {
//        List<Usuario> usuarios = new ArrayList<>();
//        Connection conexao = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            conexao = ConnectionManager.getInstance().getConnection();
//            String sql = "SELECT cd_user, nm_user, nr_CPF, dt_nasc, ds_email FROM T_USER";
//            stmt = conexao.prepareStatement(sql);
//            rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                Usuario usuario = new Usuario();
//                usuario.setCodigo(rs.getLong("cd_user"));
//                usuario.setNome(rs.getString("nm_user"));
//                usuario.setCPF(rs.getString("nr_CPF"));
//                Calendar dataNascimento = Calendar.getInstance();
//                dataNascimento.setTime(rs.getDate("dt_nasc"));
//                usuario.setDataNascimento(dataNascimento);
//                usuario.setEmail(rs.getString("ds_email"));
//
//                usuarios.add(usuario);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new DBException("Erro ao listar usuários.");
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (stmt != null) {
//                    stmt.close();
//                }
//                if (conexao != null) {
//                    conexao.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return usuarios;
//    }
//
////BUSCAR POR CÓDIGO
//    @Override
//    public Usuario buscarUsuarioPorCodigo(long codigo) throws DBException {
//        Connection conexao = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        Usuario usuario = null;
//
//        try {
//            conexao = ConnectionManager.getInstance().getConnection();
//            String sql = "SELECT cd_user, nm_user, nr_CPF, dt_nasc, ds_email FROM T_USER WHERE cd_user = ?";
//            stmt = conexao.prepareStatement(sql);
//            stmt.setLong(1, codigo);
//            rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                usuario = new Usuario();
//                usuario.setCodigo(rs.getLong("cd_user"));
//                usuario.setNome(rs.getString("nm_user"));
//                usuario.setCPF(rs.getString("nr_CPF"));
//                Calendar dataNascimento = Calendar.getInstance();
//                dataNascimento.setTime(rs.getDate("dt_nasc"));
//                usuario.setDataNascimento(dataNascimento);
//                usuario.setEmail(rs.getString("ds_email"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new DBException("Erro ao buscar usuário por código.");
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (stmt != null) {
//                    stmt.close();
//                }
//                if (conexao != null) {
//                    conexao.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return usuario;
//    }
//
//    @Override
//    public void atualizarUsuario(Usuario usuario) throws DBException {
//        Connection conexao = null;
//        PreparedStatement stmt = null;
//
////ATUALIZAR USUÁRIO
//
//        try {
//            conexao = ConnectionManager.getInstance().getConnection();
//            String sql = "UPDATE T_USER SET nm_user = ?, nr_CPF = ?, dt_nasc = ?, ds_email = ? WHERE cd_user = ?";
//            stmt = conexao.prepareStatement(sql);
//            
//            stmt.setString(1, usuario.getNome());
//            stmt.setString(2, usuario.getCPF());
//            java.sql.Date dataNascimento = new java.sql.Date(usuario.getDataNascimento().getTimeInMillis());
//            stmt.setDate(3, dataNascimento);
//            stmt.setString(4, usuario.getEmail());
//            stmt.setLong(5, usuario.getCodigo());
//
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new DBException("Erro ao atualizar usuário.");
//        } finally {
//            try {
//                if (stmt != null) {
//                    stmt.close();
//                }
//                if (conexao != null) {
//                    conexao.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
////REMOVER USUARIO
//    @Override
//    public void removerUsuario(long codigo) throws DBException {
//        Connection conexao = null;
//        PreparedStatement stmt = null;
//
//        try {
//            conexao = ConnectionManager.getInstance().getConnection();
//            String sql = "DELETE FROM T_USER WHERE cd_user = ?";
//            stmt = conexao.prepareStatement(sql);
//            stmt.setLong(1, codigo);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new DBException("Erro ao remover usuário.");
//        } finally {
//            try {
//                if (stmt != null) {
//                    stmt.close();
//                }
//                if (conexao != null) {
//                    conexao.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}

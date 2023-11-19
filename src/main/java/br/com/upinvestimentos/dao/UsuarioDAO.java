package br.com.upinvestimentos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.Date;

import br.com.upinvestimentos.bd.ConexaoOracle;
import br.com.upinvestimentos.model.UsuarioModel;

public class UsuarioDAO {
	Connection conexao = null;
	
	UsuarioModel usuarioModel = new UsuarioModel();
	
	public void UsuarioModel(int cdUsuario, String nomeUsuario, String numeroCPF, Date dataNasc, String descricaoEmail) {
		
	}

    public UsuarioDAO(int cdUsuario, String nomeUsuario, String numeroCPF, Date dataNasc, String descricaoEmail) {
    	
	}

	public UsuarioDAO() {
		
	}

	public void cadastrarUsuario(UsuarioModel usuario) throws Exception {
        
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
	
//REMOVER USUARIO
		public void delUsuario(UsuarioModel usuario) throws SQLException {
			PreparedStatement stmt = null;

			try {
				conexao = ConexaoOracle.realizarConexao();
				String sql = "DELETE FROM T_USER WHERE cd_user = ?";
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, usuario.getCdUsuario()); // Primeiro parâmetro (T_USUARIO_ID_USUARIO)

				stmt.execute();

				conexao.commit(); // Confirma as alterações

			} catch (SQLException e) {
				// dar rollback em caso de exceção
				conexao.rollback();
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					conexao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	////ATUALIZAR USUÁRIO
		
	public void atualizarUsuario(UsuarioModel usuario) throws SQLException {
		PreparedStatement stmt = null;
		boolean transacaoAtiva = false;
		try {
			transacaoAtiva = true;
			conexao = ConexaoOracle.realizarConexao();
			String sql = "UPDATE T_USER SET nm_user = ?, nr_CPF = ?, dt_nasc = ?, ds_email = ? WHERE cd_user = ?";
			stmt = conexao.prepareStatement(sql);

			stmt.setString(1, usuario.getNomeUsuario());
			stmt.setString(2, usuario.getNumeroCPF());
			java.sql.Date data = new java.sql.Date(new java.util.Date().getTime());
			stmt.setDate(3, data);
			stmt.setString(4, usuario.getDescricaoEmail());
			stmt.setInt(5, usuario.getCdUsuario());

			int linhasAfetadas = stmt.executeUpdate();
			if (linhasAfetadas > 0) {
	            conexao.commit();
	        } else {
	            // Caso nenhuma linha seja afetada, pode ser útil fazer um rollback
	            conexao.rollback();
	        }
		} catch (SQLException e) {
			if (transacaoAtiva) {
				conexao.rollback();
			}
			e.printStackTrace();
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





}

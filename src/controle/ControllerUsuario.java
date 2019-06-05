package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import dao.Conexao;
import modelo.Usuario;

public class ControllerUsuario {
	
 	
	//RETORNANDO ID 
	public int retornoId() throws ClassNotFoundException, SQLException {
		
		Connection conexao = Conexao.conector();
		int idGerado = 0;
		String sql = "select max(idusuario)+1 from usuario";
		PreparedStatement stm = conexao.prepareStatement(sql);
		
		ResultSet rst = stm.executeQuery();
		
		if(rst.next()) {
			idGerado =  rst.getInt(1);
		}
		System.out.print(""+idGerado);
		return idGerado;			
	}
	
	//METODO BUSCAR POR ID
		public static Usuario buscarNotaPorId(int iduser) throws Exception {
			
			Usuario user = null;
			
			Connection conexao = Conexao.conector();
			String sql ="select * from usuario where idusuario = ?";
			
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, iduser);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				
				user = new Usuario();
				user.setId(rs.getInt("idusuario"));
				user.setNome(rs.getString("nome"));
				user.setLogin(rs.getString("login"));
				user.setSenha(rs.getString("senha"));
				user.setEmail(rs.getString("email"));
				
				System.out.print(user.getId() +" " +user.getNome()+" " + user.getLogin());
			}
			
			return user;
		}
	
	 //METODO LISTAR
	public List<Usuario> listarUsuario() throws Exception {
	
		List<Usuario> lista = new ArrayList<>();
		
		Connection conexao = Conexao.conector();
		String sql =" select * from usuario" ;
		
		PreparedStatement stm = conexao.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		
		while(rs.next()) {
			
			Usuario usuario = new Usuario();
			usuario.setId(rs.getInt("idusuario"));
			usuario.setLogin(rs.getString("login"));
			usuario.setSenha(rs.getString("senha"));
			usuario.setNome(rs.getString("nome"));
			usuario.setEmail(rs.getString("email"));
			lista.add(usuario);
		}
		
		return lista;
	}

	//VERIFICAR SE O USUARIO E SENHA EXISTE NO BANCO
	public static int verificar(Usuario dados) throws SQLException {
	
		int i = 0;
		Connection conexao = Conexao.conector();
		String sql = "select * from usuario where login=? and senha=?";
		PreparedStatement stm = conexao.prepareStatement(sql);
		
		
		stm.setString(1, dados.getLogin());
		stm.setString(2, dados.getSenha());
		ResultSet rs = stm.executeQuery();
		
		while(rs.next()) {
			if(rs.getString("login").equals(dados.getLogin()) && rs.getString("senha").equals(dados.getSenha())) {
	       System.out.print("Usuario logado com sucesso....");
			i=1;
			}
		}
	   return i;
	}
	
	
	//METODO INSERIR
	public static int inserir(Usuario dados) throws  SQLException, ClassNotFoundException {
	
		System.out.print("entrou");
		Connection conexao = Conexao.conector();
		String sql = "insert into usuario(nome, login, senha, email) values (?,?,?,?)";
		PreparedStatement stm = conexao.prepareStatement(sql);
		stm.setString(1, dados.getNome());
		stm.setString(2, dados.getLogin());
		stm.setString(3, dados.getSenha());
		stm.setString(4, dados.getEmail());
		stm.execute();
		return 1;
	}

	
	//METODO REMOVER USUARIO
	public static void removerUsuario(int idNota) throws Exception {
		    Connection conexao = Conexao.conector();
			
			String sql = "delete from usuario where idusuario = ?";
			
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, idNota);
		    stm.execute();
			
		}
	
	//METODO EDITAR USUARIO
	public static int editarUsuario(Usuario user) throws SQLException {

		Connection conexao = Conexao.conector();
		String sql = "UPDATE usuario SET login = ?, senha = ?, nome = ?, email = ? where idusuario = ?";
		PreparedStatement stm = conexao.prepareStatement(sql);
		stm.setString(1, user.getLogin());
		stm.setString(2, user.getSenha());
		stm.setString(3, user.getNome());
		stm.setString(4, user.getEmail());
		stm.setInt(5, user.getId());
		stm.execute();
		
		return 1;
	}

	 
	
}

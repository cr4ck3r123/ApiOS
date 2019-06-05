package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.Conexao;
import modelo.Mensagens;
import modelo.Usuario;

public class ControllerDados {

	
	 //METODO INSERIR MENSAGEM
		public static String adicionaMsg(String msg, String usuario) throws Exception {
		
			
			Connection conexao = Conexao.conector2();
			String sql ="insert into dados(mensagens, usuarios) values (?, ?)";
			
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setString(1, msg);
			stm.setString(2, usuario);
			stm.execute();
		
			return "ok";		
			}

		//METODO LISTAR MENSAGEM
		public List<Mensagens> listarMsg() throws SQLException {
			
			List<Mensagens> lista = new ArrayList<>();
			Connection conexao = Conexao.conector2();
			String sql = "select * from dados";
			
			PreparedStatement stm = conexao.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
						
			while(rs.next()) {
				
				Mensagens mensagens = new Mensagens();
				mensagens.setMensagens(rs.getString("mensagens"));
				mensagens.setUsuarios(rs.getString("usuarios"));
				lista.add(mensagens);
				
			}			
			
			return lista;
		}
		
		//METODO ADICIONAR 
		public static int addUser(Usuario user) throws Exception{
			
			Connection conexao = Conexao.conector();
		
			
			String sql = "insert into usuario(nome, login, senha, email) values (?, ?, ?, ?)";
			
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setString(1, user.getNome());
			stm.setString(2, user.getLogin());
			stm.setString(3, user.getSenha());
			stm.setString(4, user.getEmail());
			
			stm.execute();
			
			return 1;
		}
		
		
		
				
				
				
				
				
				
}

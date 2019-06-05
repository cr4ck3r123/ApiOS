package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Conexao;
import modelo.Cliente;
import modelo.Servico;


public class ControllerServico {
	
	// METODO ADICIONAR
		public static int adicionar(Servico servico) throws Exception {
		
			System.out.print("metodo inserir servico");
			Connection conexao = Conexao.conector();
		
				
			String sql = "insert into servicos(tiposervico, valor, quantidade) values (?, ?, ?)";
		   
		// SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			
		    PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setString(1, servico.getTipoServico());
		    stm.setDouble(2, servico.getValor());
		    stm.setInt(3, servico.getQtde());
			// stm.setDate(8, cliente.getDataCriado());
			stm.execute();

			return 1;
		}
		
		//RETORNANDO ID 
		public int retornoId() throws ClassNotFoundException, SQLException {
			
			Connection conexao = Conexao.conector();
			int idGerado = 0;
			String sql = "select max(idservicos)+1 from servicos";
			PreparedStatement stm = conexao.prepareStatement(sql);
			
			ResultSet rst = stm.executeQuery();
			
			if(rst.next()) {
				idGerado =  rst.getInt(1);
			}
			System.out.print(""+idGerado);
			return idGerado;			
		}
		
		
		// METODO LISTAR
		public List<Servico> listarServico() throws Exception {

			List<Servico> lista = new ArrayList<>();

			Connection conexao = Conexao.conector();
			String sql = " select * from servicos";
			PreparedStatement stm = conexao.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {

				Servico servico = new Servico();
				servico.setIdservicos(rs.getInt(1));
				servico.setTipoServico(rs.getString("tiposervico"));
				servico.setValor(rs.getDouble("valor"));
				servico.setQtde(rs.getInt(4));
				lista.add(servico);
			}

			return lista;
		}
		
		
		//Busca por ID
		public static Servico buscarPorId(int idServ) throws SQLException {
			
			Connection conexao = Conexao.conector();
			String sql = "SELECT * FROM servicos where idservicos = ?";

			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, idServ);

			ResultSet rs = stm.executeQuery();

			Servico servico = new Servico();
			
			if (rs.next()) {
				servico.setIdservicos(rs.getInt(1));
				servico.setTipoServico(rs.getString("tiposervico"));
				servico.setValor(rs.getDouble(3));
				servico.setQtde(rs.getInt(4));
				
		}
							
			return servico;
		
		}
		
		
	//METODO REMOVER SERVICO
		public static void remover(int id) throws Exception {
			    Connection conexao = Conexao.conector();
				
				String sql = "delete from servicos where idservicos = ?";
				
				PreparedStatement stm = conexao.prepareStatement(sql);
				stm.setInt(1, id);
			    stm.execute();
				
			}
		

}
		


package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Conexao;
import modelo.Cliente;
import modelo.Estoque;
import modelo.Veiculo;


public class ControllerEstoque {
	
	
	// METODO ADICIONAR
	public static int adicionar(Estoque estoque) throws Exception {
	
		System.out.print("metodo inserir servico");
		Connection conexao = Conexao.conector();
				
		String sql = "insert into estoque(descricao, qtde, valor) values (?, ?, ?)";	   
	// SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		
	    PreparedStatement stm = conexao.prepareStatement(sql);
		stm.setString(1, estoque.getDescricao());
	    stm.setInt(2, estoque.getQtde());
	    stm.setDouble(3, estoque.getValor());
		// stm.setDate(8, cliente.getDataCriado());
		stm.execute();

		return 1;
	}
	
	//METODO REMOVER SERVICO
	public static void remover(int id) throws Exception {
		    Connection conexao = Conexao.conector();
			
			String sql = "delete from estoque where idestoque = ?";
			
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, id);
		    stm.execute();
			
		}
	
	
	//RETORNANDO ID 
	public int retornoId() throws ClassNotFoundException, SQLException {
		
		Connection conexao = Conexao.conector();
		int idGerado = 0;
		String sql = "select max(idestoque)+1 from estoque";
		PreparedStatement stm = conexao.prepareStatement(sql);
		
		ResultSet rst = stm.executeQuery();
		
		if(rst.next()) {
			idGerado =  rst.getInt(1);
		}
		System.out.print(""+idGerado);
		return idGerado;			
	}
	
	
	// METODO LISTAR
		public List<Estoque> listarEstoque() throws Exception {

			List<Estoque> lista = new ArrayList<>();

			Connection conexao = Conexao.conector();
			String sql = " select * from estoque";
			PreparedStatement stm = conexao.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {

				Estoque estoque = new Estoque();
				estoque.setIdestoque(rs.getInt("idestoque"));
				estoque.setDescricao(rs.getString("descricao"));
				estoque.setQtde(rs.getInt(3));
				estoque.setValor(rs.getDouble(4));
			
				lista.add(estoque);
			}

			return lista;
		}
	
		
		//METODO RETORNA VEICULO POR ID
		public static Estoque pesqId(int id) throws Exception {
			  
			    Estoque estoque = null;
			    
			    Connection conexao = Conexao.conector();
				
				String sql = "select * from estoque where idestoque = ?";
				
				PreparedStatement stm = conexao.prepareStatement(sql);
				stm.setInt(1, id);
			    ResultSet rs = stm.executeQuery();
			    
			    while(rs.next()) {
					
				    estoque = new Estoque();
					estoque.setIdestoque(rs.getInt(1));
					estoque.setDescricao(rs.getString("descricao"));
					estoque.setQtde(rs.getInt("qtde"));
					estoque.setValor(rs.getDouble(4));
					
				
				//	System.out.print(endereco.getId() +" " +endereco.getLogradouro()+" " + endereco.getBairro());
				}
				return estoque;
		}
		
}

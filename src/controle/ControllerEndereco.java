package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dao.Conexao;
import modelo.Cliente;
import modelo.Endereco;
import modelo.Usuario;

public class ControllerEndereco {

	
	//METODO ADICIONAR ENDERECO
	public static int addEndereco(Endereco  endereco) throws Exception{
		
		Connection conexao = Conexao.conector();
			
		String sql = "select mydb.insere_endereco(?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement stm = conexao.prepareStatement(sql);
		
		stm.setString(1, endereco.getLogradouro());
		stm.setString(2, endereco.getComplemento());
		stm.setString(3, endereco.getBairro());
		stm.setInt(4, endereco.getNumero());
	 	stm.setString(5, endereco.getCep());
		stm.setString(6, endereco.getLocalidade());
		stm.setString(7, endereco.getUf());
	//	stm.setInt(8, endereco.getPessoa_idpessoa());
		//stm.setDate(8, cliente.getDataCriado());
		stm.execute();
		
		return 1;
	}
	
	//METODO LISTAR O OBJETO DE ENDEREÇO
			public static Endereco pesqEndereco(int idEndereco) throws Exception {
								
				Connection conexao = Conexao.conector();
				String sql ="select * from endereco where idpessoa = ?" ;
							
				PreparedStatement stm = conexao.prepareStatement(sql);
				stm.setInt(1, idEndereco);
				ResultSet rs = stm.executeQuery();
				
				Endereco endereco = new Endereco();
				while(rs.next()) {
					endereco.setId(rs.getInt(1));
					endereco.setLogradouro(rs.getString("logradouro"));
					endereco.setComplemento(rs.getString("complemento"));
					endereco.setBairro(rs.getString("bairro"));
					endereco.setNumero(rs.getInt(5));
					endereco.setCep(rs.getString("cep"));
					endereco.setLocalidade(rs.getString("localidade"));
					endereco.setUf(rs.getString("uf"));
					
				}
				
				return endereco;
			}
			
			//METODO LISTA DE ENDEREÇO DO ID
			public static List<Endereco> listarEndereco(int idEndereco) throws Exception {
				  
				    Endereco endereco = null;
				    List<Endereco> lista = new ArrayList<>();
				    Connection conexao = Conexao.conector();
					
					String sql = "select * from endereco where idpessoa = ?";
					
					PreparedStatement stm = conexao.prepareStatement(sql);
					stm.setInt(1, idEndereco);
				    ResultSet rs = stm.executeQuery();
				    
				    while(rs.next()) {
						
						endereco = new Endereco();
						endereco.setId(rs.getInt("idendereco"));
						endereco.setLogradouro(rs.getString("logradouro"));
						endereco.setComplemento(rs.getString("complemento"));
						endereco.setBairro(rs.getString("bairro"));
						endereco.setNumero(rs.getInt(5));
						endereco.setCep(rs.getString("cep"));
						endereco.setLocalidade(rs.getString("localidade"));
						endereco.setUf(rs.getString("uf"));						
						endereco.setPessoa_idpessoa(rs.getInt(9));
						lista.add(endereco);
						System.out.print(endereco.getId() +" " +endereco.getLogradouro()+" " + endereco.getBairro());
					}
					
					return lista;

				}
			
			//EDITA ENDEREÇO
			public static int editarEndereco(Endereco  endereco) throws Exception{
				System.out.print("metodo editar cliente");
				Connection conexao = Conexao.conector();
							
				String sql = "UPDATE endereco  SET logradouro = ? , numero = ?, complemento = ?, cep = ?, bairro = ?, localidade = ?, uf = ? where idpessoa = ?";
				
				PreparedStatement stm = conexao.prepareStatement(sql);
				
				stm.setString(1, endereco.getLogradouro());
				stm.setInt(2, endereco.getNumero());
				stm.setString(3, endereco.getComplemento());
			 	stm.setString(4, endereco.getCep());
				stm.setString(5, endereco.getBairro());
				stm.setString(6, endereco.getLocalidade());
				stm.setString(7, endereco.getUf());
				stm.setInt(8, endereco.getId());
			//	stm.setInt(9, endereco.getPessoa_idpessoa());
				stm.execute();
				
				return 1;
			}
			
			
			//RETORNANDO ID ENDERECO
			public int retornoIdEndereco() throws ClassNotFoundException, SQLException {
				
				Connection conexao = Conexao.conector();
				int idGerado = 0;
				String sql = "select max(idendereco)+1 from endereco";
				PreparedStatement stm = conexao.prepareStatement(sql);
				
				ResultSet rst = stm.executeQuery();
				
				if(rst.next()) {
					idGerado =  rst.getInt(1);
				}
				System.out.print(""+idGerado);
				return idGerado;			
			}
			
			
}

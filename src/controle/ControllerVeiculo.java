package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Conexao;
import modelo.Cliente;
import modelo.Endereco;
import modelo.Veiculo;

public class ControllerVeiculo {
	
	
	
	
	//METODO ADICIONAR ENDERECO
		public static int addVeiculo(Veiculo  veiculo) throws Exception{
			System.out.print("metodo inserir Veiculo");
			Connection conexao = Conexao.conector();
					
			String sql = "select mydb.insere_veiculo(?, ?, ?, ?, ?, ?)";
			
			PreparedStatement stm = conexao.prepareStatement(sql);
			
			stm.setString(1, veiculo.getMarca());
			stm.setString(2, veiculo.getModelo());
		 	stm.setString(3, veiculo.getAno());
			stm.setString(4, veiculo.getCor());
			stm.setString(5, veiculo.getPlaca());
			stm.setDouble(6, veiculo.getKm());
			//stm.setInt(7, veiculo.getPessoa_idpessoa());
			//stm.setDate(8, cliente.getDataCriado());
			stm.execute();
			
			return 1;
		}
		
		
		
		
		//METODO RETORNA VEICULO DO CLIENTE
		public static Veiculo pesqVeiculo(int idUser) throws Exception {
			  
			    Veiculo veiculo = null;
			    
			   Connection conexao = Conexao.conector();
				
			 
				String sql = "select * from veiculo where idpessoa = ? ";
				PreparedStatement stm = conexao.prepareStatement(sql);
				stm.setInt(1, idUser);
			    ResultSet rs = stm.executeQuery();
			    
			    while(rs.next()) {
					
				    veiculo = new Veiculo();
					veiculo.setIdveiculo(rs.getInt(1));
					veiculo.setMarca(rs.getString("marca"));
					veiculo.setModelo(rs.getString("modelo"));
					veiculo.setAno(rs.getString("ano"));
					veiculo.setCor(rs.getString("cor"));
					veiculo.setPlaca(rs.getString("placa"));
					veiculo.setKm(rs.getInt(7));
					veiculo.setPessoa_idpessoa(rs.getInt(8));
				
				//	System.out.print(endereco.getId() +" " +endereco.getLogradouro()+" " + endereco.getBairro());
				}
				
				return veiculo;

			}
		
		
		//METODO LISTA VEICULO
		public static List<Veiculo> listaVeiculo(int idUser) throws Exception {
			  
			    
			    List<Veiculo> lista = new ArrayList<>();
			    Connection conexao = Conexao.conector();
				
				String sql = "select * from veiculo where veiculo.idpessoa= ?";
				
				PreparedStatement stm = conexao.prepareStatement(sql);
				stm.setInt(1, idUser);
			    ResultSet rs = stm.executeQuery();
			    Veiculo veiculo = null;
			    
			    while(rs.next()) {					
				    veiculo = new Veiculo();
					veiculo.setIdveiculo(rs.getInt(1));
					veiculo.setMarca(rs.getString("marca"));
					veiculo.setModelo(rs.getString("modelo"));
					veiculo.setAno(rs.getString("ano"));
					veiculo.setCor(rs.getString("cor"));
					veiculo.setPlaca(rs.getString("placa"));
					veiculo.setKm(rs.getInt(7));
					veiculo.setPessoa_idpessoa(rs.getInt(8));
				lista.add(veiculo);
				//	System.out.print(endereco.getId() +" " +endereco.getLogradouro()+" " + endereco.getBairro());
				}
				
				return lista;

			}
		
		//METODO EDITAR VEICULO
				public static int editarVeiculo(Veiculo veiculo) throws SQLException {

					Connection conexao = Conexao.conector();
					String sql = "UPDATE veiculo SET marca = ?, modelo = ?, ano = ?, cor = ?, placa = ? , km = ?  where idveiculo = ?";
					PreparedStatement stm = conexao.prepareStatement(sql);
					stm.setString(1, veiculo.getMarca());
					stm.setString(2, veiculo.getModelo());
					stm.setString(3, veiculo.getAno());
					stm.setString(4, veiculo.getCor());
					stm.setString(5, veiculo.getPlaca());
					stm.setDouble(6, veiculo.getKm());
					stm.setInt(7, veiculo.getIdveiculo());
					stm.execute();
					
					return 1;
				}
				
				
				//METODO RETORNA VEICULO POR ID
				public static Veiculo pesqVeiculoId(int idVeiculo) throws Exception {
					  
					    Veiculo veiculo = null;
					    
					    Connection conexao = Conexao.conector();
						
						String sql = "select * from veiculo where veiculo.idveiculo = ?";
						
						PreparedStatement stm = conexao.prepareStatement(sql);
						stm.setInt(1, idVeiculo);
					    ResultSet rs = stm.executeQuery();
					    
					    while(rs.next()) {
							
						    veiculo = new Veiculo();
							veiculo.setIdveiculo(rs.getInt(1));
							veiculo.setMarca(rs.getString("marca"));
							veiculo.setModelo(rs.getString("modelo"));
							veiculo.setAno(rs.getString("ano"));
							veiculo.setCor(rs.getString("cor"));
							veiculo.setPlaca(rs.getString("placa"));
							veiculo.setKm(rs.getInt(7));
							veiculo.setPessoa_idpessoa(rs.getInt(8));
						
						//	System.out.print(endereco.getId() +" " +endereco.getLogradouro()+" " + endereco.getBairro());
						}
						return veiculo;
				}
				
				//RETORNANDO ID VEICULO
				public int retornoIdVeiculo() throws ClassNotFoundException, SQLException {
					
					Connection conexao = Conexao.conector();
					int idGerado = 0;
					String sql = "select max(idveiculo)+1 from veiculo";
					PreparedStatement stm = conexao.prepareStatement(sql);
					
					ResultSet rst = stm.executeQuery();
					
					if(rst.next()) {
						idGerado =  rst.getInt(1);
					}
					System.out.print(""+idGerado);
					return idGerado;			
				}
				
}

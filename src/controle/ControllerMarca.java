package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.google.gson.Gson;

import dao.Conexao;
import modelo.Endereco;
import modelo.Marca;
import modelo.Modelo;
import modelo.Usuario;

public class ControllerMarca {
	
	
	//METODO LISTAR MARCA
	public List<Marca> listarMarca() throws Exception {
	
		List<Marca> lista = new ArrayList<>();
		
		Connection conexao = Conexao.conector();
		String sql =" select * from marcas" ;
					
		PreparedStatement stm = conexao.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		
		while(rs.next()) {
			
			Marca marca = new Marca();
			marca.setId(rs.getInt(1));
			marca.setNome(rs.getString("nome"));
			lista.add(marca);
		}
		
		return lista;
	}
	
	
	//METODO RETORNA UMA LISTA
		public List<Marca> pegaId(String nome) throws Exception {
			
			List<Marca> lista = new ArrayList<>();
					
			Connection conexao = Conexao.conector();
			String sql =" select id, nome from marcas where nome = ?";
			Marca marca = new Marca();			
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setString(1, nome);
			ResultSet rs = stm.executeQuery();
			//stm.execute();
			while(rs.next()) {
				marca.setId(rs.getInt(1));
				marca.setNome(rs.getString("nome"));
				
				lista.add(marca);
			}
			
			
			return lista;
		}
	
		
		//METODO RETORNA UM OBJETO MARCA
				public Marca pegaIdMarca(String nome) throws Exception {
					
					Marca m = new Marca();
							
					Connection conexao = Conexao.conector();
					String sql =" select id, nome from marcas where nome = ?";
						
					PreparedStatement stm = conexao.prepareStatement(sql);
					stm.setString(1, nome);
					ResultSet rs = stm.executeQuery();
					//stm.execute();
					while(rs.next()) {
						m.setId(rs.getInt(1));
						m.setNome(rs.getString("nome"));
												
					}
					
					
					return m;
				}

				
				//METODO LISTAR MODELO
				public List<Modelo> listarModelo(int id) throws Exception {
				
					List<Modelo> lista = new ArrayList<>();
					
					Connection conexao = Conexao.conector();
					String sql =" select * from modelos where idmarca = ?" ;
								
					PreparedStatement stm = conexao.prepareStatement(sql);
					stm.setInt(1, id);
					ResultSet rs = stm.executeQuery();
					
					while(rs.next()) {
						
						Modelo modelo = new Modelo();
						modelo.setId(rs.getInt(1));
						modelo.setIdmarca(rs.getInt(2));
						modelo.setNome(rs.getString("nome"));
						lista.add(modelo);
						
					}
					
					return lista;
				}

}

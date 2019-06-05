package controle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional.TxType;

import dao.Conexao;
import modelo.Cliente;
import modelo.Usuario;

public class ControllerCliente {
	
	
	//RETORNANDO ID PESSOA
	public int retornoIdPessoa() throws ClassNotFoundException, SQLException {
		
		Connection conexao = Conexao.conector();
		int idGerado = 0;
		String sql = "select max(idpessoa)+1 from pessoa";
		PreparedStatement stm = conexao.prepareStatement(sql);
		
		ResultSet rst = stm.executeQuery();
		
		if(rst.next()) {
			idGerado =  rst.getInt(1);
		}
		System.out.print(""+idGerado);
		return idGerado;			
	}

	// METODO LISTAR
	public List<Cliente> listarCliente() throws Exception {

		List<Cliente> lista = new ArrayList<>();

		Connection conexao = Conexao.conector();
		String sql = " select * from pessoa";
		PreparedStatement stm = conexao.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();

		while (rs.next()) {

			Cliente cliente = new Cliente();
			cliente.setId(rs.getInt(1));
			cliente.setNome(rs.getString("nome"));
			cliente.setDataNasc(rs.getDate(3));
			cliente.setCpf(rs.getString("cpf"));
			cliente.setRg(rs.getString("rg"));
			cliente.setTelefone(rs.getString("telefone"));
			cliente.setCelular(rs.getString("celular"));
			cliente.setEmail(rs.getString("email"));
			cliente.setDataCriado(rs.getDate("dataCriado"));
			lista.add(cliente);
		}

		return lista;
	}

	// METODO ADICIONAR
	public static int addClient(Cliente cliente) throws Exception {
	
		
		Connection conexao = Conexao.conector();
	
			
		String sql = "insert into pessoa(nome, datanascimento, cpf, rg, telefone, celular, email, datacriado) values (?, ?, ?, ?, ?, ?, ?, curdate())";
	   
	    PreparedStatement stm = conexao.prepareStatement(sql);
		stm.setString(1, cliente.getNome());
		stm.setDate(2, cliente.getDataNasc());
		stm.setString(3, cliente.getCpf());
		stm.setString(4, cliente.getRg());
		stm.setString(5, cliente.getTelefone());
		stm.setString(6, cliente.getCelular());
		stm.setString(7, cliente.getEmail());
	    stm.execute();

		return 0;
	}

	// METODO BUSCAR POR ID
	public static Cliente buscarClientePorId(int idClient) throws Exception {
		
		Connection conexao = Conexao.conector();
		String sql = "SELECT * FROM pessoa WHERE idpessoa = ?";

		PreparedStatement stm = conexao.prepareStatement(sql);
		stm.setInt(1, idClient);

		ResultSet rs = stm.executeQuery();

		Cliente client = new Cliente();
		if (rs.next()) {
			client.setId(rs.getInt("idpessoa"));
			client.setNome(rs.getString("nome"));
			client.setDataNasc(rs.getDate("datanascimento"));
			client.setCpf(rs.getString("cpf"));
			client.setRg(rs.getString("rg"));
			client.setTelefone(rs.getString("telefone"));
			client.setCelular(rs.getString("celular"));
			client.setEmail(rs.getString("email"));

			
		}

		return client;
	}

	//METODO REMOVER CLIENTE
	public static void removerCliente(int idCliente) throws Exception {
		    Connection conexao = Conexao.conector();
			
			String sql = "select mydb.`delete`(?)";
			
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, idCliente);
		    stm.executeQuery();
			
		}
	
	//METODO EDITAR CLIENTE
		public static int editarCliente(Cliente cliente) throws SQLException {

			Connection conexao = Conexao.conector();
			String sql = "UPDATE pessoa SET nome = ?, datanascimento = ?, cpf = ?, rg = ?, telefone = ? , celular = ?, email = ? where idpessoa = ?";
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setString(1, cliente.getNome());
			stm.setDate(2, cliente.getDataNasc());
			stm.setString(3, cliente.getCpf());
			stm.setString(4, cliente.getRg());
			stm.setString(5, cliente.getTelefone());
			stm.setString(6, cliente.getCelular());
			stm.setString(7, cliente.getEmail());
			stm.setInt(8, cliente.getId());
			stm.execute();
			
			return 1;
		}

}

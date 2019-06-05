package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Conexao;
import modelo.Cliente;
import modelo.Funcionario;

public class ControllerFuncionario {
	
	
	// METODO ADICIONAR
	public static int adicionar(Funcionario funcionario) throws Exception {
	
		
		Connection conexao = Conexao.conector();
	
			
		String sql = "insert into funcionario(nome, datanascimento, cpf, rg, telefone, celular, email, datacriado, funcao) values (?, ?, ?, ?, ?, ?, ?, curdate(), ?)";
	   
	    PreparedStatement stm = conexao.prepareStatement(sql);
		stm.setString(1, funcionario.getNome());
		stm.setDate(2, funcionario.getDatanascimento());
		stm.setString(3, funcionario.getCpf());
		stm.setString(4, funcionario.getRg());
		stm.setString(5, funcionario.getTelefone());
		stm.setString(6, funcionario.getCelular());
		stm.setString(7, funcionario.getEmail());
		stm.setString(8, funcionario.getFuncao());
	    stm.execute();

		return 1;
	}

	// METODO LISTAR
	public List<Funcionario> listar() throws Exception {

		List<Funcionario> lista = new ArrayList<>();

		Connection conexao = Conexao.conector();
		String sql = " select * from funcionario";
		PreparedStatement stm = conexao.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();

		while (rs.next()) {

			Funcionario funcionario = new Funcionario();
			funcionario.setIdfuncionario(rs.getInt(1));
			funcionario.setNome(rs.getString("nome"));
			funcionario.setEmail(rs.getString("email"));
			funcionario.setFuncao(rs.getString("funcao"));
			funcionario.setDatanascimento(rs.getDate(6));
			funcionario.setCpf(rs.getString("cpf"));
			funcionario.setRg(rs.getString("rg"));
			funcionario.setTelefone(rs.getString("telefone"));
			funcionario.setCelular(rs.getString("celular"));
			
			
			lista.add(funcionario);
		}

		return lista;
	}
	

	//RETORNANDO ID PESSOA
		public int retornoId() throws ClassNotFoundException, SQLException {
			
			Connection conexao = Conexao.conector();
			int idGerado = 0;
			String sql = "select max(idfuncionario)+1 from funcionario";
			PreparedStatement stm = conexao.prepareStatement(sql);
			
			ResultSet rst = stm.executeQuery();
			
			if(rst.next()) {
				idGerado =  rst.getInt(1);
			}
			System.out.print(""+idGerado);
			return idGerado;			
		}
	
	
	

}

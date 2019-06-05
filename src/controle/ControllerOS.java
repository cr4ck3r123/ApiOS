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
import modelo.Veiculo;

public class ControllerOS {
	
	
	//Metodo para pesquisar clientes pelo nome com filtro
    public List<Cliente> listarCliente(String nome) throws SQLException {
    	
    	Connection conexao = Conexao.conector();
    	
        String sql = "select pessoa.idpessoa, pessoa.nome from pessoa where nome like ?";
              
        List<Cliente> lista = new ArrayList<>();
             PreparedStatement pst = conexao.prepareStatement(sql);
             pst.setString(1, nome + "%");
             System.out.print(nome);
             ResultSet rs;            
             rs = pst.executeQuery();
            
        	while(rs.next()) {        		
        		Cliente cliente = new Cliente();
        		cliente.setId(rs.getInt(1));
    			cliente.setNome(rs.getString("nome"));    		
        		lista.add(cliente);    		    		
        	} 
        	return lista;
       
    }

    
    //LISTA FUNCIONARIO
    public List<Funcionario> listar() throws SQLException {
    		
    	List<Funcionario> lista = new ArrayList<>();
    	Connection conexao = Conexao.conector();
    	
    	String sql = "select * from funcionario";
    	PreparedStatement stm = conexao.prepareStatement(sql);
    	ResultSet rs = stm.executeQuery();
    	
    	while(rs.next()) {
    		
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
    
    
    //LISTA VEICULO
    public List<Veiculo> listarVeiculo(int id) throws SQLException{
    	List<Veiculo> lista = new ArrayList<>();
    	
    	Connection conexao = Conexao.conector();
    	String sql = "select idveiculo, modelo from veiculo where idpessoa = ?";
    	PreparedStatement pst = conexao.prepareStatement(sql);
    	pst.setInt(1, id);
    	ResultSet rs = pst.executeQuery();
    	
    	
    	while (rs.next()) {
    		Veiculo veiculo = new Veiculo();
			veiculo.setIdveiculo(rs.getInt(1));
    		veiculo.setModelo(rs.getString("modelo"));
    		lista.add(veiculo);
			
		}
    	    	
    	return lista;
    }

    
    //RETORNA ID
    public int retornaId() throws SQLException {
    	int id = 0;
    	Connection conexao = Conexao.conector();
    	String sql = "select max(idordemservico)+1 from ordemservico";
    	PreparedStatement pst = conexao.prepareStatement(sql);
    	ResultSet rst = pst.executeQuery();
    	
    	while(rst.next()) {
    		id = rst.getInt(1);
    	}    	
    	return id;
    }
    
}

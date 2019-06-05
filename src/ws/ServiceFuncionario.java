package ws;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controle.ControllerCliente;
import controle.ControllerFuncionario;
import modelo.Cliente;
import modelo.Funcionario;

@Path("funcionario")
public class ServiceFuncionario {
	
	
	// SERVIÇO INSERIR 
		@POST
		@Path("adicionar")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public int inserir(Funcionario funcionario) throws Exception {
			int i = 0;
			Gson gson = new Gson();
			gson.toJson(funcionario, Funcionario.class);

			if (ControllerFuncionario.adicionar(funcionario) == 1) {
				i = 1;
			}

			return i;
		}

	//----------------- METODO LISTAR CLIENTE
		@GET
		@Path("/listar")
		public String listar() throws Exception {
			System.out.print("Metodo Listar");

			List<Funcionario> lista;

			ControllerFuncionario dao = new ControllerFuncionario();
			lista = dao.listar();

			// Converter para Gson
			Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			return g.toJson(lista);
		}
		
		
		 //METODO PARA RETORNAR ID 
	   	@GET
	   	@Path("/id")
	    public int retornarId() throws ClassNotFoundException, SQLException {
	       	
	       	int id = 0;
	                      
	           ControllerFuncionario dao = new ControllerFuncionario();
	           id = dao.retornoId();
	            	
	       	return id;
	       }

}

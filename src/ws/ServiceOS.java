package ws;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;
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
import controle.ControllerOS;
import modelo.Cliente;
import modelo.Funcionario;
import modelo.OS;
import modelo.Veiculo;


@Path("os")
public class ServiceOS {
	
	
	
	@GET
	@Path("teste")
	public void teste() {
		System.out.print("os");
	}
	
	
	 // METODO LISTAR FUNCIONARIO
 	@GET
 	@Path("listar")
 	@Consumes("application/json")
 	public String listar() throws Exception {
 		System.out.print("Metodo Listar ");

 		List<Funcionario> lista;

 		ControllerFuncionario dao = new ControllerFuncionario();
 		lista = dao.listar();

 		// Converter para Gson
 		Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
 		return g.toJson(lista);
 	}

 	
	 // METODO LISTAR FUNCIONARIO
	@GET
	@Path("listarCliente/{nome}")
	@Consumes("application/json")
	public String listarCliente(@javax.ws.rs.PathParam("nome") String nome) throws Exception {
		System.out.print("Metodo Listar ");

		List<Cliente> lista;

		ControllerOS dao = new ControllerOS();
		lista = dao.listarCliente(nome);
		System.out.print(nome);
		// Converter para Gson
		Gson g = new Gson();
		return g.toJson(lista);
	}
	
	 // METODO LISTAR MODELO
	@GET
	@Path("listarModelo/{id}")
	@Consumes("application/json")
	public String listarModelo(@javax.ws.rs.PathParam("id") int id) throws Exception {
		System.out.print("Metodo Listar ");

		List<Veiculo> lista;

		ControllerOS dao = new ControllerOS();
		lista = dao.listarVeiculo(id);
		
		// Converter para Gson
		Gson g = new Gson();
		return g.toJson(lista);
	}
	
	
	// METODO ID
	@GET
	@Path("id")
	public int retornaId() throws SQLException {
		ControllerOS os = new ControllerOS();
		int id = os.retornaId();	
		return id;
	}
	
	
	//METODO INSERIR OS
	@GET
	@Path("idFuncionario/{funcionario}")
	@Consumes("application/json")
	public int idFuncionario(@javax.ws.rs.PathParam("funcionario") String nome) throws SQLException{
		Gson g = new Gson();
		ControllerOS  os = new ControllerOS();
		int id = os.retornoIdFun(nome);	
		
		return id;
		
	}
	
	
	//-------------- ADICIONA OS
	@POST
	@Path("/adicionar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public int adicionar(OS os) {
		int i = 0;
		try {

			if (ControllerOS.addOS(os) == i) {
				i = 1;
				System.out.print("ok");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return i;
	}
	
}

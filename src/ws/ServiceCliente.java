package ws;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import controle.ControllerCliente;
import modelo.Cliente;

@Path("cliente")
public class ServiceCliente {
	

	@GET
	@Path("teste")
	@Consumes("application/json")
	public String teste() {
		
		System.out.print("Funcionando web service");
		
		return "Ok";
	}
		
	 //METODO PARA RETORNAR ID CLIENTE
   	@GET
   	@Path("/id")
    public int retornarId() throws ClassNotFoundException, SQLException {
       	
       	int id = 0;
                      
           ControllerCliente dao = new ControllerCliente();
           id = dao.retornoIdPessoa();
            	
       	return id;
       }

	//-------------- ADICIONA CLIENTE
	@POST
	@Path("/adicionar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public int adicionar(Cliente cliente) {
		int i = 0;
		try {

			if (ControllerCliente.addClient(cliente) == i) {
				i = 1;
				System.out.print("ok");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return i;
	}
	
	
	

	//----------------- METODO LISTAR CLIENTE
		@GET
		@Path("/listar")
		public String listar() throws Exception {
			System.out.print("Metodo Listar");

			List<Cliente> lista;

			ControllerCliente dao = new ControllerCliente();
			lista = dao.listarCliente();

			// Converter para Gson
			Gson g = new Gson();
			return g.toJson(lista);
		}
		
//------------------- SERVIÇO DELETE CLIENTE
		@DELETE
		@Path("delete/{idcliente}/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String remover(@javax.ws.rs.PathParam("idcliente") int idCliente) throws Exception {
			String msg = "";
			try {
				ControllerCliente.removerCliente(idCliente);
				msg = "Cliente removido com sucesso!!!";
			} catch (SQLException e) {
				msg = "Cliente não removido";
				e.printStackTrace();
				System.out.print(e);
			}

			return msg;
		}

//------------------------- SERVIÇO UPDATE CLIENTE
		@PUT
		@Path("/update")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String update(Cliente dados) throws ClassNotFoundException, SQLException {
			String msg = "";
			Gson gson = new Gson();
			gson.toJson(dados, Cliente.class);

			if (ControllerCliente.editarCliente(dados) == 1) {
				msg = "Cliente alterado com sucesso";
			}

			return msg;
		}
		
// SERVIÇO PESQUISA PESSOA E RETORNOA UM JSON
		@GET
		@Path("pesquisar/{id}/")
		@Produces(MediaType.TEXT_PLAIN)
		@Consumes(MediaType.APPLICATION_JSON)
		public String buscarPorId(@PathParam("id") int idClient) {
			Gson gson = new Gson();
			Cliente cliente = null;
			try {
				cliente = ControllerCliente.buscarClientePorId(idClient);

			} catch (Exception e) {

				e.printStackTrace();
			}
			
			return gson.toJson(cliente, Cliente.class);
		}
	
}

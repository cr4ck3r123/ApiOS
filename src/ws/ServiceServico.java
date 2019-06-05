package ws;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import controle.ControllerCliente;
import controle.ControllerServico;
import modelo.Cliente;
import modelo.Servico;

@Path("servico")
public class ServiceServico {

	
	
	  //METODO PARA RETORNAR ID SERVICO
 	@GET
 	@Path("id")
  public int id() throws ClassNotFoundException, SQLException {
     	
     	int id = 0;
     	System.out.print("Metodo Retornar ID");
               
         ControllerServico dao = new ControllerServico();
         id = dao.retornoId();
      	
     	
     	return id;
     }
	
//ADICIONA SERVICO  
    @POST
	@Path("adicionar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String adicionar(Servico servico) {
		String msg = "";
		try {
			
			
			 if (ControllerServico.adicionar(servico) == 1) {
				 msg = "servico inserido com sucesso!!!";
			}				
			System.out.print(msg);
		} catch (Exception e) {
			msg = "Erro ao add um servico!";
			e.printStackTrace();
		}

		return msg;
	}
  
 	//METODO LISTAR SERVICOS
	@GET
	@Path("listar")
	public String listar() throws Exception {
		System.out.print("Metodo Listar");
		
      List<Servico> lista;
      
      ControllerServico dao = new ControllerServico();
      lista = dao.listarServico();
      
      //Converter para Gson
      Gson g = new Gson();
      return g.toJson(lista);
	}
	
	// SERVIÇO PESQUISA PESSOA E RETORNOA UM JSON
			@GET
			@Path("pesquisar/{id}/")
			public String buscarPorId(@PathParam("id") int id) {
				Gson gson = new Gson();
				Servico servico = null;
				try {
					servico = ControllerServico.buscarPorId(id);

				} catch (Exception e) {

					e.printStackTrace();
				}
				
				return gson.toJson(servico, Servico.class);
			}
			
//------------------- SERVIÇO DELETE CLIENTE
			@DELETE
			@Path("delete/{id}/")
		    public String remover(@javax.ws.rs.PathParam("id") int id) throws Exception {
				String msg = "";
			
				try {
					ControllerServico.remover(id);
					msg = "Cliente removido com sucesso!!!";
				} catch (SQLException e) {
					msg = "Cliente não removido";
					e.printStackTrace();
					System.out.print(e);
				}

				return msg;
			}
}

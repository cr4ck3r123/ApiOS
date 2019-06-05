package ws;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import controle.ControllerEstoque;
import controle.ControllerVeiculo;
import modelo.Estoque;
import modelo.Veiculo;

@Path("estoque")
public class ServicoEstoque {
	
	  //METODO PARA RETORNAR ID ESTOQUE
	 	@GET
	 	@Path("id")
	  public int id() throws ClassNotFoundException, SQLException {
	     	
	     	int id = 0;
	     	System.out.print("Metodo Retornar ID");
	               
	         ControllerEstoque dao = new ControllerEstoque();
	         id = dao.retornoId();      	
	     	
	     	return id;
	     }
	 	
	 	//ADICIONA SERVICO  
	    @POST
		@Path("adicionar")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String adicionar(Estoque estoque) {
			String msg = "";
			try {
								
				 if (ControllerEstoque.adicionar(estoque) == 1) {
					 msg = "estoque inserido com sucesso!!!";
				}				
				System.out.print(msg);
			} catch (Exception e) {
				msg = "Erro ao add um estoque!";
				e.printStackTrace();
			}

			return msg;
		}
	  
	    
	  //METODO LISTAR SERVICOS
		@GET
		@Path("listar")
		public String listar() throws Exception {
			System.out.print("Metodo Listar");
			
	      List<Estoque> lista;
	      
	      ControllerEstoque dao = new ControllerEstoque();
	      lista = dao.listarEstoque();
	      
	      //Converter para Gson
	     Gson g = new Gson();
	      return g.toJson(lista);
		}
		
		//SERVIÇO PESQUISA POR ID
		  
		@GET
		@Path("pesquisar/{id}/")
		public String buscarPorIdVeiculo(@PathParam("id") int id) {
			
			Gson gson = new Gson();
			Estoque estoque = new Estoque();
			try {
				estoque = ControllerEstoque.pesqId(id);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			return gson.toJson(estoque);
		}
		
	

}

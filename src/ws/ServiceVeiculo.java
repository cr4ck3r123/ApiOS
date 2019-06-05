package ws;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import controle.ControllerMarca;
import controle.ControllerVeiculo;
import modelo.Marca;
import modelo.Modelo;
import modelo.Veiculo;


@Path("veiculo")
public class ServiceVeiculo {

	
	  //SERVIÇO INSERIR VEICULO
    @POST
	@Path("inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String inserirVeiculo(Veiculo veiculo) throws Exception {
		String msg = "";
		Gson gson = new Gson();	
		gson.toJson(veiculo, Veiculo.class);
		
		if(controle.ControllerVeiculo.addVeiculo(veiculo) == 1) {
			msg="veiculo Inserido com sucesso";
		}
	
		return msg;
	}
	
	//SERVIÇO PESQUISA VEICULO 
	@GET
	@Path("idVeiculo/{id}/")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String buscarPorIdVeic(@PathParam("id") int idVeiculo) {
		Gson gson = new Gson();
		Veiculo veiculo = null;
		try {
			veiculo = ControllerVeiculo.pesqVeiculoId(idVeiculo);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		System.out.println(""+idVeiculo);
		return gson.toJson(veiculo);
	}
	
	//SERVIÇO PESQUISA VEICULO POR ID
	  
	@GET
	@Path("pesquisar/{id}/")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String buscarPorIdVeiculo(@PathParam("id") int idUser) {
		
		Gson gson = new Gson();
		Veiculo veiculo = null;
		try {
			veiculo = ControllerVeiculo.pesqVeiculo(idUser);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return gson.toJson(veiculo);
	}


	//METODO LISTA VEICULO
	@GET
	@Path("listar/{id}")
	public String listaVeiculo(@PathParam("id") int idVeiculo) throws Exception {
		System.out.print("Metodo Listar");
		
        List<Veiculo> lista;
        
        ControllerVeiculo dao = new ControllerVeiculo();
        lista = dao.listaVeiculo(idVeiculo);
        
        //Converter para Gson
        Gson g = new Gson();
        return g.toJson(lista);
	}
	

	//SERVIÇO UPDATE VEICULO
    @PUT
	@Path("editar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCliente(Veiculo dados) throws ClassNotFoundException, SQLException {
		String msg = "";
		Gson gson = new Gson();	
		gson.toJson(dados, Veiculo.class);
		
		if(ControllerVeiculo.editarVeiculo(dados)==1) {
			msg="Veiculo alterado com sucesso";
		}
	
		return msg;
	}
    
	//LISTA MODELO
  	@GET
  	@Path("listarModelos/{id}/")
  	public String listaModelos(@PathParam("id") int id) throws Exception {
  		System.out.print("Metodo Listar Modelos");
  		
          List<Modelo> lista;
          
          ControllerMarca dao = new ControllerMarca();
          lista = dao.listarModelo(id);
          
          //Converter para Gson
          Gson g = new Gson();
          return g.toJson(lista);
  	}
  	
  //METODO PARA RETORNAR ID VEICULO
   	@GET
   	@Path("idVeiculo")
    public int retornarIdPessoa() throws ClassNotFoundException, SQLException {
       	
       	int id = 0;
       	System.out.print("Metodo Retornar ID VEICULO");
                 
           ControllerVeiculo dao = new ControllerVeiculo();
           id = dao.retornoIdVeiculo();
        	
       	
       	return id;
       }
   	
   	//METODO LISTA MARCAS
   	@GET
	@Path("pesqMarca/{marcas}/")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String buscarPorId(@PathParam("marcas") String marcas) throws Exception {

		ControllerMarca dao = new ControllerMarca();

		List<Marca> lista;

		lista = dao.pegaId(marcas);

		// Converter para Gson
		Gson g = new Gson();
		return g.toJson(lista);
	}
   	
 // SERVIÇO PESQUISA MARCA
 	@GET
 	@Path("marca/{marca}/")
 	@Produces(MediaType.TEXT_PLAIN)
 	@Consumes(MediaType.APPLICATION_JSON)
 	public String buscarPorMarca(@PathParam("marca") String marca) throws Exception {
 		Gson gson = new Gson();
 		Marca nomeMarca = new Marca();

 		ControllerMarca dao = new ControllerMarca();

 		nomeMarca = dao.pegaIdMarca(marca);
 		String json = gson.toJson(nomeMarca);

 		return json;
 	}
 	
 // METODO LISTAR MARCAS
 	@GET
 	@Path("listarMarcas")
 	public String listaMarcas() throws Exception {
 		System.out.print("Metodo Listar Marcas");

 		List<Marca> lista;

 		ControllerMarca dao = new ControllerMarca();
 		lista = dao.listarMarca();

 		// Converter para Gson
 		Gson g = new Gson();
 		return g.toJson(lista);
 	}
}

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

import controle.ControllerDados;
import controle.ControllerUsuario;
import modelo.Usuario;

@Path("usuario")
public class ServiceUsuario {
	
//SERVIÇO PESQUISA  
			@GET
			@Path("pesquisar/{id}/")
			@Produces(MediaType.TEXT_PLAIN)
			@Consumes(MediaType.APPLICATION_JSON)
			public String buscarPorId(@PathParam("id") int idUser) {
				Gson gson = new Gson();
				Usuario usuario = null;
				try {
					usuario = ControllerUsuario.buscarNotaPorId(idUser);
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				return gson.toJson(usuario);
			}
			
	
//METODO LISTAR USUARIO
			@GET
			@Path("listarUsuario")
			@Produces(MediaType.TEXT_PLAIN)
			@Consumes(MediaType.APPLICATION_JSON)
			public String listar() throws Exception {
				System.out.print("Metodo Listar");
				
		        List<Usuario> lista;
		        
		        ControllerUsuario dao = new ControllerUsuario();
		        lista = dao.listarUsuario();
		        
		        //Converter para Gson
		        Gson g = new Gson();
		        return g.toJson(lista);
			}
	
//SERVIÇO DE AUTENTICAÇÃO USUARIO
			@GET
			@Path("autenticar/{login}/{senha}")
			public String autenticar(@PathParam("login") String login, @PathParam("senha") String  senha) throws SQLException {
				
				Usuario dados = new Usuario();
				dados.setLogin(login);
				dados.setSenha(senha);
				String msg = "";
				Gson gson = new Gson();	
				gson.toJson(dados, Usuario.class);
				if(ControllerUsuario.verificar(dados) == 1) {
					msg = "Usuario logado com sucesso.";
				}else {
					msg = "Usuario não localizado";
				}
				return msg;
					
			}
			
//SERVIÇO INSERIR
		    @POST
			@Path("inserir")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.TEXT_PLAIN)
			public String inserir(Usuario dados) throws ClassNotFoundException, SQLException {
				String msg = "";
				Gson gson = new Gson();	
				gson.toJson(dados, Usuario.class);
				
				if(ControllerUsuario.inserir(dados)==1) {
					msg="Usuario Inserido com sucesso";
				}
			
				return msg;
			}
		    

//METODO ADICIONAR USUARIO
		    @POST
			@Path("adicionar")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.TEXT_PLAIN)
			public String adicionar(Usuario user) {
				String msg = "";

				System.out.println(user.getNome());
				try {
					
					
					 if (ControllerDados.addUser(user) == 1) {
						 msg = "usuario inserido com sucesso!!!";
					}				
					System.out.print(msg);
				} catch (Exception e) {
					msg = "Erro ao add um usuario!";
					e.printStackTrace();
				}

				return msg;
			}
			
		    
		    
//SERVIÇO DELETE USUARIO
		 	@DELETE
		 	@Path("delete/{id}/")
		 	@Consumes(MediaType.APPLICATION_JSON)
		 	@Produces(MediaType.TEXT_PLAIN)
		 	public String remover(@javax.ws.rs.PathParam("id") int idUser) throws Exception {
		 		String msg = "";
		 		try {
		 			ControllerUsuario.removerUsuario(idUser);
		 			msg = "Usuario removido com sucesso!!!";
		 		} catch (SQLException e) {
		 			msg = "Usuario não removido";
		 			e.printStackTrace();
		 			System.out.print(e);
		 		}		
		 		
		 		return msg;
		 		}
		 	
			
//SERVIÇO UPDATE USUARIO
		    @PUT
			@Path("update/")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.TEXT_PLAIN)
			public String update(Usuario dados) throws ClassNotFoundException, SQLException {
				String msg = "";
				Gson gson = new Gson();	
				gson.toJson(dados, Usuario.class);
				
				if(ControllerUsuario.editarUsuario(dados)==1) {
					msg="Usuario Inserido com sucesso";
				}
			
				return msg;
			}
		    
//METODO PARA RETORNAR ID USUARIO
		   	@GET
		   	@Path("idUsuario")
		    public int retornarId() throws ClassNotFoundException, SQLException {
		       	
		       	int id = 0;
		       	System.out.print("Metodo Retornar ID USUARIO");
		                 
		           ControllerUsuario dao = new ControllerUsuario();
		           id = dao.retornoId();
		          	
		       	
		       	return id;
		       }
		    
		    
			
}

package servico;

import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import armazenamento.ListUser;
import controle.ValidateUser;
import modelo.User;

@Path("/user")
@ApplicationPath("/resource")
public class UserControlService extends Application{
	
	ValidateUser validate = new ValidateUser();
	
	@GET
	@Path("/hello")
	public Response hello() {
		return Response.status(200).entity("chamou.").build();

	}
	
	@POST
	@Path("/inserir")
	@Consumes("application/json")
	public Response inserir(User user){
		try {			
			if(!validate.checkCpf(user)){
				if(!validate.checkEmail(user)){
					ListUser.listUsers.add(user);
					System.out.println("Tamanho da lista depois de salvar: " +ListUser.listUsers.size());
					return Response.status(200).entity("Salvo").build();
					
				}else{
					return Response.status(Response.Status.FOUND).encoding(Response.Status.FOUND.toString())
							.build();					
				}
			}else{
				return Response.status(Response.Status.FOUND).encoding(Response.Status.FOUND.toString())
						.build();					
			}
				
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}		
	}
	
	@GET
	@Path("/listar")
	@Produces("application/json")
	public Response listar(){
		return Response.status(200).entity(new GenericEntity<List<User>>(ListUser.listUsers) {
		}).build();
	}	
	
	@POST
	@Path("/logar")
	@Consumes("application/json")
	public Response verificarUser(User user) {
		System.out.println("Entro no login");
		if (validate.checkLogin(user)) {
			return Response.status(Response.Status.OK).entity("{\"status\": true}").build();
		}
		return Response.status(Response.Status.UNAUTHORIZED).entity("{\"status\": false}").build();
	}


	
}

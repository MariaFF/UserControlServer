package controle;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import armazenamento.ListUser;
import modelo.User;

public class ValidateUser {
	
	public boolean checkLogin(User user){
		if(ListUser.listUsers.stream().anyMatch(
				u -> u.getEmail().equals(user.getEmail())) &&
			ListUser.listUsers.stream().anyMatch(
				u -> u.getSenha().equals(user.getSenha())))
			
			return true;
		return false;	
	}
	
	public boolean checkCpf(User user){
		return ListUser.listUsers.stream().anyMatch(
				u -> u.getCpf().equals(user.getCpf()));
	}
	
	public boolean checkEmail(User user){
		//nome da classe e o nome da lista static
		return ListUser.listUsers.stream().anyMatch(u -> u.getEmail().equals(user.getEmail()));
	}
	
	
	@GET
	@Path("/searchByName/{name}")
	@Produces("application/json")
	public Response searchByName(@PathParam("name") String nome) {
		return Response.status(200).entity(new GenericEntity<List<User>>(
				ListUser.listUsers.stream().filter(u -> u.getNome().equals(nome)).collect(Collectors.toList())) {
		}).build();
	}

	@GET
	@Path("/searchByEmail/{email}")
	@Produces("application/json")
	public Response searchByEmail(@PathParam("email") String email) {
		return Response.status(200).entity(new GenericEntity<List<User>>(
				ListUser.listUsers.stream().filter(u -> u.getEmail().equals(email)).collect(Collectors.toList())) {
		}).build();
	}
	
}

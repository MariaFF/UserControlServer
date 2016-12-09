package armazenamento;

import java.util.ArrayList;
import java.util.List;

import modelo.User;

public class ListUser {
	//Lista static para não criar uma nova a cada requisição
	public static List<User> listUsers = new ArrayList<>();

}

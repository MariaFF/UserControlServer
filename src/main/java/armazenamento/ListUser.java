package armazenamento;

import java.util.ArrayList;
import java.util.List;

import modelo.User;

public class ListUser {
	//Lista static para n�o criar uma nova a cada requisi��o
	public static List<User> listUsers = new ArrayList<>();

}

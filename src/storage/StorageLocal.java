package storage;

import java.util.List;

import it.dataone.graphql.test.domain.User;

public class StorageLocal {
	
	private static List<User> users;
	
	//GETTERS & SETTERS

	public static List<User> getUsers() {
		return users;
	}

	public static void setUsers(List<User> users) {
		StorageLocal.users = users;
	}
	
}

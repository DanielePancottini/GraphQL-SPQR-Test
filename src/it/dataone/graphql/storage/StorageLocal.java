package it.dataone.graphql.storage;

import java.util.List;

import it.dataone.graphql.domain.User;



/**
 * 
 * Classe che simula un' archivio di dati tramite una lista statica di {@link User}
 * 
 * @author Daniele Pancottini
 * 
 */

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

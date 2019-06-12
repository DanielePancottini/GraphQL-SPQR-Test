package it.dataone.graphql.test.service;

import java.util.List;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import it.dataone.graphql.test.domain.User;
import storage.StorageLocal;

public class UserService {
	
	 @GraphQLQuery(name = "users")
	 public List<User> getUsers(){
		 return StorageLocal.getUsers();
	 }
	
	 @GraphQLQuery(name = "user")
	 public User getById(@GraphQLArgument(name = "id") Integer id) {
		 for (User x : StorageLocal.getUsers()) {
			if(x.getId().equals(id))
				return x;
		}
		 return null;
	 }
	 
	 @GraphQLMutation(name = "addUser")
	 public User addUser(@GraphQLArgument(name = "user") User user) {
	     StorageLocal.getUsers().add(user);
	     return StorageLocal.getUsers().get(StorageLocal.getUsers().lastIndexOf(user));
	 }
	 
	 @GraphQLMutation(name = "updateUser")
	 public User updateUser(@GraphQLArgument(name = "user") User user) {
		 return user;
	 }
	 
	 @GraphQLMutation(name = "deleteUser")
	 public void deleteUser(@GraphQLArgument(name = "id") Integer id) {
		 User toRemove = new User();
		 for (User x : StorageLocal.getUsers()) {
				if(x.getId().equals(id)) {
					toRemove = x;
				}
			}
		StorageLocal.getUsers().remove(toRemove);
	 }
	
}

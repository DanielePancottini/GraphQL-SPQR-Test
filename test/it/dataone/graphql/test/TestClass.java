package it.dataone.graphql.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import it.dataone.graphql.test.domain.User;
import it.dataone.graphql.test.service.UserService;
import it.dataone.graphql.test.storage.StorageLocal;

public class TestClass {

	public static void main(String args[]) {
		
		List<User> users = new ArrayList<>(Arrays.asList(new User("utente 1", 1, "12/06/2019")));
		StorageLocal.setUsers(users);
		
		UserService userService = new UserService(); //instantiate the service (or inject by Spring or another framework)
		GraphQLSchema schema = new GraphQLSchemaGenerator()
		    .withBasePackages("it.dataone.graphql.test") //not mandatory but strongly recommended to set your "root" packages
		    .withOperationsFromSingleton(userService) //register the service
		    .generate(); //done ;)
		
		GraphQL graphQL = new GraphQL.Builder(schema)
			.build();

		//keep the reference to GraphQL instance and execute queries against it.
		ExecutionResult result = graphQL.execute(
					getQuery(QueryType.GET_USERS)
				);
		
		System.err.println(result);
		System.err.println(result.getData().toString());
	}
	
	public enum QueryType{
		GET_USERS,
		GET_USER,
		ADD_USER,
		UPDATE_USER,
		DELETE_USER
	}
	
	public static String getQuery(QueryType type) {
		
		String resultQuery;
		
		switch(type) {
			case GET_USERS:
				resultQuery = "{"
							+ "	users {"
							+ "			id"
							+ "		  }"
							+ "}";
				break;
			case GET_USER:
				resultQuery = "{"
							+ "	user(id: 1){"
							+ "					id"
							+ "					regDate"
							+ "					name"
							+ "				}"
							+ "}";
				break;
			case ADD_USER:
				resultQuery = "mutation {"
							+ "		addUser(user : {"
							+ "			id: 2"
							+ "			name: \"utente 2\" "
							+ "			regDate: \"19/06/2019\" "
							+ "		}){"
							+ "			id"
							+ "			name"
							+ "		}"
							+ "}";
				break;
			case UPDATE_USER:
				resultQuery = "mutation {"
							+ "		updateUser(user: {"
							+ "			id: 1"
							+ "			name: \"utente 1\" "
							+ "			regDate: \"19/06/2019\" "
							+ "		}){"
							+ "			id"
							+ "			name"
							+ "		}"
							+ "}";
				break;
			case DELETE_USER:
				resultQuery = "mutation {"
							+ "		deleteUser(id: 2)"
							+ "}";
				break;
			
			default: resultQuery = null;
		}
		
		return resultQuery;
	}
	
}

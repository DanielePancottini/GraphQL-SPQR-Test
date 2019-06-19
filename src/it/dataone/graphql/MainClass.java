package it.dataone.graphql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import it.dataone.graphql.domain.User;
import it.dataone.graphql.service.UserService;
import it.dataone.graphql.storage.StorageLocal;

public class MainClass {

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
					//String for mutation query
					"mutation {\r\n" + 
					"  updateUser(user: {\r\n" + 
					"    id: 3323 \r\n"
					+ "	 regDate: \" 14/12/12 \" " +
					"  }) {"
					+ "id "
					+ "name "
					+ "regDate"
					+ "}" +
					"}"
				);
		
		System.err.println(result);
	}
	
}

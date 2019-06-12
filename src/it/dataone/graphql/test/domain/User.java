package it.dataone.graphql.test.domain;

import io.leangen.graphql.annotations.GraphQLQuery;

public class User {

	private String name;
    private Integer id;
    private String registrationDate;

    public User(String name, Integer id, String regDate) {
    	this.name = name;
    	this.id = id;
    	this.registrationDate = regDate;
    }
    
    public User() {};
    
    //GETTERS & SETTERS
    
    @GraphQLQuery(name = "name", description = "A person's name")
    public String getName() {
        return name;
    }

    @GraphQLQuery(name = "id")
    public Integer getId() {
        return id;
    }

    @GraphQLQuery(name = "regDate", description = "Date of registration")
    public String getRegistrationDate() {
        return registrationDate;
    }
	
}

package it.dataone.graphql.test.domain;

import io.leangen.graphql.annotations.GraphQLQuery;

/**
 * Classe di dominio che definisce l' Utente, tutti i 'getters' sono annotati
 * con l' annotazione {@link GraphQLQuery} in modo da permettere la parzializzazione dei dati
 * in maniera automatica
 * 
 * @author Daniele Pancottini
 *
 */

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
    
    @GraphQLQuery(name = "name", description = "Nome dell' utente")
    public String getName() {
        return name;
    }

    @GraphQLQuery(name = "id")
    public Integer getId() {
        return id;
    }

    @GraphQLQuery(name = "regDate", description = "Data di registrazione")
    public String getRegistrationDate() {
        return registrationDate;
    }
	
}

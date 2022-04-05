package com.springboot.groceryAPI.shoppinglist;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.groceryAPI.grocery.Groceries;
import com.springboot.groceryAPI.grocery.GroceryRepo;



@Entity
public class ShoppingList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id") 
	private Integer id;

	private String list_name;
	
	
	@ManyToMany
	  @JoinTable(
			  name="grocery_tolist",
			  joinColumns = @JoinColumn(name = "list_id"),
			  inverseJoinColumns = @JoinColumn(name = "grocery_id")
			  )
	private Set<Groceries> contained = new HashSet<>();
	
	public ShoppingList() {
	}
	
	public ShoppingList(String list_name, Set<Groceries> contained) {
		super();
		this.list_name = list_name;
		this.contained = contained;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return list_name;
	}

	public void setName(String name) {
		this.list_name = name;
	}

	public Set<Groceries> getContained() {
		return contained;
	}

	public void addToList(Groceries groceries) {
		contained.add(groceries);
	}
	
	public void addMultiToList(Set<Groceries> groceries) {
		contained.addAll(groceries);
	}
	
	
	
	
	
	public void mapJSONtoList(String jsonString , GroceryRepo groceryRepo) throws JsonMappingException, JsonProcessingException {
		JsonNode contained, name;
		List<Groceries> groceries = groceryRepo.findAll();
		
		contained = new ObjectMapper().readTree(jsonString).get("contained");
		name = new ObjectMapper().readTree(jsonString).get("name");
		
		
		if(name.isTextual() && name.asText() != "") {
			this.list_name = name.asText();
		}
		else {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, "Name not found");
		}
			
		if (contained.isArray()) {
			   for (final JsonNode objNode : contained) {
				   this.contained.add(groceries.get(objNode.asInt()-1));
			  }

		}
		
		
	}
	
	
}
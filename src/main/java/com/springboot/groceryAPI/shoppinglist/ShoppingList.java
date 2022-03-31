package com.springboot.groceryAPI.shoppinglist;

import java.util.HashSet;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.groceryAPI.grocery.Groceries;



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
	
	
}
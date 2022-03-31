package com.springboot.groceryAPI.grocery;


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
import com.springboot.groceryAPI.shoppinglist.ShoppingList;


@Entity
public class Groceries {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id") 
  private Integer id;

  private String groceries_name;

  private Integer groceries_stock;
  
  @JsonIgnore
  @ManyToMany(mappedBy = "contained")
  private Set<ShoppingList> list = new HashSet<>();
  
  

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return groceries_name;
  }

  public void setName(String name) {
    this.groceries_name = name;
  }

  public Integer getStock() {
    return groceries_stock;
  }

  public void setStock(Integer stock) {
    this.groceries_stock = stock;
  }
  
  public Set<ShoppingList> getList() {
		return list;
	}

public void addToList(ShoppingList list2) {
	list.add(list2);
}
}
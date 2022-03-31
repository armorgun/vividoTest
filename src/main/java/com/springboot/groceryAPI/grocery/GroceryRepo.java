package com.springboot.groceryAPI.grocery;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.groceryAPI.grocery.Groceries;

public interface GroceryRepo extends JpaRepository<Groceries, Integer> {

}
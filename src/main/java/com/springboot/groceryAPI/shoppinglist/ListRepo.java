package com.springboot.groceryAPI.shoppinglist;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.groceryAPI.shoppinglist.ShoppingList;

public interface ListRepo extends JpaRepository<ShoppingList, Integer> {

}
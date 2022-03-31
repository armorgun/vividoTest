package com.springboot.groceryAPI.grocery;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.groceryAPI.shoppinglist.ListRepo;
import com.springboot.groceryAPI.shoppinglist.ShoppingList;

@RestController
public class GroceryController {
	@Autowired
	private GroceryRepo groceryRepo;
	
	@Autowired
	private ListRepo listRepo;

	
	@GetMapping("/hi")
	public String showSplash()
	{
		return "Hi ALL";
	}
	
	@GetMapping(path="/showavailable")
	  public @ResponseBody Iterable<Groceries> getAllGroceries() {
	    return groceryRepo.findAll();
	  }
	
	
	
	@PostMapping(path="/add") 
	  public @ResponseBody String addNewGrocery (@RequestParam String name, @RequestParam Integer stock) {

		Groceries n = new Groceries();
	    n.setName(name);
	    n.setStock(stock);
	    groceryRepo.save(n);
	    return "Added new Grocery named " + name + " with stock of " + stock;
	  }
	

}

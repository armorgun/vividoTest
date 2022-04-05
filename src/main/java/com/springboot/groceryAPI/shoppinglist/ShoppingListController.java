package com.springboot.groceryAPI.shoppinglist;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.groceryAPI.grocery.Groceries;
import com.springboot.groceryAPI.grocery.GroceryRepo;

@RestController
public class ShoppingListController {
	
	@Autowired
	private ListRepo listRepo;
	
	@Autowired
	private GroceryRepo groceryRepo;
	
	
	@GetMapping(path="/showavailablelists")

	  public @ResponseBody Iterable<ShoppingList> getAllLists() {
	    return listRepo.findAll();
	  }
	
	/*
	@PostMapping(path="/addlist") 
	  public @ResponseBody ShoppingList addNewList (
			  @RequestParam String name, 
			  @RequestParam List<Groceries> contained
			  ) 
	{
		ShoppingList list = new ShoppingList();
		list.setName(name);
		
		//List<Groceries> groceries = groceryRepo.findAll();
		//int[] groceryId = convertorToArrayOfInt(contained);
		
		for(int i=0;i<contained.size();i++) {
			//list.addToList(iterator.next());
			list.addToList(contained.get(i));
		}
	    return listRepo.save(list);
	  }
	*/
	
	@PostMapping(path="/addlist") 
	  public  ShoppingList addNewList (
			  @RequestBody String jsonString		  
			  ) 
	{
		ShoppingList list = new ShoppingList();
		
		try {
			list.mapJSONtoList(jsonString , groceryRepo);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listRepo.save(list);

//		List<Groceries> groceries = groceryRepo.findAll();
//		//int[] groceryId = convertorToArrayOfInt(contained);
//		
//		for(int i=0;i<contained.size();i++) {
//			//list.addToList(iterator.next());
//			list.addToList(groceries.get(contained.get(i)));
//		}
	    //return listRepo.save(list);
		 
	    
	  }
	
	@PutMapping(path="/addgrocerytolist")
	public ShoppingList addGroceryToList(
			@RequestParam String listId,
			@RequestParam String groceryId
			)
	{
		Groceries groceries = groceryRepo.findById(Integer.valueOf(groceryId)).get();
		ShoppingList list = listRepo.findById(Integer.valueOf(listId)).get();
		list.addToList(groceries);
		return listRepo.save(list);
		
	}
	
	
	static int[] convertorToArrayOfInt(String str)
    {
 
        String[] splitArray = str.split(" ");
        int[] array = new int[splitArray.length];
        for (int i = 0; i < splitArray.length; i++) {
            array[i] = Integer.parseInt(splitArray[i]);
        }
        return array;
    }
	

}

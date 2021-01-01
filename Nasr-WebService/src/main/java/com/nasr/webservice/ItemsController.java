package com.nasr.webservice;

import com.nasr.entity.Items;
import com.nasr.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/items")
public class ItemsController {
	
	@Autowired
	private ItemsService itemsService;
	
	// Add new item
	@PostMapping(path="/add")
	public @ResponseBody String addNewItems (@RequestBody Items i) {
		return itemsService.addItems(i);
	}
	
	// Get all items
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Items> getAllItems() {
		return itemsService.getAllItems();
	}
	
	// Get single item by Id
	@GetMapping(path="/{id}")
	public @ResponseBody Object getItemsById(@PathVariable(name = "id") Integer id) {
                if(itemsService.getItems(id).equals(null))
                {
                    return (Object)"Item not found";
                }
		return itemsService.getItems(id);
	}
	
	// Update a Item
	@PutMapping(path="/{id}")
	public @ResponseBody String updateItems(@PathVariable(name = "id") Integer id, @RequestBody Items items) {
		return itemsService.updateItems(id,items);
	}

	
	// Delete a Item
	@DeleteMapping(path="/delete/{id}")
	public @ResponseBody String deleteItems(@PathVariable(name = "id") Integer id) {
		return itemsService.deleteItems(id);
	}
}

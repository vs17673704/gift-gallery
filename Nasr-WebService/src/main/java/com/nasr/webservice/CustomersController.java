package com.nasr.webservice;

import com.nasr.entity.Customers;
import com.nasr.service.CustomersService;
import java.util.List;
import java.util.Map;

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
@RequestMapping(path="/customers")
public class CustomersController {
	
	@Autowired
	private CustomersService customersService;
        
	
	// Add new student
	@PostMapping(path="/add")
	public @ResponseBody String addNewCustomers (@RequestBody Customers c) {
		return customersService.addCustomers(c);
	}
	
	// Get all item
	@GetMapping(path="/all")
	public @ResponseBody List getAllCustomers() {
		return customersService.getAllCustomers();
	}
	
	// Get single item by Id
	@GetMapping(path="login/{id}/{password}")
	public @ResponseBody Map loginCustomersById(@PathVariable(name = "id") String id, @PathVariable(name = "password") String password) {
		
//                return role;
		return customersService.loginCustomers(id,password);
	}
	// Get single item by Id
	@GetMapping(path="/{id}")
	public @ResponseBody Map getCustomersById(@PathVariable(name = "id") String id) {
		
//                return role;
		return customersService.getCustomers(id);
	}
	
	// Update a Item
	@PutMapping(path="/editprofile/{id}")
	public @ResponseBody String updateCustomers(@PathVariable(name = "id") String id, @RequestBody Customers items) {
		return customersService.updateCustomers(id,items);
	}

	
	// Delete a Item
	@DeleteMapping(path="/delete/{id}")
	public @ResponseBody String deleteCustomers(@PathVariable(name = "id") String id) {
		return customersService.deleteCustomers(id);
	}
}

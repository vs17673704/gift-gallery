package com.nasr.webservice;

import com.nasr.entity.Orders;
import com.nasr.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/orders")
public class OrderController {
	
        @Autowired
	private OrdersService ordersService;
	
	// Add new Order
	@PostMapping(path="/add/{custId}/{itemId}")
	public @ResponseBody String Orders (@PathVariable(name = "custId") String custId, @PathVariable(name = "itemId") String itemId) {
		return ordersService.addOrders(custId, itemId);
	}
	
	// Get all orders
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Orders> getAllItems() {
		return ordersService.getAllOrders();
	}
	
	// Get single orders by Customer Id
	@GetMapping(path="orderbyid/{id}")
	public @ResponseBody Iterable<Orders> getOrdersById(@PathVariable(name = "id") String id) {
		return ordersService.getOrdersById(id);
	}
	
	// Update a cancel order
	@PutMapping(path="/cancel/{id}")
	public @ResponseBody String updateOrders(@PathVariable(name = "id") Integer id) {
		return ordersService.updateOrders(id);
	}

	
	// Delete a Order
            @DeleteMapping(path="/delete/{id}")
	public @ResponseBody String deleteOrders(@PathVariable(name = "id") Integer id) {
		return ordersService.deleteOrders(id);
	}
}

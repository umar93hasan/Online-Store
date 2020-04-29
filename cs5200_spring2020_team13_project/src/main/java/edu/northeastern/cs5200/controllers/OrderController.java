package edu.northeastern.cs5200.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Buyer;
import edu.northeastern.cs5200.models.Order;
import edu.northeastern.cs5200.models.OrderItem;
import edu.northeastern.cs5200.models.Product;
import edu.northeastern.cs5200.repos.*;

@RestController
public class OrderController {

	@Autowired(required = true)
	private ProductRepo productRepo;
	
	@Autowired(required = true)
	private OrderRepo orderRepo;
	
	@Autowired(required = true)
	private BuyerRepo buyerRepo;
	
	@Autowired(required = true)
	private OrderItemRepo orderItemRepo;
	
	@PostMapping("/api/order/create")
	public Order checkout(@RequestBody String data) {		
		String[] elements = data.split(" ");
		String email = elements[0];
		String[] prodIds = Arrays.copyOfRange(elements,1,elements.length);
		Date date = new Date(System.currentTimeMillis());
		Double amount = 0.00;
		System.out.println("email::"+email);
		Buyer buyer = buyerRepo.findBuyerByEmail(email);
		List<Product> products = new ArrayList<Product>();
		for(String id:prodIds) {
			Product p = productRepo.findProductById(Integer.valueOf(id));
			products.add(p);
			amount += p.getPrice();
		}
		amount = Math.round(amount * 100.0) / 100.0;
		Order newOrder = new Order(date,amount,buyer);
		System.out.println(newOrder.getOrderBuyer().getEmail());
		Order finalOrder = orderRepo.save(newOrder);
		
		for(Product p:products) {
			OrderItem orderItem = new OrderItem(finalOrder, p);
			orderItemRepo.save(orderItem);
		}		
		return finalOrder;
	}
	
	@GetMapping("/api/order/{email}")
	public List<Order> getOrdersByEmail(@PathVariable String email){
		Buyer buyer = buyerRepo.findBuyerByEmail(email);
		return orderRepo.findOrdersByBuyer(buyer);
	}
	
	@GetMapping("/api/orderItem/{orderId}")
	public List<OrderItem> getOrderItemsByOrderId(@PathVariable int orderId) {
		Order o = orderRepo.findOrderById(orderId);		
		return orderItemRepo.findOrdersItemByOrder(o);
	}
}

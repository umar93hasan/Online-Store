package edu.northeastern.cs5200.controllers;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.repos.*;

@RestController
public class AdminController {

	@Autowired(required = true)
	private AdminRepo adminRepo;
	
	@Autowired(required = true)
	private UserRepo userRepo;
	
	@Autowired(required = true)
	private BuyerRepo buyerRepo;
	
	@Autowired(required = true)
	private SellerRepo sellerRepo;
	
	@Autowired(required = true)
	private AddressRepo addressRepo;
	
	@Autowired(required = true)
	private OrderRepo orderRepo;
	
	@Autowired(required = true)
	private OrderItemRepo orderItemRepo;
	
	@Autowired(required = true)
	private ProductSellerRepo productSellerRepo;
	
	@PostMapping("/api/admin/insertBuyer")
	public Buyer insertBuyer(@RequestBody Buyer newBuyer) {
		if(userRepo.findUserByEmail(newBuyer.getEmail())!=null) {
			throw new IllegalArgumentException("Email Already Registered");
		}
		return buyerRepo.save(newBuyer);
	}
	
	@PostMapping("/api/admin/insertSeller")
	public Seller insertSeller(@RequestBody Seller newSeller) {
		if(userRepo.findUserByEmail(newSeller.getEmail())!=null) {
			throw new IllegalArgumentException("Email Already Registered");
		}
		return sellerRepo.save(newSeller);
	}
	
	@GetMapping("/api/admin/buyers")
	public List<Buyer> selectAllBuyers() {
		return (List<Buyer>) buyerRepo.findAll();
	}
	
	@GetMapping("/api/admin/sellers")
	public List<Seller> selectAllSellers() {
		return (List<Seller>) sellerRepo.findAll();
	}
	
	
	@PutMapping("/api/admin/updateBuyer") 
	public Buyer updateBuyer(@RequestBody Buyer buyer) { 
		if(userRepo.findUserByEmail(buyer.getEmail())!=null) 
			return buyerRepo.save(buyer); 
		throw new IllegalArgumentException("Buyer Doesn't Exist"); 
	}
	  
	@PutMapping("/api/admin/updateSeller") 
	public Seller updateSeller(@RequestBody Seller seller) {
		if(userRepo.findUserByEmail(seller.getEmail())!=null) { 
			return sellerRepo.save(seller); 
		} 
		throw new IllegalArgumentException("Seller Doesn't Exist"); 
	}
	 
	
	/*
	 * @PutMapping("/api/admin/updateUser") public User updateUser(@RequestBody User
	 * user) { if(findUserByEmail(user.getEmail())!=null) { return
	 * userRepo.save(user); } throw new
	 * IllegalArgumentException("User Doesn't Exist"); }
	 */
	
	@DeleteMapping("/api/admin/deleteUser/{email}")
	void deleteUser(@PathVariable String email) {
	    //userRepo.deleteUserByEmail(email);
	    
	    if(buyerRepo.findBuyerByEmail(email)!=null) {
			Buyer b = buyerRepo.findBuyerByEmail(email);
			for(Address a:b.getAddresses()) {
				addressRepo.delete(a);
			}
			
			for(Order o:b.getPurchasedOrders()) {
				for(OrderItem oi:o.getOrderItems()) {
					orderItemRepo.delete(oi);
				}
				orderRepo.delete(o);
			}			
			buyerRepo.delete(b);
			
		}else if(sellerRepo.findSellerByEmail(email)!=null){
			Seller s = sellerRepo.findSellerByEmail(email);
			
			for(Address a:s.getAddresses()) {
				addressRepo.delete(a);
			}
			for(ProductSeller sp:s.getSellingProducts()) {
				productSellerRepo.delete(sp);
			}
			sellerRepo.delete(s);
		}
	    
	  }
	
	@GetMapping("/api/admin/getUser/{email}")
	public String findUserByEmail(@PathVariable String email) {
		JSONObject response = null;
		//return userRepo.findUserByEmail(email);
		if(buyerRepo.findBuyerByEmail(email)!=null) {
			Buyer b = buyerRepo.findBuyerByEmail(email);
			response = new JSONObject();
			response.put("email", b.getEmail());
			response.put("password", b.getPassword());
			response.put("firstName", b.getFirstName());
			response.put("lastName", b.getLastName());
			response.put("userType", "buyer");
			return response.toString();
		}else if(sellerRepo.findSellerByEmail(email)!=null){
			Seller s = sellerRepo.findSellerByEmail(email);
			response = new JSONObject();
			response.put("email", s.getEmail());
			response.put("password", s.getPassword());
			response.put("firstName", s.getFirstName());
			response.put("lastName", s.getLastName());
			response.put("userType", "seller");
			return response.toString();
		}
		throw new IllegalArgumentException();
	}
	
	
}

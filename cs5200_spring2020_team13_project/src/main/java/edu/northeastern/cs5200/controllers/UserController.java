package edu.northeastern.cs5200.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Admin;
import edu.northeastern.cs5200.models.Buyer;
import edu.northeastern.cs5200.models.Seller;
import edu.northeastern.cs5200.models.User;
import edu.northeastern.cs5200.repos.AdminRepo;
import edu.northeastern.cs5200.repos.BuyerRepo;
import edu.northeastern.cs5200.repos.SellerRepo;
import edu.northeastern.cs5200.repos.UserRepo;

@RestController
public class UserController {
	
	@Autowired(required = true)
	private UserRepo userRepo;
	
	@Autowired(required = true)
	private BuyerRepo buyerRepo;
	
	@Autowired(required = true)
	private SellerRepo sellerRepo;
	
	@Autowired(required = true)
	private AdminRepo adminRepo;
	
	@PostMapping("/api/user/login")
	public String login(@RequestBody User user) {
		
		JSONObject response = new JSONObject();
		
		Seller s = sellerRepo.findSellerByEmailPassword(user.getEmail(), user.getPassword());
		if(s!=null) {
			response = new JSONObject();
			response.put("email", s.getEmail());
			response.put("password", s.getPassword());
			response.put("firstName", s.getFirstName());
			response.put("lastName", s.getLastName());
			response.put("userType", "seller");
			return response.toString();
		}
		
		Buyer b = buyerRepo.findBuyerByEmailPassword(user.getEmail(), user.getPassword());
		if(b!=null) {
			response = new JSONObject();
			response.put("email", b.getEmail());
			response.put("password", b.getPassword());
			response.put("firstName", b.getFirstName());
			response.put("lastName", b.getLastName());
			response.put("userType", "buyer");
			return response.toString();
		}
		
		Admin a = adminRepo.findAdminByEmailPassword(user.getEmail(), user.getPassword());
		if(a!=null) {
			response = new JSONObject();
			response.put("email", a.getEmail());
			response.put("password", a.getPassword());
			response.put("firstName", a.getFirstName());
			response.put("lastName", a.getLastName());
			response.put("userType", "admin");
			return response.toString();
		}
		
		response.put("userType", "error");
		
		return response.toString();
	}
}

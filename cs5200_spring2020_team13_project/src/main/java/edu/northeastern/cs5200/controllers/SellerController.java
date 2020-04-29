package edu.northeastern.cs5200.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Seller;
import edu.northeastern.cs5200.models.User;
import edu.northeastern.cs5200.repos.SellerRepo;
import edu.northeastern.cs5200.repos.UserRepo;

@RestController
public class SellerController {
	
	@Autowired(required = true)
	private SellerRepo sellerRepo;
	
	@Autowired(required = true)
	private UserRepo userRepo;
	
	@PostMapping("/api/seller/insert")
	public Seller insertSeller(@RequestBody Seller newSeller) {
		if(findUserByEmail(newSeller.getEmail())!=null) {
			throw new IllegalArgumentException("Email Already Registered");
		}
		return sellerRepo.save(newSeller);
	}
	
	@PutMapping("/api/seller/update")
	public Seller updateSeller(@RequestBody Seller seller) {
		if(findUserByEmail(seller.getEmail())!=null) {
			return sellerRepo.save(seller);			
		}
		throw new IllegalArgumentException("Seller Doesn't Exist");
	}
	
	@GetMapping("/api/sellers")
	public List<Seller> selectAllSellers() {
		return (List<Seller>) sellerRepo.findAll();
	}

	@GetMapping("/api/seller/{email}")
	public Seller findSellerByEmail(@PathVariable String email) {
		return sellerRepo.findSellerByEmail(email);
	}
	
	private User findUserByEmail(String email) {
		return userRepo.findUserByEmail(email);
	}

}

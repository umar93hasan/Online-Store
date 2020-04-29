package edu.northeastern.cs5200.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Buyer;
import edu.northeastern.cs5200.models.User;
import edu.northeastern.cs5200.repos.BuyerRepo;
import edu.northeastern.cs5200.repos.UserRepo;

@RestController
public class BuyerController {

	@Autowired(required = true)
	private BuyerRepo buyerRepo;
	
	@Autowired(required = true)
	private UserRepo userRepo;

	@PostMapping("/api/buyer/insert")
	public Buyer insertBuyer(@RequestBody Buyer newBuyer) {
		if(findUserByEmail(newBuyer.getEmail())!=null) {
			throw new IllegalArgumentException("Email Already Registered");
		}
		return buyerRepo.save(newBuyer);
	}
	
	@PutMapping("/api/buyer/update")
	public Buyer updateBuyer(@RequestBody Buyer buyer) {
		if(findUserByEmail(buyer.getEmail())!=null)
			return buyerRepo.save(buyer);
		throw new IllegalArgumentException("Buyer Doesn't Exist");
	}

	@GetMapping("/api/buyers")
	public List<Buyer> selectAllBuyers() {
		return (List<Buyer>) buyerRepo.findAll();
	}

	@GetMapping("/api/buyer/{email}")
	public Buyer findBuyerByEmail(@PathVariable String email) {
		return buyerRepo.findBuyerByEmail(email);
	}
	
	private User findUserByEmail(String email) {
		return userRepo.findUserByEmail(email);
	}

}

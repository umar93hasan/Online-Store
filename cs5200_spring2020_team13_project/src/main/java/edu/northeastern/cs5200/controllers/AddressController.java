package edu.northeastern.cs5200.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.User;
import edu.northeastern.cs5200.repos.AddressRepo;
import edu.northeastern.cs5200.repos.UserRepo;


@RestController
public class AddressController {

	@Autowired(required = true)
	private AddressRepo ar;
	
	@Autowired(required = true)
	private UserRepo ur;
	
	@GetMapping("/api/address/{email}")
	public List<Address> getAddressesByUserEmail(@PathVariable String email){
		User u = ur.findUserByEmail(email);
		return ar.findAddressesByEmail(u);
	}
	
	@PostMapping("/api/address/insert")
	public Address insertAddress(@RequestBody Address address) {
		User u = ur.findUserByEmail(address.getResidentEmail());
		address.setResident(u);
		return ar.save(address);
	}
	
	@DeleteMapping("/api/address/delete/{id}")
	void deleteAddress(@PathVariable int id) {
		ar.deleteById(id);
	}
	
}

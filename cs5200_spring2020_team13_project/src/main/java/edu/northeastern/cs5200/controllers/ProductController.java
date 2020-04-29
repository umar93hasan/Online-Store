package edu.northeastern.cs5200.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Product;
import edu.northeastern.cs5200.repos.ProductRepo;

@RestController
public class ProductController {

	@Autowired(required = true)
	private ProductRepo productRepo;
	
	

	@GetMapping("/api/product/{keyword}/{keyword2}")
	public List<Product> findProductByKeyword(@PathVariable String keyword, @PathVariable String keyword2) {
		return (List<Product>) productRepo.findProductByKeyword(keyword,keyword2);
	}
	
	@GetMapping("/api/product/{keyword}")
	public Product findProductById(@PathVariable int keyword) {
		return  productRepo.findProductById(keyword);
	}
	
	@GetMapping("/api/cartproducts/{productIds}")
	public List<Product> getProductsInCart(@PathVariable String productIds){
		String[] ids = productIds.split(" ");
		List<Product> cart = new ArrayList<>();
		for(String id:ids) {
			Product p = productRepo.findProductById(Integer.valueOf(id));
			cart.add(p);
		}
		return cart;
	}

}

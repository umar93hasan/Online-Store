package edu.northeastern.cs5200.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="seller")
public class Seller extends User {

	private String rating;
	
	@OneToMany(mappedBy="seller", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<ProductSeller> sellingProducts;
	
	public void addSellingProduct(ProductSeller sellingProduct) {
		this.sellingProducts.add(sellingProduct);
	}
	
	public void removeSellingProduct(ProductSeller sellingProduct) {
		this.sellingProducts.remove(sellingProduct);
	}
	
	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Set<ProductSeller> getSellingProducts() {
		return sellingProducts;
	}

	public void setSellingProducts(Set<ProductSeller> sellingProducts) {
		this.sellingProducts = sellingProducts;
	}

	public Seller(String email, String password, String firstName, String lastName, String rating) {
		super(email, password, firstName, lastName);
		this.rating = rating;
		this.sellingProducts = new HashSet<ProductSeller>(); 
	}

	public Seller() {
		super();
		this.sellingProducts = new HashSet<ProductSeller>();
		// TODO Auto-generated constructor stub
	}

	public Seller(String email, String password, String firstName, String lastName) {
		super(email, password, firstName, lastName);
		this.sellingProducts = new HashSet<ProductSeller>();
		// TODO Auto-generated constructor stub
	}
	
	
}

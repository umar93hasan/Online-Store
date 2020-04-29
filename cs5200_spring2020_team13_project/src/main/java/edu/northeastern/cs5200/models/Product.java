package edu.northeastern.cs5200.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="product")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private Double price;
	
	@OneToMany(mappedBy="product", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<ProductSeller> productSellers;
	
	public void addProductSeller(ProductSeller productSeller) {
		this.productSellers.add(productSeller);
	}
	public void removeProductSeller(ProductSeller productSeller) {
		this.productSellers.remove(productSeller);
	}
	
	@OneToMany(mappedBy="productItem", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<OrderItem> orderItems;
	
	public void addOrderItem(OrderItem orderItem) {
		this.orderItems.add(orderItem);
	}
	public void removeOrderItem(OrderItem orderItem) {
		this.orderItems.remove(orderItem);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Set<ProductSeller> getProductSellers() {
		return productSellers;
	}
	public void setProductSellers(Set<ProductSeller> productSellers) {
		this.productSellers = productSellers;
	}
	
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public Product(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
		this.productSellers = new HashSet<ProductSeller>();
		this.orderItems = new HashSet<OrderItem>();
	}
	public Product() {
		super();
		this.productSellers = new HashSet<ProductSeller>();
		this.orderItems = new HashSet<OrderItem>();
		// TODO Auto-generated constructor stub
	}	
	
	
}

package edu.northeastern.cs5200.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="buyer")
public class Buyer extends User {

	@OneToMany(mappedBy="orderBuyer")
	@JsonIgnore
	private Set<Order> purchasedOrders;
	
	public void addOrder(Order order) {
		this.purchasedOrders.add(order);
	}
	
	public void removeOrder(Order order) {
		this.purchasedOrders.remove(order);
	}
	
	public Set<Order> getPurchasedOrders() {
		return purchasedOrders;
	}

	public void setPurchasedOrders(Set<Order> purchasedOrders) {
		this.purchasedOrders = purchasedOrders;
	}

	public Buyer() {
		super();
		purchasedOrders = new HashSet<>();
		// TODO Auto-generated constructor stub
	}

	public Buyer(String email, String password, String firstName, String lastName) {
		super(email, password, firstName, lastName);
		purchasedOrders = new HashSet<>();
		// TODO Auto-generated constructor stub
	}
	
}

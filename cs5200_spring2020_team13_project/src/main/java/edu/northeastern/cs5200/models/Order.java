package edu.northeastern.cs5200.models;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private Date orderDate;
	private Double amount;
	
	@ManyToOne()
	private Buyer orderBuyer;
	
	@OneToMany(mappedBy="order", fetch = FetchType.EAGER)
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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Buyer getOrderBuyer() {
		return orderBuyer;
	}

	public void setOrderBuyer(Buyer orderBuyer) {
		this.orderBuyer = orderBuyer;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	public Order(Date orderDate, Double amount, Buyer orderBuyer) {
		super();
		this.orderDate = orderDate;
		this.amount = amount;
		this.orderBuyer = orderBuyer;
		this.orderItems = new HashSet<OrderItem>();
	}

	public Order() {
		super();
		this.orderItems = new HashSet<OrderItem>();
		// TODO Auto-generated constructor stub
	}
	
	
}

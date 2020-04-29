package edu.northeastern.cs5200.models;

import javax.persistence.*;

@Entity
@Table(name="order_item")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne()
	private Order order;
	
	@ManyToOne()
	private Product productItem;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProductItem() {
		return productItem;
	}

	public void setProductItem(Product productItem) {
		this.productItem = productItem;
	}

	public OrderItem(Order order, Product productItem) {
		super();
		this.order = order;
		this.productItem = productItem;
	}

	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

}

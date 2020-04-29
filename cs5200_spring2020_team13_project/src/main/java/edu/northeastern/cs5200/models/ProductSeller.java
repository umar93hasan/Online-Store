package edu.northeastern.cs5200.models;

import javax.persistence.*;

@Entity
@Table(name="product_seller")
public class ProductSeller {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne()
	private Seller seller;
	
	@ManyToOne()
	private Product product;

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProductSeller(Seller seller, Product product) {
		super();
		this.seller = seller;
		this.product = product;
	}

	public ProductSeller() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

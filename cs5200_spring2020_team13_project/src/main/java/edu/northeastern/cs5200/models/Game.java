package edu.northeastern.cs5200.models;

import javax.persistence.*;

@Entity
@Table(name="game")
public class Game extends Product {
	
	private boolean controllerSupport;
	
	public boolean isControllerSupport() {
		return controllerSupport;
	}
	public void setControllerSupport(boolean controllerSupport) {
		this.controllerSupport = controllerSupport;
	}
	public Game(String name, Double price, boolean controllerSupport) {
		super(name, price);
		this.controllerSupport = controllerSupport;
	}
	public Game() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Game(String name, Double price) {
		super(name, price);
		// TODO Auto-generated constructor stub
	}
	
	
}

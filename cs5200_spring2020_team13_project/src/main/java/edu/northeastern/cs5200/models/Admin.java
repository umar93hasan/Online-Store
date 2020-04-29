package edu.northeastern.cs5200.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class Admin extends User {

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String email, String password, String firstName, String lastName) {
		super(email, password, firstName, lastName);
		// TODO Auto-generated constructor stub
	}		

}

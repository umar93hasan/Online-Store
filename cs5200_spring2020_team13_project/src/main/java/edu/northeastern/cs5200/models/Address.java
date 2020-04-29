package edu.northeastern.cs5200.models;

import javax.persistence.*;

@Entity
@Table(name="address")
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String street1, street2, city, state, zip;
	
	@Transient
	private String residentEmail;
	
	public String getResidentEmail() {
		return residentEmail;
	}

	public void setResidentEmail(String residentEmail) {
		this.residentEmail = residentEmail;
	}

	@ManyToOne
	private User resident;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public User getResident() {
		return resident;
	}

	public void setResident(User resident) {
		this.resident = resident;
	}

	public Address(String street1, String street2, String city, String state, String zip, User resident) {
		super();
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.resident = resident;
	}

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

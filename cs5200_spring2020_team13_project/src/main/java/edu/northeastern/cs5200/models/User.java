package edu.northeastern.cs5200.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class User {

	@Id
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	
	@OneToMany(mappedBy="resident", fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<Address> addresses;
	
	public void addAddress(Address address) {
		this.addresses.add(address);
	}
	public void removeAddress(Address address) {
		this.addresses.remove(address);
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	
	public Set<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	public User(String email, String password, String firstName, String lastName) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.addresses = new HashSet<Address>();
	}
	public User() {
		super();
		this.addresses = new HashSet<Address>();
		// TODO Auto-generated constructor stub
	}
	
}

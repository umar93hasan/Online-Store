package edu.northeastern.cs5200.models;

import javax.persistence.*;

@Entity
@Table(name="book")
public class Book extends Product {
	private String author;
	private String genre;
	private String publisher;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Book(String name, Double price, String author, String genre, String publisher) {
		super(name, price);
		this.author = author;
		this.genre = genre;
		this.publisher = publisher;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String name, Double price) {
		super(name, price);
		// TODO Auto-generated constructor stub
	}
	
	
}

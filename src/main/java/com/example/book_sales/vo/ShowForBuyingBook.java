package com.example.book_sales.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ShowForBuyingBook {

	private String bookName;
	
	private String isbn;
	
	private String author;
	
	private int price;
	
	private int quantity;
	
	private int priceTotal;

	public ShowForBuyingBook() {
		
	}

	public ShowForBuyingBook(String bookName, String isbn, String author, int price, int quantity, int priceTotal) {
		super();
		this.bookName = bookName;
		this.isbn = isbn;
		this.author = author;
		this.price = price;
		this.quantity = quantity;
		this.priceTotal = priceTotal;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(int priceTotal) {
		this.priceTotal = priceTotal;
	}
	
}

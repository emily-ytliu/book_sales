package com.example.java_project_01.vo;

import java.util.List;
import java.util.Map;

import com.example.java_project_01.entity.BookSales;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookSalesRequest {

	@JsonProperty("book")
	private BookSales bookSales;
	
	private String category;
	
	@JsonProperty("is_customer")
	private boolean isCustomer;
	
	private String keyword;
	
	private String isbn;
	
	private int price;
	
	private int inventory;
	
	@JsonProperty("buy_book_map")
	private Map<String, Integer> buyBookMap;

	public BookSales getBookSales() {
		return bookSales;
	}

	public void setBookSales(BookSales bookSales) {
		this.bookSales = bookSales;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isCustomer() {
		return isCustomer;
	}

	public void setCustomer(boolean isCustomer) {
		this.isCustomer = isCustomer;
	}
		
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public Map<String, Integer> getBuyBookMap() {
		return buyBookMap;
	}

	public void setBuyBookMap(Map<String, Integer> buyBookMap) {
		this.buyBookMap = buyBookMap;
	}
	
}

package com.example.java_project_01.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "")
public class BookSales {

	@Column(name = "book_name")
	private String bookName;
	
	@Id
	@Column(name = "ISBN")
	private String isbn;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "price")
	private int price;
	
	@Column(name = "inventory")
	private int inventory;
	
	@Column(name = "sales")
	private int sales;
	
	@Column(name = "category")
	private List<String> categoryList;
	
	public BookSales() {
		
	}

	public BookSales(String bookName, String isbn, String author, int price, int inventory, int sales,
			List<String> categoryList) {
		super();
		this.bookName = bookName;
		this.isbn = isbn;
		this.author = author;
		this.price = price;
		this.inventory = inventory;
		this.sales = sales;
		this.categoryList = categoryList;
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

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public List<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}

}

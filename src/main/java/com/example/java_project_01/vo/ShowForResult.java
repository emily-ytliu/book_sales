package com.example.java_project_01.vo;

public class ShowForResult {

	private String bookName;
	
	private String isbn;
	
	private String author;
	
	private int price;
	
	private int inventory;
	
	private int sales;
	
	private String category;

	public ShowForResult() {
		
	}

	//基本
	public ShowForResult(String bookName, String isbn, String author, int price, int inventory) {
		super();
		this.bookName = bookName;
		this.isbn = isbn;
		this.author = author;
		this.price = price;
		this.inventory = inventory;
	}
	
	//方法三: 消費者
	public ShowForResult(String bookName, String isbn, String author, int price) {
		super();
		this.bookName = bookName;
		this.isbn = isbn;
		this.author = author;
		this.price = price;
	}
	
	//方法三: 書商
	public ShowForResult(String bookName, String isbn, String author, int price, int inventory, int sales) {
		super();
		this.bookName = bookName;
		this.isbn = isbn;
		this.author = author;
		this.price = price;
		this.inventory = inventory;
		this.sales = sales;
	}
	
	//方法四: 分類
	public ShowForResult(String bookName, String isbn, String author, int price, String category) {
		super();
		this.bookName = bookName;
		this.isbn = isbn;
		this.author = author;
		this.price = price;
		this.category = category;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}

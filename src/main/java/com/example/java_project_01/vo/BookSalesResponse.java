package com.example.java_project_01.vo;

import com.example.java_project_01.entity.BookSales;

public class BookSalesResponse {

	private BookSales bookSales;
	
	private String message;
	
	public BookSalesResponse() {
		
	}

	public BookSalesResponse(BookSales bookSales, String message) {
		this.bookSales = bookSales;
		this.message = message;
	}

	public BookSalesResponse(String message) {
		this.message = message;
	}

	public BookSales getBookSales() {
		return bookSales;
	}

	public void setBookSales(BookSales bookSales) {
		this.bookSales = bookSales;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}

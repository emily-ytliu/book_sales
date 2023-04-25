package com.example.java_project_01.vo;

import com.example.java_project_01.entity.BookSales;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookSalesRequest {

	@JsonProperty("book")
	private BookSales bookSales;

	public BookSales getBookSales() {
		return bookSales;
	}

	public void setBookSales(BookSales bookSales) {
		this.bookSales = bookSales;
	}
	
}

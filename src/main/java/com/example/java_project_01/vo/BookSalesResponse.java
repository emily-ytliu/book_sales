package com.example.java_project_01.vo;

import java.util.List;

import com.example.java_project_01.entity.BookSales;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookSalesResponse {
	
	private List<ShowForBuyingBook> showForBuyingBook;
	
	private List<ShowForResult> showForResult;
	
	private BookSales bookSales;
	
	private String message;
	
	public BookSalesResponse() {
		
	}

	public BookSalesResponse(List<ShowForBuyingBook> showForBuyingBook) {
		super();
		this.showForBuyingBook = showForBuyingBook;
	}


	public BookSalesResponse(List<ShowForResult> showForResult, String message) {
		this.showForResult = showForResult;
		this.message = message;
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

	public List<ShowForResult> getShowForResult() {
		return showForResult;
	}

	public void setShowForResult(List<ShowForResult> showForResult) {
		this.showForResult = showForResult;
	}

	public List<ShowForBuyingBook> getShowForBuyingBook() {
		return showForBuyingBook;
	}

	public void setShowForBuyingBook(List<ShowForBuyingBook> showForBuyingBook) {
		this.showForBuyingBook = showForBuyingBook;
	}
	
	
}

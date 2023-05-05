package com.example.book_sales.vo;

import java.util.List;

import com.example.book_sales.entity.BookSales;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class BookSalesResponse {
	
	private List<BookSales> bookSalesList;
	
//	private List<ShowForBuyingBook> showForBuyingBook;
//	
//	private List<ShowForResult> showForResult;
	
	private BookSales bookSales;
	
	private String message;
	
	public BookSalesResponse() {
		
	}
	
	public BookSalesResponse(List<BookSales> bookSalesList, String message) {
		this.bookSalesList = bookSalesList;
		this.message = message;
	}

//	public BookSalesResponse(List<ShowForBuyingBook> showForBuyingBook, String message) {
//		this.showForBuyingBook = showForBuyingBook;
//		this.message = message;
//	}
//
//
//	public BookSalesResponse(List<ShowForResult> showForResult, String message) {
//		this.showForResult = showForResult;
//		this.message = message;
//	}

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

	public List<BookSales> getBookSalesList() {
		return bookSalesList;
	}

	public void setBookSalesList(List<BookSales> bookSalesList) {
		this.bookSalesList = bookSalesList;
	}
	
//	public List<ShowForResult> getShowForResult() {
//		return showForResult;
//	}
//
//	public void setShowForResult(List<ShowForResult> showForResult) {
//		this.showForResult = showForResult;
//	}
//
//	public List<ShowForBuyingBook> getShowForBuyingBook() {
//		return showForBuyingBook;
//	}
//
//	public void setShowForBuyingBook(List<ShowForBuyingBook> showForBuyingBook) {
//		this.showForBuyingBook = showForBuyingBook;
//	}
	
	
}

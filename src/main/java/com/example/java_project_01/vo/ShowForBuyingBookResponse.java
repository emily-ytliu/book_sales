package com.example.java_project_01.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ShowForBuyingBookResponse {

	private List<ShowForBuyingBook> showForBuyingBook;
	
	private String message;
	
	public ShowForBuyingBookResponse() {
		
	}

	public ShowForBuyingBookResponse(List<ShowForBuyingBook> showForBuyingBook, String message) {
		super();
		this.showForBuyingBook = showForBuyingBook;
		this.message = message;
	}
	
	public ShowForBuyingBookResponse(String message) {
		super();
		this.message = message;
	}

	public List<ShowForBuyingBook> getShowForBuyingBook() {
		return showForBuyingBook;
	}

	public void setShowForBuyingBook(List<ShowForBuyingBook> showForBuyingBook) {
		this.showForBuyingBook = showForBuyingBook;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}

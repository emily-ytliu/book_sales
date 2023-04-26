package com.example.java_project_01.vo;

import java.util.List;

import com.example.java_project_01.entity.BookSales;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookSalesRequest {

	@JsonProperty("book")
	private BookSales bookSales;
	
	@JsonProperty("cate")
	private List<String> categoryList;

	public BookSales getBookSales() {
		return bookSales;
	}

	public void setBookSales(BookSales bookSales) {
		this.bookSales = bookSales;
	}

	public List<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}

	
	
}

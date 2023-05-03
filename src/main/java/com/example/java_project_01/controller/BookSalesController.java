package com.example.java_project_01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_project_01.service.ifs.BookSalesService;
import com.example.java_project_01.vo.BookSalesRequest;
import com.example.java_project_01.vo.BookSalesResponse;
import com.example.java_project_01.vo.ShowForBuyingBookResponse;
import com.example.java_project_01.vo.ShowForResultResponse;

@RestController
public class BookSalesController {

	@Autowired
	private BookSalesService bookSalesService;
	
	@PostMapping(value = "add_book")
	public BookSalesResponse addBookInfo(@RequestBody BookSalesRequest request) {
		return bookSalesService.addBookInfo(request.getBookSales());
	}
	
	@PostMapping(value = "search_by_category")
	public BookSalesResponse searchByCategory(@RequestBody BookSalesRequest request) {
		return bookSalesService.searchByCategory(request.getCategory());
	}
	
	@PostMapping(value = "search_by_keyword")
	public ShowForResultResponse searchByKeyword(@RequestBody BookSalesRequest request) {
		return bookSalesService.searchByKeyword(request.isCustomer(), request.getKeyword());
	}
	
	@PostMapping(value = "update")
	public ShowForResultResponse updateBookInfo(@RequestBody BookSalesRequest request) {
		return bookSalesService.updateBookInfo(request.getIsbn(), request.getPrice(), request.getInventory(), request.getCategory());
	}
	
	@PostMapping(value = "buy_book")
	public ShowForBuyingBookResponse buyBookByIsbn(@RequestBody BookSalesRequest request) {
		return bookSalesService.buyBookByIsbn(request.getBuyBookMap());
	}
	
	@PostMapping(value = "best_seller")
	public ShowForResultResponse getBestSellerTop5() {
		return bookSalesService.getBestSellerTop5();
	}
}

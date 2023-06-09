package com.example.book_sales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.book_sales.service.ifs.BookSalesService;
import com.example.book_sales.vo.BookSalesRequest;
import com.example.book_sales.vo.BookSalesResponse;
import com.example.book_sales.vo.ShowForBuyingBookResponse;
import com.example.book_sales.vo.ShowForResultResponse;

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
		return bookSalesService.searchByKeyword(request.isSeller(), request.getKeyword());
	}
	
	@PostMapping(value = "update")
	public ShowForResultResponse updateBookInfo(@RequestBody BookSalesRequest request) {
		return bookSalesService.updateBookInfo(request.getIsbn(), request.getPrice(), request.getInventory(), request.getCategory());
	}
	
	@PostMapping(value = "update_price")
	public ShowForResultResponse updateBookPrice(@RequestBody BookSalesRequest request) {
		return bookSalesService.updateBookPrice(request.getUpdatePriceMap());
	}
	
	@PostMapping(value = "update_inventory")
	public ShowForResultResponse updateBookInventory(@RequestBody BookSalesRequest request) {
		return bookSalesService.updateBookInventory(request.getUpdateInventoryMap());
	}
	
	@PostMapping(value = "update_category")
	public ShowForResultResponse updateBookCategory(@RequestBody BookSalesRequest request) {
		return bookSalesService.updateBookCategory(request.getUpdateCategoryMap());
	}
	
	@PostMapping(value = "buy_book")
	public ShowForBuyingBookResponse buyBookByIsbn(@RequestBody BookSalesRequest request) {
		return bookSalesService.buyBookByIsbn(request.getBuyBookMap());
	}
	
	//寫Get，是請求資源，@RequestBody是空的
	@GetMapping(value = "best_seller")
	public ShowForResultResponse getBestSellerTop5() {
		return bookSalesService.getBestSellerTop5();
	}
}

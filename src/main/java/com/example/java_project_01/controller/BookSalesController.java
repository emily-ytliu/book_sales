package com.example.java_project_01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_project_01.service.ifs.BookSalesService;
import com.example.java_project_01.vo.BookSalesRequest;
import com.example.java_project_01.vo.BookSalesResponse;

@RestController
public class BookSalesController {

	@Autowired
	private BookSalesService bookSalesService;
	
	@PostMapping(value = "add_book")
	public BookSalesResponse addBookInfo(@RequestBody BookSalesRequest request) {
		return bookSalesService.addBookInfo(request.getBookSales());
	}
	
	@PostMapping(value = "find_by_category")
	public BookSalesResponse findByCategory(@RequestBody BookSalesRequest request) {
		return bookSalesService.findByCategoryList(request.getCategoryList());
	}
}

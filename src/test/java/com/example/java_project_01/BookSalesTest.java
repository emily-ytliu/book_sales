package com.example.java_project_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.java_project_01.entity.BookSales;
import com.example.java_project_01.repository.BookSalesDao;
import com.example.java_project_01.service.ifs.BookSalesService;

@SpringBootTest(classes = JavaProject01Application.class)
public class BookSalesTest {

	@Autowired
	private BookSalesDao bookSalesDao;
	
	@Autowired
	private BookSalesService bookSalesService;
	
	//  4/26
	//新增假資料 (每一個方法前後)
	@BeforeEach
	public void beforeEach() {
		
	}
	
	//刪除假資料 (每一個方法前後)
	@AfterEach
	public void aftereEach() {
		
	}
	
	//正常
	@Test
	public void addBookInfoTest() {
//		List<BookSales> booksales = new ArrayList<>(Arrays.asList("貓與海的彼端", "9786263524552", "巧喵", 237, 99, 1));
	}
	
	
}

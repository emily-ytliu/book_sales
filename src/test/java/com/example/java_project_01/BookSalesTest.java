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
import com.example.java_project_01.vo.BookSalesResponse;

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
	
	//===Dao==================================
		@Test
		public void findByCategoryContaining() {
			List<BookSales> result = bookSalesDao.findByCategoryContaining("中文");
			for (BookSales res : result) {
				System.out.println(res);
			}
		}
		
		@Test
		public void findByKeyword() {
			List<BookSales> result = bookSalesDao.findByKeyword("9786263524552");
			for (BookSales res : result) {
				System.out.println(res);
			}
		}
		
//		@Test
//		public void showForCustomerTest() {
//			List<Object[]> result = bookSalesDao.showForCustomer("中文");
//			for (Object[] res : result) {
//				System.out.println(res);
//			}
//		}
		
//		@Test
//		public void showForSellerTest() {
//			List<Object[]> result = bookSalesDao.showForSeller("中文");
//			for (Object[] res : result) {
//				System.out.println(res);
//			}
//		}
	
	//===方法一=======================
	//正常
	@Test
	public void addBookInfoTest() {
		BookSales booksales = new BookSales("貓與海的彼端", "9786263524552", "巧喵", 237, 99, 1, "中文,輕小說");
		BookSalesResponse result = bookSalesService.addBookInfo(booksales);
		System.out.println(result.getMessage());
	}
	
	//===方法二=======================
	@Test
	public void searchByCategoryTest() {
		String category = "中文";
		BookSalesResponse result = bookSalesService.searchByCategory(category);
		System.out.println(result.getMessage());
	}
	
	//===方法三=======================
	@Test
	public void searchByKeywordTest() {
		String keyword = "9786263524552";
		BookSalesResponse result = bookSalesService.searchByKeyword(true, keyword);
		System.out.println(result.getMessage());
	}
	
	
}

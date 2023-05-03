package com.example.java_project_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.java_project_01.entity.BookSales;
import com.example.java_project_01.repository.BookSalesDao;
import com.example.java_project_01.service.ifs.BookSalesService;
import com.example.java_project_01.vo.BookSalesResponse;
import com.example.java_project_01.vo.ShowForBuyingBookResponse;
import com.example.java_project_01.vo.ShowForResultResponse;

@SpringBootTest(classes = JavaProject01Application.class)
public class BookSalesTest {

	@Autowired
	private BookSalesDao bookSalesDao;
	
	@Autowired
	private BookSalesService bookSalesService;
	
//	//  4/26
//	//新增假資料 (每一個方法前後)
//	@BeforeEach
//	public void beforeEach() {
//		
//	}
//	
//	//刪除假資料 (每一個方法前後)
//	@AfterEach
//	public void aftereEach() {
//		
//	}
	
	//===Dao==================================
//	@Test
//	public void findByCategoryContainingTest() {
//		List<BookSales> result = bookSalesDao.findByCategoryContaining("中文");
//		System.out.println(result.size());
//	}
	
	@Test
	public void findByCategoryTest() {
		List<BookSales> result = bookSalesDao.findByCategory("中文");
		System.out.println(result.size());
	}
	
	@Test
	public void findByKeywordTest() {
		List<BookSales> result = bookSalesDao.findByKeyword("9786263524552");
		System.out.println(result.size());
	}
	
	@Test
	public void findByIsbnForSearchingTest() {
		List<BookSales> result = bookSalesDao.findByIsbnForSearching("9786263524552");
		System.out.println(result.size());
	}
	
	@Test
	public void findByBookNameAndIsbnAndAuthorAndPriceTest() {
		List<BookSales> result = bookSalesDao.findByBookNameAndIsbnAndAuthorAndPrice();
		System.out.println(result.size());
	}
	
	@Test
	public void findTopLimitNumOrderBySalesDescTest() {
		List<BookSales> result = bookSalesDao.findTopLimitNumOrderBySalesDesc(5);
		System.out.println(result.size());
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
		BookSales booksales = new BookSales("測試ISBN", "0000000001", "test", 100, 10, 5, "test, test");
		BookSalesResponse result = bookSalesService.addBookInfo(booksales);
		System.out.println(result.getMessage());
	}
	//檢查1: 輸入的bookSales 不能是null
	//檢查2: 輸入的bookSales的每項資訊
	@Test
	public void addBookInfoCheck1And2Test() {
		BookSales booksales = new BookSales(" ", "9786263524552", "巧喵", 237, 99, 1, "中文,輕小說");
		BookSalesResponse result = bookSalesService.addBookInfo(booksales);
		System.out.println(result.getMessage());
	}
	//檢查3: ISBN格式
	@Test
	public void addBookInfoCheck3Test() {
		BookSales booksales = new BookSales("測試ISBN", "1234567890", "test", 100, 1, 1, "test");
		BookSalesResponse result = bookSalesService.addBookInfo(booksales);
		System.out.println(result.getMessage());
	}
	//檢查4: 輸入的category裡的每個分類 不能是空、不能是全空白...待check: 逗點不能在最後
	@Test
	public void addBookInfoCheck4Test() {
		BookSales booksales = new BookSales("測試ISBN", "0000000010", "test", 100, 1, 1, "test,");
		BookSalesResponse result = bookSalesService.addBookInfo(booksales);
		System.out.println(result.getMessage());
	}
	//確認: 要新增的bookSales是否已經存在資料庫(不存在的才能新增)
	@Test
	public void addBookInfoConfirmTest() {
		BookSales booksales = new BookSales("測試ISBN", "0000000001", "test", 100, 10, 5, "test, test");
		BookSalesResponse result = bookSalesService.addBookInfo(booksales);
		System.out.println(result.getMessage());
	}
	
	//===方法二=======================
	//正常
	@Test
	public void searchByCategoryTest() {
		String category = "中文";
		BookSalesResponse result = bookSalesService.searchByCategory(category);
		System.out.println(result.getMessage());
	}
	//檢查: 輸入的String 不能是null、空字串、全空白
	@Test
	public void searchByCategoryCheckTest() {
		String category = "";
		BookSalesResponse result = bookSalesService.searchByCategory(category);
		System.out.println(result.getMessage());
	}
	//確認: 資料庫有沒有輸入的category的資料
	@Test
	public void searchByCategoryConfirmTest() {
		String category = "日文";
		BookSalesResponse result = bookSalesService.searchByCategory(category);
		System.out.println(result.getMessage());
	}
	
	//===方法三=======================
	//正常
	@Test
	public void searchByKeywordTest() {
		String keyword = "9786263524552";
		ShowForResultResponse result = bookSalesService.searchByKeyword(true, keyword);
		System.out.println(result.getMessage());
	}
	
	//===方法四=======================
	//正常
	@Test
	public void updateBookInfoTest() {
		BookSalesResponse result = bookSalesService.updateBookInfo("9786263524552", 180, 99, "中文,輕小說");
		System.out.println(result.getMessage());
	}
	
	//===方法五=======================
	//正常
	@Test
	public void buyBookByIsbnTest() {
		Map<String, Integer> map = new HashMap<>();
		map.put("9781801312592", 2);
		ShowForBuyingBookResponse result = bookSalesService.buyBookByIsbn(map);
		System.out.println(result.getMessage());
	}
	
	//===方法六=======================
	//正常
	@Test
	public void getBestSellerTop5() {
		ShowForResultResponse result = bookSalesService.getBestSellerTop5();
		System.out.println(result.getMessage());
	}
}

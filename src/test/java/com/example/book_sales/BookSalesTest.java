package com.example.book_sales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.example.book_sales.bookSalesApplication;
import com.example.book_sales.entity.BookSales;
import com.example.book_sales.repository.BookSalesDao;
import com.example.book_sales.service.ifs.BookSalesService;
import com.example.book_sales.vo.BookSalesResponse;
import com.example.book_sales.vo.ShowForBuyingBookResponse;
import com.example.book_sales.vo.ShowForResultResponse;

@SpringBootTest(classes = bookSalesApplication.class)
public class BookSalesTest {

	@Autowired
	private BookSalesDao bookSalesDao;
	
	@Autowired
	private BookSalesService bookSalesService;
	
	//  4/26
//	//新增假資料 (每一個方法前後)
//	@BeforeEach
//	public void beforeEach() {
//		BookSales oldData = bookSalesDao.save(new BookSales("測試ISBN", "0000000002", "test", 100, 10, 5, "test, test"));
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
	
	/*
	 * Assert.isTrue(false, null) 怎麼寫?
	 */
	
	@Test
	public void findByCategoryTest() {
		List<BookSales> result = bookSalesDao.findByCategory("中文");
		System.out.println(result.size());
//		Assert.isTrue(false, null);
	}
	
	@Test
	public void findByKeywordTest() {
//		List<String> keyword = new ArrayList<>(Arrays.asList("A", "P"));
//		List<BookSales> result = bookSalesDao.findByKeyword(keyword);
		List<BookSales> result = bookSalesDao.findByKeyword("9786263524552");
		System.out.println(result.size());
	}
	
//	@Test
//	public void findByIsbnForSearchingTest() {
//		List<BookSales> result = bookSalesDao.findByIsbnForSearching("9786263524552");
//		System.out.println(result.size());
//	}
	
//	@Test
//	public void findByBookNameAndIsbnAndAuthorAndPriceTest() {
//		List<BookSales> result = bookSalesDao.findByBookNameAndIsbnAndAuthorAndPrice();
//		System.out.println(result.size());
//	}
	
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
	
	//防呆1: 輸入的bookSales 不能是null
	//防呆2: 輸入的bookSales的每項資訊
	@Test
	public void addBookInfoCheck1And2Test() {
		BookSales booksales = new BookSales(" ", "9786263524552", "巧喵", 237, 99, 1, "中文,輕小說");
		BookSalesResponse result = bookSalesService.addBookInfo(booksales);
		System.out.println(result.getMessage());
	}
	
	//檢查1: ISBN格式
	@Test
	public void addBookInfoCheck3Test() {
		BookSales booksales = new BookSales("測試ISBN", "1234567890123", "test", 100, 1, 1, "test");
		BookSalesResponse result = bookSalesService.addBookInfo(booksales);
		System.out.println(result.getMessage());
	}
	
	//檢查2: 輸入的category裡的每個分類 不能是空、不能是全空白
	@Test
	public void addBookInfoCheck4Test() {
		BookSales booksales = new BookSales("測試ISBN", "000000001", "test", 100, 1, 1, "test, , test,  ");
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
	
	//防呆: 輸入的String 不能是null、空字串、全空白
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
		String keyword = "9786263524552 chicken";
		ShowForResultResponse result = bookSalesService.searchByKeyword(true, keyword);
		System.out.println(result.getMessage());
	}
	
	//防呆: 輸入的boolean 不能是null
	//      輸入的String 不能是null、不能是空字串、不能是全空白
	@Test
	public void searchByKeywordCheckTest() {
		String keyword = "";
		ShowForResultResponse result = bookSalesService.searchByKeyword(false, keyword);
		System.out.println(result.getMessage());
	}
	
	//===方法四=======================
	//正常
	@Test
	public void updateBookInfoTest() {
		ShowForResultResponse result = bookSalesService.updateBookInfo("1234567890", 99, 55, "test, test");
		System.out.println(result.getMessage());
	}
	
	//防呆: 輸入的每個項目
	//      String: bookName、isbn、author、category 不能是null、不能是空、不能是全空白
	//      int: price、inventory、sales 不能是負數
	@Test
	public void updateBookInfoCheck1Test() {
		ShowForResultResponse result = bookSalesService.updateBookInfo("9786263524552", -400, 99, "中文, 輕小說");
		System.out.println(result.getMessage());
	}
	
	//確認1: 資料庫是否有此Isbn的資料
	@Test
	public void updateBookInfoConfirm1Test() {
		ShowForResultResponse result = bookSalesService.updateBookInfo("0000000000000", 200, 77, "中文");
		System.out.println(result.getMessage());
	}
	
	//確認2: 如果price和inventory和category與資料庫資料相同，就不更改
	@Test
	public void updateBookInfoConfirm2Test() {
		ShowForResultResponse result = bookSalesService.updateBookInfo("0000000001", 0, 77, "中文");
		System.out.println(result.getMessage());
	}
	
	//檢查1: 輸入的庫存 要大於 原本資料庫的庫存 (因為是進貨)
	@Test
	public void updateBookInfoConfirm3Test() {
		ShowForResultResponse result = bookSalesService.updateBookInfo("0000000001", 200, 79, "中文");
		System.out.println(result.getMessage());
	}
	
	//檢查2: 輸入的category裡的每個分類 不能是null、不能是空、不能是全空白
	@Test
	public void updateBookInfoCheck2Test() {
		ShowForResultResponse result = bookSalesService.updateBookInfo("9786263524552", 200, 77, "中文,   ");
		System.out.println(result.getMessage());
	}

	//===方法四-1=======================
	//更新書籍資訊(價格)
	//正常
	@Test
	public void updateBookPriceTest() {
		Map<String, Integer> map = new HashMap<>();
		map.put("0000000001", 300);
		map.put("1234567890123", 200);
		ShowForResultResponse result = bookSalesService.updateBookPrice(map);
		System.out.println(result.getMessage());
	}
	
	//防呆: String isbn 不能是null、不能是空字串、不能是全空白
	//      int price 不能是負數
	@Test
	public void updateBookPriceCheckTest() {
		Map<String, Integer> map = new HashMap<>();
		map.put("0000000001", -400);
		ShowForResultResponse result = bookSalesService.updateBookPrice(map);
		System.out.println(result.getMessage());
	}
	
	//如果輸入的price 等於 資料庫的price
	@Test
	public void updateBookPriceConfirmTest() {
		Map<String, Integer> map = new HashMap<>();
		map.put("0000000001", 400);
		ShowForResultResponse result = bookSalesService.updateBookPrice(map);
		System.out.println(result.getMessage());
	}
	
	//===方法四-2=======================
	//更新書籍資訊(庫存量)
	//正常
	@Test
	public void updateBookInventoryTest() {
		Map<String, Integer> map = new HashMap<>();
		map.put("0000000001", 96);
		map.put("1234567890123", 10);
		ShowForResultResponse result = bookSalesService.updateBookInventory(map);
		System.out.println(result.getMessage());
	}
	
	//防呆: String isbn 不能是null、不能是空字串、不能是全空白
	//      int inventory 不能是負數
	@Test
	public void updateBookInventoryCheckTest() {
		Map<String, Integer> map = new HashMap<>();
		map.put("0000000001", -95);
		ShowForResultResponse result = bookSalesService.updateBookInventory(map);
		System.out.println(result.getMessage());
	}
	
	//確認: 輸入的庫存量(因為進貨) 要大於 資料庫的inventory
	@Test
	public void updateBookInventoryConfirm1Test() {
		Map<String, Integer> map = new HashMap<>();
		map.put("0000000001", 80);
		ShowForResultResponse result = bookSalesService.updateBookInventory(map);
		System.out.println(result.getMessage());
	}
	
	//如果輸入的庫存 等於 資料庫的inventory
	@Test
	public void updateBookInventoryConfirm2Test() {
		Map<String, Integer> map = new HashMap<>();
		map.put("0000000001", 95);
		ShowForResultResponse result = bookSalesService.updateBookInventory(map);
		System.out.println(result.getMessage());
	}
	
	//===方法四-3=======================
	//更新書籍資訊(分類)
	//正常
	@Test
	public void updateBookCategoryTest() {
		Map<String, String> map = new HashMap<>();
		map.put("0000000001", "中文, 繪本, 兒童");
		map.put("1234567890123", "英文, 兒童, 繪本");
		ShowForResultResponse result = bookSalesService.updateBookCategory(map);
		System.out.println(result.getMessage());
	}
	
	@Test
	public void SetTest() {
		//檢查: 用Set來避免重複分類項目的出現
		Set<String> cateList = new HashSet<>();
//		String str = "A, B, C";
//		str = "A, C, B";
		String str = "A, C, B";
		String ary[] = str.replace(" ", "").split(",");  //去除掉所有空格，再用逗點切
		List<String> list = Arrays.asList(ary);  //Array轉成List
		cateList.addAll(list);
		
        //輸入的category裡的每個分類 不能是null、不能是空、不能是全空白
		List<String> resCateList = new ArrayList<String>();
		for (String CateItem : cateList) {
			if (!StringUtils.hasText(CateItem)) {
				continue;
			}
			resCateList.add(CateItem);
		}
		
		//List轉成String，同時用", "去連接
		String resCateStr = String.join(", ", resCateList); 
		
		System.out.println(resCateStr);
	}
	
	
	//防呆: String isbn和category 不能是null、不能是空、不能是全空白
	@Test
	public void updateBookCategoryCheck1Test() {
		Map<String, String> map = new HashMap<>();
		map.put("1234567890123", "英文, 繪本");
		ShowForResultResponse result = bookSalesService.updateBookCategory(map);
		System.out.println(result.getMessage());
	}
	
	//如果 輸入的分類 相同於 資料庫的category
	@Test
	public void updateBookCategoryCheck2Test() {
		Map<String, String> map = new HashMap<>();
		map.put("1234567890123", "英文");
		ShowForResultResponse result = bookSalesService.updateBookCategory(map);
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
	
	//防呆: 輸入的String isbn 不能是null、不能是空、不能是全空白
	//      輸入的int quantity 不能是負數
	@Test
	public void buyBookByIsbnCheckTest() {
		Map<String, Integer> map = new HashMap<>();
		map.put("   ", 2);
		ShowForBuyingBookResponse result = bookSalesService.buyBookByIsbn(map);
		System.out.println(result.getMessage());
	}
	
	//確認1: 資料庫是否存在isbnList的isbn
	@Test
	public void buyBookByIsbnConfirm1Test() {
		Map<String, Integer> map = new HashMap<>();
		map.put("0000000000", 2);
		ShowForBuyingBookResponse result = bookSalesService.buyBookByIsbn(map);
		System.out.println(result.getMessage());
	}
	
	//確認2: 資料庫的inventory(庫存要大於書輸入的購買量)
	@Test
	public void buyBookByIsbnConfirm2Test() {
		Map<String, Integer> map = new HashMap<>();
		map.put("9781801312592", 999);
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

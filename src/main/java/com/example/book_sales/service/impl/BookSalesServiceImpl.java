package com.example.book_sales.service.impl;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.book_sales.constants.RtnCode;
import com.example.book_sales.entity.BookSales;
import com.example.book_sales.repository.BookSalesDao;
import com.example.book_sales.service.ifs.BookSalesService;
import com.example.book_sales.vo.BookSalesResponse;
import com.example.book_sales.vo.ShowForBuyingBook;
import com.example.book_sales.vo.ShowForBuyingBookResponse;
import com.example.book_sales.vo.ShowForResult;
import com.example.book_sales.vo.ShowForResultResponse;

@Service
public class BookSalesServiceImpl implements BookSalesService{

	@Autowired
	private BookSalesDao bookSalesDao;;

	//===方法一=======================
	//新增書籍資訊
	@Override
	public BookSalesResponse addBookInfo(BookSales bookSales) {
		//檢查1: 輸入的bookSales 不能是null
		//檢查2: 輸入的bookSales的每項資訊
		//      String: bookName, isbn, author 不能是null、不能是空字串、不能是全空白
		//      int: price, inventory, sales 不能是負數
		//      List<String>: category 不能是null、不能是空陣列
		if (bookSales == null
				|| !StringUtils.hasText(bookSales.getBookName())
				|| !StringUtils.hasText(bookSales.getIsbn())
				|| !StringUtils.hasText(bookSales.getAuthor())
				|| bookSales.getPrice() <= 0 
				|| !StringUtils.hasText(bookSales.getCategory())) {
			return new BookSalesResponse(RtnCode.DATA_ERROR.getMessage());
		}
		bookSales.setInventory(0);
		bookSales.setSales(0);
		//確認: 要新增的bookSales是否已經存在資料庫(不存在的才能新增)
		Optional<BookSales> op = bookSalesDao.findById(bookSales.getIsbn());
		if (op.isPresent()) {
			return new BookSalesResponse(RtnCode.ALREADY_EXIST.getMessage());
		}
		
		//檢查3: ISBN格式
		String pattern = "^\\d{10}(\\d{3})?$";  //可以輸入10位數字，或者10+3=13位數字  //? 表示出現0次或1次
//		String pattern = "\\d{10}|\\d{13}";
		if (!bookSales.getIsbn().matches(pattern)) {
			return new BookSalesResponse(RtnCode.DATA_ERROR.getMessage());
		}
		
		//檢查4: 輸入的category裡的每個分類 不能是null、不能是空、不能是全空白
		String ary[] = bookSales.getCategory().replace(" ", "").split(",");  //去除掉所有空格，再用逗點切
		List<String> list = Arrays.asList(ary);  //Array轉成List
		List<String> resList = new ArrayList<String>();
		for (String item : list) {
			if (!StringUtils.hasText(item)) {
				return new BookSalesResponse(RtnCode.DATA_ERROR.getMessage());
			}
			resList.add(item);
		}
		String cateStr = resList.toString();  //List轉成String
		String resCateStr = cateStr.substring(1, cateStr.length()-1);  //去掉中括號
		bookSales.setCategory(resCateStr);
		
		
		return new BookSalesResponse(bookSalesDao.save(bookSales), RtnCode.SUCCESSFUL.getMessage());
	}

	//===方法二=======================
	//分類搜尋
	@Override
	public BookSalesResponse searchByCategory(String category) {
		//檢查: 輸入的String 不能是null、空字串、全空白
		if (!StringUtils.hasText(category)) {
			return new BookSalesResponse(RtnCode.DATA_ERROR.getMessage());
		}
		
		//確認: 資料庫有沒有輸入的category的資料
		List<BookSales> result = bookSalesDao.findByCategory(category);
		if (result.isEmpty()) {
			return new BookSalesResponse(RtnCode.NOT_FOUND.getMessage());
		}
		
		//分類: 只顯示書名、ISBN、作者、價格、庫存量
//		List<ShowForResult> forBasic = new ArrayList<>();
//		for (BookSales item : result) {
//			ShowForResult showForBasic = new ShowForResult(item.getBookName(), item.getIsbn(), 
//					item.getAuthor(), item.getPrice(), item.getInventory());
//			forBasic.add(showForBasic);
//		}
		
		//如果確定資料庫有此分類的書籍，回傳	
		return new BookSalesResponse(result, RtnCode.SUCCESSFUL.getMessage());
	}

	//===方法三=======================
	//消費者或書商搜尋
	@Override
	public ShowForResultResponse searchByKeyword(Boolean isCustomer, String keyword) {
		//檢查: 輸入的boolean 不能是null
		//      輸入的String 不能是null、不能是空字串、不能是全空白
		if (isCustomer == null || !StringUtils.hasText(keyword)) {
			return new ShowForResultResponse(RtnCode.DATA_ERROR.getMessage());
		}
		
		//確認: 資料庫是否存在輸入的keyword
		List<BookSales> result = bookSalesDao.findByKeyword(keyword);
		if (result.isEmpty()) {
			return new ShowForResultResponse(RtnCode.NOT_FOUND.getMessage());
		}
		
		//顯示限定的項目
		
		//如果是消費者
		if (isCustomer == true) {
			//消費者: 只顯示書名、ISBN、作者、價格
			List<ShowForResult> forCustomer = new ArrayList<>();
			for (BookSales item : result) {
				ShowForResult showForCustomer = new ShowForResult(item.getBookName(), item.getIsbn(), 
						item.getAuthor(), item.getPrice());
				forCustomer.add(showForCustomer);
			}
			return new ShowForResultResponse(forCustomer, RtnCode.SUCCESSFUL.getMessage());
		} 
		
		//如果是書商
		//書商: 只顯示書名、ISBN、作者、價格、庫存量、銷售量
		List<ShowForResult> forSeller = new ArrayList<>();
		for (BookSales item : result) {
			ShowForResult showForSeller = new ShowForResult(item.getBookName(), item.getIsbn(), 
					item.getAuthor(), item.getPrice(), item.getInventory(), item.getSales());
			forSeller.add(showForSeller);
		}
		return new ShowForResultResponse(forSeller, RtnCode.SUCCESSFUL.getMessage());
		
//		List<BookSales> resCustomer = bookSalesDao.showForCustomer(keyword);	
//		List<BookSales> resSeller = bookSalesDao.showForSeller(keyword);
//		if (resCustomer.isEmpty() || resSeller.isEmpty()) {
//			return new BookSalesResponse(RtnCode.NOT_FOUND.getMessage());
//		}
	}

	
	//===方法四=======================
	//更新書籍資訊
	@Override
	public ShowForResultResponse updateBookInfo(String isbn, int price, int inventory, String category) {
		//檢查1: 輸入的每個項目
		//      String: bookName、isbn、author、category 不能是null、不能是空、不能是全空白
		//      int: price、inventory、sales 不能是負數
		List<String> cateList = new ArrayList<>();
		if (!StringUtils.hasText(isbn)
				|| price < 0
				|| inventory < 0
				|| !StringUtils.hasText(category)) {
			return new ShowForResultResponse(RtnCode.DATA_ERROR.getMessage());
		}
		
		//檢查2: 輸入的category裡的每個分類 不能是null、不能是空、不能是全空白
		String ary[] = category.replace(" ", "").split(",");  //去除掉所有空格，再用逗點切
		List<String> list = Arrays.asList(ary);  //Array轉成List
		List<String> resList = new ArrayList<String>();
		for (String item : list) {
			if (!StringUtils.hasText(item)) {
				return new ShowForResultResponse(RtnCode.DATA_ERROR.getMessage());
			}
			resList.add(item);
		}
		String cateStr = resList.toString();  //List轉成String
		String resCateStr = cateStr.substring(1, cateStr.length()-1);  //去掉中括號
		
		//用isbn(PK)從資料庫找到某書籍資訊
		Optional<BookSales> op = bookSalesDao.findById(isbn);
		
		//確認1: 資料庫是否有此Isbn的資料
		if (!op.isPresent()) {
			return new ShowForResultResponse(RtnCode.NOT_FOUND.getMessage());
		}
		
		//確認2: 如果price和inventory和category與資料庫資料相同，就不更改
		if (price == op.get().getPrice() 
				&& inventory == op.get().getInventory() 
				&& category.equals(op.get().getCategory())) {
			return new ShowForResultResponse(RtnCode.SAME_DATA.getMessage());
		}
		//更新價格
		op.get().setPrice(price);
		
		//確認3: 輸入的庫存 要大於 原本資料庫的庫存 (因為是進貨)
		if (inventory < op.get().getInventory()) {
			return new ShowForResultResponse(RtnCode.INVENTORY.getMessage());
		}
		//更新庫存
		op.get().setInventory(inventory);
		
		//確認4: 如果分類項目相同，但順序不同，就不更改
		//category List長度相同，看輸入的每個分類項目是否與資料庫完全相同
//		List<String> DBCateList = Arrays.asList(op.get().getCategory());
//		if (resList.size() ==  DBCateList.size()) {
//			for (String item : resList) {
//				for (String DBItem : DBCateList) {
//					if (item.equals(DBItem)) {
//						return new ShowForResultResponse(RtnCode.SAME_DATA.getMessage());
//					}
//				}
//			}
//		}
		//更新分類
		op.get().setCategory(resCateStr);
		
		bookSalesDao.save(op.get());
		
		//只顯示書名、ISBN、作者、價格、庫存、分類
		List<ShowForResult> forUpdate = new ArrayList<>();
		ShowForResult showForUpdate = new ShowForResult(op.get().getBookName(), op.get().getIsbn(), 
				op.get().getAuthor(), op.get().getPrice(), op.get().getInventory(), op.get().getCategory());
		forUpdate.add(showForUpdate);
		
		return new ShowForResultResponse(forUpdate, RtnCode.SUCCESSFUL.getMessage());
	}

	//===方法五=======================
	//書籍銷售
	@Override
	public ShowForBuyingBookResponse buyBookByIsbn(Map<String, Integer> buyBookMap) {
		//檢查: 輸入的String isbn 不能是null、不能是空、不能是全空白
		//      輸入的int quantity 不能是負數
		List<String> isbnList = new ArrayList<>();
		Map<String, Integer> finalBookMap = new HashMap<>();
		int priceTotal = 0;
		for (Entry<String, Integer> map : buyBookMap.entrySet()) {
			if (!StringUtils.hasText(map.getKey()) || map.getValue() < 0) {
				return new ShowForBuyingBookResponse(RtnCode.DATA_ERROR.getMessage());
			}
			isbnList.add(map.getKey());
		}
		
		//確認1: 資料庫是否存在isbnList的isbn
		List<BookSales> result = bookSalesDao.findAllById(isbnList);
		if (result.isEmpty()) {
			return new ShowForBuyingBookResponse(RtnCode.NOT_FOUND.getMessage());
		}
		
		for (BookSales resItem: result) {
			for (Entry<String, Integer> map : buyBookMap.entrySet()) {
				//確認2: 資料庫的inventory(庫存要大於輸入的購買量)
				if (resItem.getInventory() < map.getValue()) {
					return new ShowForBuyingBookResponse(RtnCode.SHORTAGE.getMessage());
				}
				//庫存更新量、銷售更新量、計算總價格
				if (resItem.getIsbn().equals(map.getKey())) {
					int eachtotal = map.getValue() * resItem.getPrice();
					int finalInventory = resItem.getInventory() - map.getValue();
					int finalSales = resItem.getSales() + map.getValue();
					resItem.setInventory(finalInventory);
					resItem.setSales(finalSales);
					priceTotal += eachtotal;
				}
			}
		}
		
		//只顯示書名、ISBN、作者、價格、購買數量、購買總價格
		List<ShowForBuyingBook> buyBook = new ArrayList<>();
		for (BookSales item : result) {
			for (Entry<String, Integer> map : buyBookMap.entrySet()) {
				ShowForBuyingBook buyingBook = new ShowForBuyingBook(item.getBookName(), item.getIsbn(), 
					item.getAuthor(), item.getPrice(), map.getValue(), priceTotal);
			buyBook.add(buyingBook);
			}
		}
		
		bookSalesDao.saveAll(result);
		return new ShowForBuyingBookResponse(buyBook, RtnCode.SUCCESSFUL.getMessage());
	}

	//===方法六=======================
	//暢銷排行
	@Override
	public ShowForResultResponse getBestSellerTop5() {
		List<BookSales> bestSeller = bookSalesDao.findTopLimitNumOrderBySalesDesc(5);
		
		//顯示限定的項目
		//只顯示書名、ISBN、作者、價格
		List<ShowForResult> result = new ArrayList<>();
		for (BookSales item : bestSeller) {
			ShowForResult showForCustomer = new ShowForResult(item.getBookName(), item.getIsbn(), 
					item.getAuthor(), item.getPrice());
			result.add(showForCustomer);
		}
		
		return new ShowForResultResponse(result, RtnCode.SUCCESSFUL.getMessage());
	}
}

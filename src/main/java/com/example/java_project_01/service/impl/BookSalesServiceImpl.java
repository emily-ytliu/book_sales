package com.example.java_project_01.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.java_project_01.constants.RtnCode;
import com.example.java_project_01.entity.BookSales;
import com.example.java_project_01.repository.BookSalesDao;
import com.example.java_project_01.service.ifs.BookSalesService;
import com.example.java_project_01.vo.BookSalesResponse;
import com.example.java_project_01.vo.ShowForBuyingBook;
import com.example.java_project_01.vo.ShowForResult;

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
				|| bookSales.getPrice() < 0 
				|| bookSales.getInventory() < 0 
				|| bookSales.getSales() < 0
				|| !StringUtils.hasText(bookSales.getCategory())) {
			return new BookSalesResponse(RtnCode.DATA_ERROR.getMessage());
		}
		//檢查3: ISBN格式
		String pattern = "^\\d{10}(\\d{3}?)$";  //可以輸入10位數字，或者10+3=13位數字  //? 表示出現0次或1次
		if (!bookSales.getIsbn().matches(pattern)) {
			return new BookSalesResponse(RtnCode.DATA_ERROR.getMessage());
		}
			
		//確認: 要新增的bookSales是否已經存在資料庫(不存在的才能新增)
		Optional<BookSales> op = bookSalesDao.findById(bookSales.getIsbn());
		if (op.isPresent()) {
			return new BookSalesResponse(RtnCode.ALREADY_EXIST.getMessage());
		}
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
		//確認資料庫有沒有輸入的category的資料
		List<BookSales> result = bookSalesDao.findByCategoryContaining(category);
		if (result.isEmpty()) {
			return new BookSalesResponse(RtnCode.NOT_FOUND.getMessage());
		}
		//分類: 只顯示書名、ISBN、作者、價格、庫存量
		List<ShowForResult> forBasic = new ArrayList<>();
		for (BookSales item : result) {
			ShowForResult showForBasic = new ShowForResult(item.getBookName(), item.getIsbn(), 
					item.getAuthor(), item.getPrice(), item.getInventory());
			forBasic.add(showForBasic);
		}
		//如果確定資料庫有此分類的書籍，回傳	
		return new BookSalesResponse(forBasic, RtnCode.SUCCESSFUL.getMessage());
	}

	//===方法三=======================
	//消費者或書商搜尋
	@Override
	public BookSalesResponse searchByKeyword(Boolean isCustomer, String keyword) {
		//檢查: 輸入的boolean 不能是null
		//檢查: 輸入的String 不能是null、不能是空字串、不能是全空白
		if (isCustomer == null
				|| !StringUtils.hasText(keyword)) {
			return new BookSalesResponse(RtnCode.DATA_ERROR.getMessage());
		}
		//確認資料庫是否存在輸入的keyword
		List<BookSales> result = bookSalesDao.findByKeyword(keyword);
		if (result.isEmpty()) {
			return new BookSalesResponse(RtnCode.NOT_FOUND.getMessage());
		}
		//顯示限定的項目
		//消費者: 只顯示書名、ISBN、作者、價格
		List<ShowForResult> forCustomer = new ArrayList<>();
		for (BookSales item : result) {
			ShowForResult showForCustomer = new ShowForResult(item.getBookName(), item.getIsbn(), 
					item.getAuthor(), item.getPrice());
			forCustomer.add(showForCustomer);
		}
		//書商: 只顯示書名、ISBN、作者、價格、庫存量、銷售量
		List<ShowForResult> forSeller = new ArrayList<>();
		for (BookSales item : result) {
			ShowForResult showForSeller = new ShowForResult(item.getBookName(), item.getIsbn(), 
					item.getAuthor(), item.getPrice(), item.getInventory(), item.getSales());
			forSeller.add(showForSeller);
		}
		//如果是消費者
		if (isCustomer = true) {
			return new BookSalesResponse(forCustomer, RtnCode.SUCCESSFUL.getMessage());
		} 
		//如果是書商
		return new BookSalesResponse(forSeller, RtnCode.SUCCESSFUL.getMessage());
		
//		List<BookSales> resCustomer = bookSalesDao.showForCustomer(keyword);	
//		List<BookSales> resSeller = bookSalesDao.showForSeller(keyword);
//		if (resCustomer.isEmpty() || resSeller.isEmpty()) {
//			return new BookSalesResponse(RtnCode.NOT_FOUND.getMessage());
//		}
	}

	
	//===方法四=======================
	//更新書籍資訊
	@Override
	public BookSalesResponse updateBookInfo(String isbn, int price, int inventory, String category) {
		//檢查: 輸入的每個項目
		//     String: bookName、isbn、author、category 不能是null、不能是空、不能是全空白
		//     int: price、inventory、sales 不能是負數
		List<String> cateList = new ArrayList<>();
		if (!StringUtils.hasText(isbn)
				|| price < 0
				|| inventory < 0
				|| !StringUtils.hasText(category)) {
			return new BookSalesResponse(RtnCode.DATA_ERROR.getMessage());
		}
		//檢查: 輸入的category裡的每個分類 不能是null、不能是空、不能是全空白
		String ary[] = category.split(", ");  //ary是記憶體位址
		List<String> list = new ArrayList<>(Arrays.asList(ary));  //Array轉成List
		for (String cateItem : list) {
			if (!StringUtils.hasText(cateItem)) {
				continue;
			}
			cateList.add(cateItem);
		}
		//用isbn(PK)從資料庫找到某書籍資訊
		Optional<BookSales> op = bookSalesDao.findById(isbn);
		//把op強制轉成List
		List<BookSales> opRes = (List<BookSales>) op.get();
		//只顯示書名、ISBN、作者、價格、庫存、分類
		List<ShowForResult> forUpdate = new ArrayList<>();
		for (BookSales item : opRes) {
			ShowForResult showForUpdate = new ShowForResult(op.get().getBookName(), op.get().getIsbn(), 
					op.get().getAuthor(), op.get().getPrice(), op.get().getInventory(), op.get().getCategory());
			forUpdate.add(showForUpdate);
		}
		
		//如果 輸入的價格 和原本的資料庫不同，才能更新
		if (price == op.get().getPrice()) {
			return new BookSalesResponse(RtnCode.SAME_DATA.getMessage());
		}
		//更新價格
		op.get().setPrice(price);
		
		//如果 輸入的庫存 和原本的資料庫不同，才能更新
		if (inventory == op.get().getInventory()) {
			return new BookSalesResponse(RtnCode.SAME_DATA.getMessage());
		}	
		//更新庫存
		op.get().setInventory(inventory);
		
		//檢查: DB的category裡的每個分類 不能是null、不能是空、不能是全空白...需要檢查嗎?
		List<String> cateDBList = new ArrayList<>();
		String aryDB[] = op.get().getCategory().split(", ");  
		List<String> listDB = new ArrayList<>(Arrays.asList(aryDB));  //Array轉成List
		for (String cateItem : listDB) {
			if (!StringUtils.hasText(cateItem)) {
				continue;
			}
			cateDBList.add(cateItem);
		}
		//如果 輸入的分類List 和 資料庫的分類List 所有分類完全一樣
		for (String cateItem : cateList) {
			for (String cateDBItem : cateDBList) {
				if (cateItem.equals(cateDBItem)) {
					return new BookSalesResponse(RtnCode.SAME_DATA.getMessage());
				}
			}
		}
		//更新分類
		op.get().setCategory(category);
		
		bookSalesDao.save(op.get());
		return new BookSalesResponse(forUpdate, RtnCode.SUCCESSFUL.getMessage());
		
		//陣列轉成String
//		String str1 = Arrays.toString(ary);
		//去除掉中括號
//		str1.substring(1, str1.length()-1);

	}

	//===方法四=======================
	//書籍銷售
	@Override
	public BookSalesResponse buyBookByIsbn(Map<String, Integer> buyBookMap) {
		//檢查: 輸入的String isbn 不能是null、不能是空、不能是全空白
		//     輸入的int quantity 不能是負數
		List<String> isbnList = new ArrayList<>();
		Map<String, Integer> finalBookMap = new HashMap();
		int priceTotal = 0;
		for (Entry<String, Integer> map : buyBookMap.entrySet()) {
			if (!StringUtils.hasText(map.getKey()) || map.getValue() < 0) {
				return new BookSalesResponse(RtnCode.DATA_ERROR.getMessage());
			}
			isbnList.add(map.getKey());
		}
		
		//確認資料庫是否存在isbnList的isbn
		List<BookSales> result = bookSalesDao.findAllById(isbnList);
		if (result.isEmpty()) {
			return new BookSalesResponse(RtnCode.NOT_FOUND.getMessage());
		}
		
		//如果都沒問題，
		for (BookSales resItem: result) {
			for (Entry<String, Integer> map : buyBookMap.entrySet()) {
				//確認資料庫的inventory(庫存)
				if (resItem.getInventory() < map.getValue()) {
					return new BookSalesResponse(RtnCode.SHORTAGE.getMessage());
				}
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
		return new BookSalesResponse(buyBook);
	}
	
	
}

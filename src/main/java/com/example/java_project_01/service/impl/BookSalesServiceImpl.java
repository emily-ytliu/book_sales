package com.example.java_project_01.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.java_project_01.constants.RtnCode;
import com.example.java_project_01.entity.BookSales;
import com.example.java_project_01.repository.BookSalesDao;
import com.example.java_project_01.service.ifs.BookSalesService;
import com.example.java_project_01.vo.BookSalesResponse;
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
		//      String: bookName, ISBN, author 不能是null、不能是空字串、不能是全空白
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
		String pattern = "\\d{13}";
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
			ShowForResult showForBasic = new ShowForResult(item.getBookName(), item.getIsbn(), item.getAuthor(), item.getPrice(), item.getInventory());
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
			ShowForResult showForCustomer = new ShowForResult(item.getBookName(), item.getIsbn(), item.getAuthor(), item.getPrice());
			forCustomer.add(showForCustomer);
		}
		//書商: 只顯示書名、ISBN、作者、價格、庫存量、銷售量
		List<ShowForResult> forSeller = new ArrayList<>();
		for (BookSales item : result) {
			ShowForResult showForSeller = new ShowForResult(item.getBookName(), item.getIsbn(), item.getAuthor(), item.getPrice(), item.getInventory(), item.getSales());
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
	public BookSalesResponse updateBookInfo(List<BookSales> bookSalesList) {
		//檢查: 輸入的List 不能是null、不能是空、不能是全空白
		if (!CollectionUtils.isEmpty(bookSalesList)) {
			return new BookSalesResponse(RtnCode.DATA_ERROR.getMessage());
		}
		//檢查: 輸入的List裡的每個項目
		//     String: bookName、isbn、author、category 不能是null、不能是空、不能是全空白
		//     int: price、inventory、sales 不能是負數
		for (BookSales item : bookSalesList) {
			if (!StringUtils.hasText(item.getBookName())
					|| !StringUtils.hasText(item.getIsbn())
					|| !StringUtils.hasText(item.getAuthor())
					|| item.getPrice() < 0
					|| item.getInventory() < 0
					|| item.getSales() < 0
					|| !StringUtils.hasText(item.getCategory())) {
				return new BookSalesResponse(RtnCode.DATA_ERROR.getMessage());
			}
		}
		//檢查: String category裡的每個分類 不能是null、不能是空、不能是全空白
		
		//用', '切割String category，把切好的category放入陣列
//		String ary[] = category.split(", ");  //ary是記憶體位址
		
		//陣列轉成String
//		String str1 = Arrays.toString(ary);
		//去除掉中括號
//		str1.substring(1, str1.length()-1);
				
		//把不同分類名稱加入到List
		//檢查: List裡的每個項目String 不能是null、空字串、全空白
//		List<String> cateList = new ArrayList<>();
//		for (String item : ary) {
//			if (!StringUtils.hasText(item)) {
//				continue;
//			}
//			cateList.add(item);
//		}
		//與資料庫所有的分類做比對，只要有任一符合就回傳資料
//		輸入不同的分類，java回傳資料庫只要有任一項符合輸入的分類就回傳這些資料
		return null;
	}
	
	
}

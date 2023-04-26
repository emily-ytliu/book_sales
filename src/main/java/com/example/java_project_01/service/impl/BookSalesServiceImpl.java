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

@Service
public class BookSalesServiceImpl implements BookSalesService{

	@Autowired
	private BookSalesDao bookSalesDao;;

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
				|| !CollectionUtils.isEmpty(bookSales.getCategoryList())) {
			return new BookSalesResponse(RtnCode.DATA_ERROR.getMessage());
		}
		//檢查3: ISBN格式
		String pattern = "[^\\s]\\d{10|13}";
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

	//分類搜尋
	@Override
	public BookSalesResponse findByCategoryList(List<String> categoryList) {
		//檢查: 輸入的List 不能是null、空陣列
		if (!CollectionUtils.isEmpty(categoryList)) {
			return new BookSalesResponse(RtnCode.DATA_ERROR.getMessage());
		}
		List<String> resCateList = new ArrayList<>();
		//檢查: 遍歷List的每一個項目 不能是null、空字串、全空白
		for (String item : categoryList) {
			if (!StringUtils.hasText(item)) {
				continue;
			}
			resCateList.add(item);
		}
		//確認: 輸入的分類是否存在於資料庫
//		bookSalesDao.findById(null);
		
		
		//檢查: 輸入的String 不能是null、空字串、全空白
		
		//把切好的category放入陣列
//		String ary[] = categoryList.split(", ");  //ary是記憶體位址
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
//		List<BookSales> result = bookSalesDao.findAllByItem(cateList);
		
		//確認資料庫有沒有此分類的書籍資料
//		if (result.isEmpty()) {
//			return new BookSalesResponse(RtnCode.NOT_FOUND.getMessage());
//		}
		//如果確定資料庫有此分類的書籍，回傳	
		return new BookSalesResponse(RtnCode.SUCCESSFUL.getMessage());
	}
	
	
	
}

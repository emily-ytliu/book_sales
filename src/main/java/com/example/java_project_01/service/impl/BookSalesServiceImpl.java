package com.example.java_project_01.service.impl;

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
				|| !StringUtils.hasText(bookSales.getISBN())
				|| !StringUtils.hasText(bookSales.getAuthor())
				|| bookSales.getPrice() < 0 
				|| bookSales.getInventory() < 0 
				|| bookSales.getSales() < 0
				|| !CollectionUtils.isEmpty(bookSales.getCategoryList())) {
			return new BookSalesResponse(RtnCode.DATA_ERROR.getMessage());
		}
		
//		if (!StringUtils.hasText(bookSales.getBookName())
//				|| !StringUtils.hasText(bookSales.getISBN())
//				|| !StringUtils.hasText(bookSales.getAuthor())) {
//			return new BookSalesResponse(RtnCode.DATA_ERROR.getMessage());
//		}
//		if (bookSales.getPrice() < 0 
//				|| bookSales.getInventory() < 0 
//				|| bookSales.getSales() < 0) {
//			return new BookSalesResponse(RtnCode.DATA_ERROR.getMessage());
//		}
//		if (!CollectionUtils.isEmpty(bookSales.getCategoryList())) {
//			return new BookSalesResponse(RtnCode.DATA_ERROR.getMessage());
//		}
		//檢查3: ISBN格式
		String pattern = "";
		if (!bookSales.getISBN().matches(pattern)) {
			return new BookSalesResponse(RtnCode.DATA_ERROR.getMessage());
		}
			
		//確認: 要新增的bookSales是否已經存在資料庫(不存在的才能新增)
		Optional<BookSales> op = bookSalesDao.findById(bookSales.getISBN());
		if (!op.isPresent()) {
			return new BookSalesResponse(RtnCode.ALREADY_EXIST.getMessage());
		}
		return new BookSalesResponse(bookSalesDao.save(bookSales), RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public BookSalesResponse findByCategory(List<String> categoryList) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}

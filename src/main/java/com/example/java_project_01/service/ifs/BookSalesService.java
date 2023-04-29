package com.example.java_project_01.service.ifs;

import java.util.List;
import java.util.Map;

import com.example.java_project_01.entity.BookSales;
import com.example.java_project_01.vo.BookSalesResponse;

public interface BookSalesService {

	//方法一
	//新增書籍資訊
	public BookSalesResponse addBookInfo(BookSales bookSales);
	
	//方法二
	//分類搜尋
	public BookSalesResponse searchByCategory(String category);
	
	//方法三
	//書籍搜尋
	public BookSalesResponse searchByKeyword(Boolean isCustomer, String keyword);
	
	//方法四
	//更新書籍資訊(價格、庫存、分類)
	public BookSalesResponse updateBookInfo(String isbn, int price, int inventory, String category);
	
	//方法五
	//書籍銷售<ISBN, 購買數量>
	public BookSalesResponse buyBookByIsbn(Map<String, Integer> buyBookMap);
}

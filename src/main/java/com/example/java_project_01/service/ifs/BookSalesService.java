package com.example.java_project_01.service.ifs;

import java.util.List;

import com.example.java_project_01.entity.BookSales;
import com.example.java_project_01.vo.BookSalesResponse;

public interface BookSalesService {

	//新增書籍資訊
	public BookSalesResponse addBookInfo(BookSales bookSales);
	
	//分類搜尋
	public BookSalesResponse findByCategory(List<String> categoryList);
}

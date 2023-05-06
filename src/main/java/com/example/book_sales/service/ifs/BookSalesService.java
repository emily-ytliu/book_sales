package com.example.book_sales.service.ifs;

import java.util.List;
import java.util.Map;

import com.example.book_sales.entity.BookSales;
import com.example.book_sales.vo.BookSalesResponse;
import com.example.book_sales.vo.ShowForBuyingBookResponse;
import com.example.book_sales.vo.ShowForResultResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

public interface BookSalesService {

	//方法一
	//新增書籍資訊
	public BookSalesResponse addBookInfo(BookSales bookSales);
	
	//方法二
	//分類搜尋
	public BookSalesResponse searchByCategory(String category);
	
	//方法三
	//書籍搜尋
	public ShowForResultResponse searchByKeyword(boolean isSeller, String keyword);
	
	//方法四
	//更新書籍資訊(用ISBN，更新: 價格、庫存、分類)
	public ShowForResultResponse updateBookInfo(String isbn, int price, int inventory, String category);
	
	//方法四-1
	//只更新價格<ISBN, 價格>
	public ShowForResultResponse updateBookPrice(Map<String, Integer> updatePriceMap);
	
	//方法四-2
	//只更新庫存<ISBN, 庫存量>
	public ShowForResultResponse updateBookInventory(Map<String, Integer> updateInventoryMap);
	
	//方法四-3
	//只更新分類<ISBN, 分類>
	public ShowForResultResponse updateBookCategory(Map<String, String> updateCategoryMap);
	
	//方法五
	//書籍銷售<ISBN, 購買數量>
	public ShowForBuyingBookResponse buyBookByIsbn(Map<String, Integer> buyBookMap);
	
	//方法六
	//暢銷排行: 銷售量前5
	public ShowForResultResponse getBestSellerTop5();
}

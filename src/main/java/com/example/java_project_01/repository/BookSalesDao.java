package com.example.java_project_01.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.java_project_01.entity.BookSales;

@Repository
public interface BookSalesDao extends JpaRepository<BookSales, String>{
	
	public List<BookSales> findByCategoryContaining(String category);
	
	//===JPQL===========================
	//用書名、ISBN、作者搜尋
	@Query("SELECT b FROM BookSales b "
			+ "WHERE b.bookName LIKE %:keyword% OR b.isbn LIKE %:keyword% OR b.author LIKE %:keyword%")
	public List<BookSales> findByKeyword(@Param("keyword") String keyword);
	
//	@Query(VALUE = "SELECT NEW BookSales(b.bookName, b.isbn, b.author, b.price, b.inventory)")
//	public List<BookSales> showForBuyingBook(@Param(""))
	

	

//	@Query("select BookSales b where b.category in :newCategory")
	
//	//UPDATE才需要加: 
//	  @Transactional
//	  @Modifying
	
//	//用書名、ISBN、作者搜尋
//	//消費者: 只顯示書名、ISBN、作者、價格
//	@Query("select b.bookName, b.isbn, b.author, b.price from BookSales b "
//			+ "where b.bookName like %:keyword% or b.isbn like %:keyword% or b.author like %:keyword%")
//	public List<Object[]> showForCustomer(@Param("keyword") String keyword);
//	
//	//書商: 只顯示書名、ISBN、作者、價格
//	@Query("select b.bookName, b.isbn, b.author, b.price, b.sales, b.inventory from BookSales b "
//			+ "where b.bookName like %:keyword% or b.isbn like %:keyword% or b.author like %:keyword%")
//	public List<Object[]> showForSeller(@Param("keyword") String keyword);
}

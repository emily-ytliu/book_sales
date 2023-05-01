package com.example.java_project_01.repository;

import java.awt.print.Pageable;
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
	
	//===JPQL=============================
	//用書名、ISBN、作者搜尋
	@Query("SELECT b FROM BookSales b "
			+ "WHERE b.bookName LIKE %:keyword% OR b.isbn LIKE %:keyword% OR b.author LIKE %:keyword%")
	public List<BookSales> findByKeyword(@Param("keyword") String keyword);
	
	//只顯示書名、ISBN、作者、價格、庫存量、分類這6個欄位
	@Query("SELECT NEW BookSales(b.bookName, b.isbn, b.author, b.price, b.inventory, b.category) FROM BookSales b")
	public List<BookSales> findByIsbnForSearching(@Param("isbn") String isbn);
	
	//只顯示書名、ISBN、作者、價格這4個欄位
	@Query("SELECT NEW BookSales(b.bookName, b.isbn, b.author, b.price) FROM BookSales b")
	public List<BookSales> findByBookNameAndIsbnAndAuthorAndPrice();
	
	//===SQL=============================
	//用銷售量由大到小排序，並限制顯示的資料筆數
	@Query(value = "SELECT * FROM book_sales b "
			+ "ORDER BY b.sales DESC LIMIT :limitNum", nativeQuery = true)  //nativeQuery = true表示直接對DB操作，等於用原生SQL查詢
	public List<BookSales> findTopLimitNumOrderBySalesDesc(@Param("limitNum") int limitNum);
	//因為JPQL不支援LIMIT，所以用原生SQL
	
	
	
	
	
//	@Query(value = "SELECT NEW BookSales(b.bookName, b.isbn, b.author, b.price) FROM BookSales b limit :limitNum", 
//	nativeQuery = true)  //nativeQuery = true表示直接對DB做操作
//			+ "WHERE b.bookName = :inputBookName AND b.isbn = :inputIsbn AND b.author = :inputAuthor AND b.price = :inputPrice")
//	public List<BookSales> findByBookNameAndISBNAndAuthorAndPrice(@Param("inputBookName") String bookName,
//																  @Param("inputIsbn") String isbn,
//																  @Param("inputAuthor") String author,
//																  @Param("inputPrice") int price);
	
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

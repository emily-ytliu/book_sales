package com.example.java_project_01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_project_01.entity.BookSales;

@Repository
public interface BookSalesDao extends JpaRepository<BookSales, String>{

//	public List<BookSales> findAllByItem(List<String> categoryList);
	
	public List<BookSales> findByCategoryListContaining(List<String> categoryList);
}

package dev.pc.dao;

import java.util.List;

import dev.pc.entity.Category;

public interface ICategoryDao {
	void insert(Category category);
	
	void update(Category category);
	
	void delete(int cateId);
	
	List<Category> findAll();
	
	Category findById(int cateId);
	
	List<Category> findByName(String cateName);
}

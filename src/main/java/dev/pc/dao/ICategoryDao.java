package dev.pc.dao;

import java.util.List;

import dev.pc.entity.Category;

public interface ICategoryDao {
	void create(Category category);

	void update(Category category);

	void delete(String cateId);

	List<Category> findAll();

	Category findById(String cateId);

	List<Category> findByCategoryName(String cateName) throws Exception;
}

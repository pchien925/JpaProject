package dev.pc.service;

import java.util.List;

import dev.pc.entity.Category;

public interface ICategoryService {
	void create(Category category);

	void update(Category category);

	void delete(String cateId);

	List<Category> findAll();

	Category findById(String cateId);

	List<Category> findByCategoryName(String cateName) throws Exception;
}

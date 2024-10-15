package dev.pc.service.impl;

import java.util.List;

import dev.pc.dao.ICategoryDao;
import dev.pc.dao.impl.CategoryDaoImpl;
import dev.pc.entity.Category;
import dev.pc.service.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {

	ICategoryDao categoryDao = new CategoryDaoImpl();

	@Override
	public void insert(Category category) {
		categoryDao.insert(category);
	}

	@Override
	public void update(Category category) {
		categoryDao.update(category);
	}

	@Override
	public void delete(int cateId) {
		categoryDao.delete(cateId);
	}

	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public Category findById(int cateId) {
		return categoryDao.findById(cateId);
	}

	@Override
	public List<Category> findByName(String cateName) {
		return categoryDao.findByName(cateName);
	}

}

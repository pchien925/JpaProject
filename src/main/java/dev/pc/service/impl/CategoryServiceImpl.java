package dev.pc.service.impl;

import java.util.List;

import dev.pc.dao.ICategoryDao;
import dev.pc.dao.impl.CategoryDaoImpl;
import dev.pc.entity.Category;
import dev.pc.service.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {

	ICategoryDao categoryDao = new CategoryDaoImpl();

	@Override
	public void create(Category category) {
		categoryDao.create(category);
	}

	@Override
	public void update(Category category) {
		categoryDao.update(category);
	}

	@Override
	public void delete(String cateId) {
		categoryDao.delete(cateId);
	}

	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public Category findById(String cateId) {
		return categoryDao.findById(cateId);
	}

	@Override
	public List<Category> findByCategoryName(String cateName) throws Exception {
		return categoryDao.findByCategoryName(cateName);
	}

}

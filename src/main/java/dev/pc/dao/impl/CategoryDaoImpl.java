package dev.pc.dao.impl;

import java.util.List;
import dev.pc.configs.JPAConfig;
import dev.pc.dao.ICategoryDao;
import dev.pc.entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class CategoryDaoImpl implements ICategoryDao {

	@Override
	public void create(Category category) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			enma.persist(category);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void update(Category category) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			enma.merge(category);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void delete(String cateId) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			enma.remove(enma.find(Category.class, cateId));
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public List<Category> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);

		return query.getResultList();
	}

	@Override
	public Category findById(String cateId) {
		EntityManager enma = JPAConfig.getEntityManager();
		return enma.find(Category.class, cateId);
	}

	@Override
	public List<Category> findByCategoryName(String cateName) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "select u from Category u where u.categoryName LIKE :cateName";
		TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
		query.setParameter("cateName", "%" + cateName + "%");
		try {
			return query.getResultList();

		} catch (Exception e) {
			throw new Exception("Category not exitsted");
		}
	}

}

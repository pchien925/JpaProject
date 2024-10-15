package dev.pc.dao.impl;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import dev.pc.configs.JPAConfig;
import dev.pc.dao.IUserDao;
import dev.pc.dto.request.RegisterRequest;
import dev.pc.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class UserDaoImpl implements IUserDao {

	@Override
	public void create(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		try {
			trans.begin();
			enma.persist(user);
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
	public void update(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			enma.merge(user);
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
	public void delete(String id) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			User user = enma.find(User.class, id);
			if (user == null) {
				throw new Exception("User not existed");
			}

			trans.begin();
			enma.remove(user);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			enma.close();
		}
	}

	@Override
	public List<User> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<User> query = enma.createNamedQuery("User.findAll", User.class);

		return query.getResultList();
	}

	@Override
	public User userRegitered(RegisterRequest request) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		User user = User.builder().username(request.getUsername())
				.password(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt())).email(request.getEmail())
				.active(request.isActive()).code(request.getCode()).phone(request.getPhone())
				.fullname(request.getFullname()).images(request.getImages()).role(request.getRole()).build();
		try {
			trans.begin();
			enma.persist(user);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
		return user;
	}

	@Override
	public User findByUsername(String username) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "select u from User u where u.username = :username";
		TypedQuery<User> query = enma.createQuery(jpql, User.class);
		query.setParameter("username", username);
		try {
			User user = query.getSingleResult();
			return user;
		} catch (Exception e) {
			throw new Exception("User not exitsted");
		}
	}

	@Override
	public User findById(String id) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		User user = enma.find(User.class, id);
		if (user == null)
			throw new Exception("User not exitsted");
		return user;
	}

	@Override
	public User findByEmail(String email) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "select u from User u where u.email = :email";
		TypedQuery<User> query = enma.createQuery(jpql, User.class);
		query.setParameter("email", email);
		try {
			User user = query.getSingleResult();
			return user;
		} catch (Exception e) {
			throw new Exception("Email not exitsted");
		}
	}

	@Override
	public User findByPhone(String phone) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "select u from User u where u.phone = :phone";
		TypedQuery<User> query = enma.createQuery(jpql, User.class);
		query.setParameter("phone", phone);
		try {
			User user = query.getSingleResult();
			return user;
		} catch (Exception e) {
			throw new Exception("Phone not exitsted");
		}
	}

}

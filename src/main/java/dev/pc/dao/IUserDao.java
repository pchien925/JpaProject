package dev.pc.dao;

import java.util.List;

import dev.pc.dto.request.RegisterRequest;
import dev.pc.entity.User;

public interface IUserDao {
	void create(User user);

	void update(User user);

	void delete(String id);

	List<User> findAll();
	
	User userRegitered(RegisterRequest request);

	User findByUsername(String username) throws Exception;

	User findById(String id) throws Exception;

	User findByEmail(String email) throws Exception;

	User findByPhone(String phone) throws Exception;
}

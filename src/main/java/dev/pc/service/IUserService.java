package dev.pc.service;

import java.util.List;

import dev.pc.dto.request.LoginRequest;
import dev.pc.dto.request.RegisterRequest;
import dev.pc.entity.User;

public interface IUserService {
	void create(User user);

	void update(User user);

	void delete(String id);

	List<User> findAll();

	User findByUsername(String username) throws Exception;

	User findById(String id) throws Exception;

	boolean checkExistEmail(String email) throws Exception;

	boolean checkExistUsername(String username) throws Exception;

	boolean checkExistPhone(String phone) throws Exception;

	User Login(LoginRequest request) throws Exception;

	User Register(RegisterRequest request) throws Exception;
}

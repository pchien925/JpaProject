package dev.pc.service.impl;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import dev.pc.dao.IUserDao;
import dev.pc.dao.impl.UserDaoImpl;
import dev.pc.dto.request.LoginRequest;
import dev.pc.dto.request.RegisterRequest;
import dev.pc.entity.User;
import dev.pc.service.IUserService;

public class UserServiceImpl implements IUserService {

	IUserDao userDao = new UserDaoImpl();

	@Override
	public void create(User user) {
		userDao.create(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void delete(String id) {
		userDao.delete(id);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User findByUsername(String username) throws Exception {
		return userDao.findByUsername(username);
	}

	@Override
	public User findById(String id) throws Exception {
		return userDao.findById(id);
	}

	@Override
	public boolean checkExistEmail(String email) throws Exception {
		if (userDao.findByEmail(email) != null)
			return false;
		return true;
	}

	@Override
	public boolean checkExistUsername(String username) throws Exception {

		if (userDao.findByUsername(username) != null)
			return false;
		return true;
	}

	@Override
	public boolean checkExistPhone(String phone) throws Exception {
		if (userDao.findByPhone(phone) != null)
			return false;
		return true;
	}

	@Override
	public User Login(LoginRequest request) throws Exception {

		User user = userDao.findByUsername(request.getUsername());
		if (BCrypt.checkpw(request.getPassword(), user.getPassword()))
			return user;
		else
			throw new Exception("login failed");
	}

	@Override
	public User Register(RegisterRequest request) throws Exception {
		if (userDao.findByUsername(request.getUsername()) != null || userDao.findByPhone(request.getPhone()) != null
				|| userDao.findByEmail(request.getEmail()) != null) {
			throw new Exception("User exitsted");
		}
		return userDao.userRegitered(request);
	}

}

package com.revature.services;

import java.util.List;

import com.revature.dao.UserDao;
import com.revature.model.Type;
import com.revature.model.User;

public class UserService {
	private UserDao userDao;
	public UserService() {
		userDao = new UserDao();
	}

	public User login(String username, String password) {
		User u = userDao.login(username,password);
		return u;
	}

	public List<User> getAll() {
		return userDao.findAll();
		}	

	
}

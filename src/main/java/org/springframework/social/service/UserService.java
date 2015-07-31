package org.springframework.social.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.dao.UserDAO;
import org.springframework.social.model.User;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
	@Autowired
	UserDAO UserDAO;
	
	public List<User> getAllUser() {
		return this.UserDAO.getAllUser();
	}
	
	public boolean createUser(User User) {
		return this.UserDAO.createUser(User);
	}
	
	public boolean updateUser(User User)
	{
		return this.UserDAO.updateUser(User);
	}
	public boolean deleteUserId(int id) {
		return this.UserDAO.deleteUserId(id);
	}
	
	public User getUserById(int id) {
		return this.UserDAO.getUserById(id);
	}
	
	public boolean isExistUserId(String userId) {
		return this.UserDAO.isExistUserId(userId);
	}
	
	public User getUserByUserId(String userId) {
		return this.UserDAO.getUserByUserId(userId);
	}
	
	public boolean isExistUsename(String username){
		return this.UserDAO.isExistUsename(username);
	}
	
	public boolean updateUserLogin(User user) {
		return this.UserDAO.updateUserLogin(user);
	}
	
	public boolean isExistUsenameAndPassword(String username, String password) {
		return this.UserDAO.isExistUsenameAndPassword(username, password);
	}
	
	public boolean isExistUsenameAndPasswordUserId(String username, String password, String userId) {
		return this.UserDAO.isExistUsenameAndPasswordUserId(username, password, userId);
	}
	public boolean isExistUsenameWhenEdit(String username) {
		return this.UserDAO.isExistUsenameWhenEdit(username);
	}
}

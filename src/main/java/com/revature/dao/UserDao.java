package com.revature.dao;

import java.util.List;

import com.revature.models.User;

public interface UserDao {
	
	public void addUser(User user, String first, String last);
	
	public User getUser(int id);
	
	public User getUser(String username);
	
	public List<User> getAllUsers();
	
	public boolean verifyUser(String username, String password);
	
	public void updateUser(User user);
	
	public void deleteUser(int id);
	
	public void deleteAllUsers();
}

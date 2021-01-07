package com.revature.services;

import com.revature.dao.UserDao;
import com.revature.dao.UserHandler;
import com.revature.models.User;

import jdk.internal.org.jline.utils.Log;

public class UserService {
	
	public static User masterUser = new User(10,"Testing", "Employee", "testing", "testing123","testtest@gmail.com","12345");			//For testing purposes
	static UserDao uDao = new UserHandler();
		
	public static boolean verifyUser(String username, String password) {
		return uDao.verifyUser(username, password);
	}
	
	public static String getNameFromId(int id) {
		 return uDao.getUser(id).getName();
	}
	
	public static User getUser(int id) {
		return uDao.getUser(id);
	}
	
	public static User getUser(String username) {
		return uDao.getUser(username);
	}
	
	public static void saveUser(User user, String first, String last) {
		uDao.addUser(user, first, last);
	}
	
	public static void deleteUser(int id) {
		Log.info("Deleting user with id: " + id);
		uDao.deleteUser(id);
	}

	public static void deleteAllUsers() {
		// TODO Auto-generated method stub
		
	}
	
}

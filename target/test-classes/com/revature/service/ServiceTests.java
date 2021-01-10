package com.revature.service;

import static org.junit.Assert.*;

import org.junit.Test;

import org.mockito.*;
import org.mockito.junit.*;

import com.revature.models.User;
import com.revature.models.Ticket;

public class ServiceTests{

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	
	static UserDao uDao = new UserHandler(); 
	static TicketsDao tDao = new TicketsHandler(); 
	
	@Mock User user;
	@Mock User user2;

	@Before
	public void generateUsers() {
		user = new User("User1", "Employee", "employee1", "pass123", "newemail@mail.com");
		uDao.addUser(user, "User", "1");
		
		user2 = new User("User2", "Manager", "manager1", "pass123", "newmanager@gmail.com");
		uDao.addUser(user2, "User", "2");
	}
	
	@Test
	public void verifyUserExists() {
		
	}
	
	//Resets the data
	@After
	public void clearData() {
		for(User i: users) {
			uDao.remove_user(i);
		}
	}
}

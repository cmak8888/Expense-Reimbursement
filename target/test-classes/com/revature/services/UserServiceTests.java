package com.revature.services;

import static org.junit.Assert.*;

import org.junit.Test;

import org.mockito.*;
import org.mockito.junit.*;

import com.revature.models.User;
import com.revature.models.Ticket;
import com.revature.services.UserService;

public class ServiceTests{

	@InjectMocks
	UserService uService = new UserService(); 
	@InjectMocks
	User user = new User("Bob", "Employee", "bob123", "bobpass", "bobbybobob@mail.com"); 
	
	@Mock
	UserDao uDao= new UserHandler(); 
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetUserByLogin() {
		when(uDao.getUserByLogin("bob123", "bobpass")).thenReturn(user); 
		assertNotNull(uDaoImpl.getUserByLogin("bob123", "bobpass")); 
		verify(uDao).getUserByLogin("bob123", "bobpass"); 
		
	}
	
	@Test
	public void testPostNewUser() {
		when(uDao.addUser(user)).thenReturn(user); 
		assertEquals(user, uDao.addUser(user)); 
		verify(uDao).addUser(user); 
		
	}
}

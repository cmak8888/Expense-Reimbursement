package com.revature.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.models.User;

public class TicketService {

	@InjectMocks
	TicketService tService = new TicketService();
	
	@InjectMocks
	User user = new User(1,"Bob", "Employee", "bob123", "bobpass", "bobbybobob@mail.com")
	
	@InjectMocks
	Ticket ticket = new Ticket(1, "New Check", "anothercheck", 3, 200000.00); 
	
	@Mock
	TicketsDao tDao = new TicketsHandler();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}

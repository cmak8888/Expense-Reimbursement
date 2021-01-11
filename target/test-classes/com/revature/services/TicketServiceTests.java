package com.revature.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.dao.TicketsDao;
import com.revature.models.User;

public class TicketService {

	@InjectMocks
	TicketService tService = new TicketService();
	
	@InjectMocks
	User user = new User(1,"John Smith", "Employee", "bob123", "bobpass", "bobbybobob@mail.com")
	
	@InjectMocks
	Ticket ticket = new Ticket(1, user, "New Check", "anothercheck", 3, 200000.00, true, ""); 
	
	@Mock
	TicketsDao tDao = new TicketsHandler();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void insertTicket() {
		when(tDao.createTicket(ticket)).thenReturn(ticket); 
		assertEquals(ticket, tService.createTicket(ticket)); 
		verify(tDao).createTicket(ticket); 
	}

	@Test
	public void getTicket() {
		List<Ticket> ticketList = new ArrayList<>(); 
		ticketList.add(ticket); 
		when(tDao.getTicket(1)).thenReturn(ticketList); 	
		assertEquals(1, tService.getTicket(1).size()); 
		verify(tDao).getTicket(3); 
	}
	
	@Test
	public void getAllTickets() {
		List<Ticket> tickets = new ArrayList<Ticket>();
		Ticket ticket2 = new Ticket(1, user, "New Check", "anothercheck", 3, 200000.00, true, ""); 
		tickets.add(ticket);
		tickets.add(ticket2);
		when(tDao.getAllTickets()).thenReturn(tickets);
		assertEquals(2,tService.getAllTickets().size());
		verify(tDao).getAllTickets();
	}
}

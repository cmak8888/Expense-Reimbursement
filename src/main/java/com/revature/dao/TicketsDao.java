package com.revature.dao;

import java.util.List;

import com.revature.models.Ticket;
import com.revature.models.TicketType;
import com.revature.models.User;

public interface TicketsDao {
	public Ticket getTicket(int id);
	
	public List<Ticket> getTickets(boolean approved);
	
	public List<Ticket> getTickets(User user);
	
	public List<Ticket> getTickets(int id);
	
	public List<Ticket> getTickets(TicketType tType);
	
	public List<Ticket> getTickets(User user, boolean approved);
	
	public List<Ticket> getTickets(User user, TicketType tType);
	
	public List<Ticket> getAllTickets();
	
	public List<Ticket> getAllTickets(String term, OrderDirection order);
	
	public User getTicketUser(int id);
	
	public void approveTicket(int id);
	
	public void rejectTicket(int id);
	
	public void createTicket(Ticket ticket);
	
	public void updateTicket(int id);
	
	public void removeTicket(int id);
	
	public void removeAllTickets();
	
	public void removeAllTickets(int id);
	
	public void removeAllRejectedTickets();
}

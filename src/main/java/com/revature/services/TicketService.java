package com.revature.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.controllers.LoginController;
import com.revature.dao.TicketsDao;
import com.revature.dao.TicketsHandler;
import com.revature.models.Ticket;
import com.revature.models.TicketType;
import com.revature.models.User;

public class TicketService {
    public final static Logger log = Logger.getLogger(TicketService.class);
	static TicketsDao tDao = new TicketsHandler();
	
	public static void submitTicket(Ticket ticket) {
		tDao.createTicket(ticket);
	}
	
	public static Ticket getTicket(int id) {
		return tDao.getTicket(id);
	}
	
	public static User getUserWithTicketId(int id) {
		return tDao.getTicketUser(id);
	}
	
	public static List<Ticket> getTicketByUser(int id) {
		return tDao.getTickets(id);
	}
	
	public static List<Ticket> getTicketByType(TicketType type) {
		return tDao.getTickets(type);
	}
	
	public static List<Ticket> getTicketApproved(boolean approved) {
		return tDao.getTickets(approved);
	}
	

	
	public static List<Ticket> getAllTickets() {
		return tDao.getAllTickets();
	}
	
	public static void acceptTicket(int id) {
		tDao.approveTicket(id);
	}
	
	public static void rejectTicket(int id) {
		tDao.rejectTicket(id);
	}
	
	public static void deleteTicket(int id) {
		Log.info("Deleting Ticket");
		tDao.removeTicket(id);
	}
	
	public static void deleteAllTickets() {
		
	}
	
	public static void deleteAllRejectedTickets() {
		
	}
	
	public static void deleteAllTicketsForUser(int id) {
		
	}
	
	public static void deleteAllTicketsForUser(User user) {
		
	}
}

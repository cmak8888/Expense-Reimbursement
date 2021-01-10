package com.revature.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.mail.MailException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.Exception405;
import com.revature.models.Ticket;
import com.revature.models.User;
import com.revature.services.JSONService;
import com.revature.services.MailService;
import com.revature.services.TicketService;
import com.revature.services.UserService;

public class DashboardController {

    public final static Logger log = Logger.getLogger(DashboardController.class);
	//Pages
	//Navigation to Get
	public static void getNewUserPage(HttpServletRequest req, HttpServletResponse resp) {
		
	}
	
	public static void getDashboardPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {JSONObject username = new JSONObject();
		HttpSession sesh = req.getSession();
		if(sesh.getAttribute("User") == null) {
			resp.sendRedirect("http://localhost:8080/ExpReimburse/expr");
			return;
		}
		log.info(((User) sesh.getAttribute("User")).getUsername().toString());
		username.append("username", (String)(((User) sesh.getAttribute("User")).getUsername()));
		resp.getWriter().write(username.toString());
		if(((User) sesh.getAttribute("User")).getUserType().equals("Employee")) {
			log.info("Am a Employee");
			RequestDispatcher redis = req.getRequestDispatcher("/EmployeeDashboard.html");
			redis.forward(req,resp);
			return;
		} else if(((User) sesh.getAttribute("User")).getUserType().equals("Manager")) {
			log.info("Am a Manager");
			RequestDispatcher redis = req.getRequestDispatcher("/ManagerDashboard.html");
			redis.forward(req,resp);	
			return;		
		} else {
			log.warn("Invalid user type");
			resp.sendRedirect("http://localhost:8080/ExpReimburse/expr/404");
			resp.setStatus(404);
			return;
		}
		
	}
	
	
	public static void viewTicketPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sesh = req.getSession();
		if(sesh.getAttribute("User") == null) {
			resp.sendRedirect("http://localhost:8080/ExpReimburse/expr");
			return;
		}
		RequestDispatcher redis = req.getRequestDispatcher("/ShowTicket.html");
		redis.forward(req, resp);
	}
	
	public static void viewTicketsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher redis = req.getRequestDispatcher("/ViewTickets.html");
		redis.forward(req, resp);
	
	}
	
	public static void submitTicketPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("Make Ticket");
		RequestDispatcher redis = req.getRequestDispatcher("/TicketForm.html");
		redis.forward(req, resp);
		return;
	}
	
	//Views specific ticket
	public static void getTicket(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException, Exception405 {
		if(req.getSession(false) != null) {
			if(req.getMethod().equals("GET")) {
				HttpSession sesh = req.getSession();
				JSONObject jTicket = new JSONObject(); 
				Ticket ticket = null; 
				resp.setContentType("application/json"); 
				ticket = TicketService.getTicket((int)sesh.getAttribute("ticketid"));
				jTicket = new JSONObject(ticket);
				
				resp.getWriter().write(jTicket.toString());
				
				
			} else {
				throw new Exception405(resp, "Could not find ticket with id");
			}
		} else {
			resp.setStatus(401);
			log.warn("User does not have authorization");
		}
	}
	
	public static void getTickets(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession sesh = req.getSession(false);
		if((sesh == null)) {
			resp.sendRedirect("http://localhost:8080/ExpReimburse/expr");
			return;
		} else if(((User) sesh.getAttribute("User")).getUserType() == "Employee"){
			List<Ticket> tickets = new ArrayList<Ticket>();
			tickets = TicketService.getTicketByUser(((User) sesh.getAttribute("User")).getId());
			
			JSONObject jTickets = new JSONObject();
			jTickets = JSONService.ticketListToObject(tickets);
			
			resp.setContentType("application/json");
			resp.getWriter().write(jTickets.toString());
		} else if(((User) sesh.getAttribute("User")).getUserType() == "Manager") {
			List<Ticket> tickets = new ArrayList<Ticket>();
			tickets = TicketService.getAllTickets();
			
			JSONObject jTickets = new JSONObject();
			jTickets = JSONService.ticketListToObject(tickets);
			
			resp.setContentType("application/json");
			resp.getWriter().write(jTickets.toString());
		}
	}
	
	public static void getUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, Exception405 {
		log.info("Inside getUser()");
		if(req.getSession(false) != null) {
			log.info("getUser session");
			HttpSession sesh = req.getSession();
			if(sesh.getAttribute("User") != null) {
				JSONObject userObject = new JSONObject();
				User user = (User) sesh.getAttribute("User");
				log.info(user.toString());
				userObject.put("name", user.getName());
				resp.setContentType("application/json");
				resp.getWriter().write(userObject.toString());
			} else {
				resp.setStatus(403);
				throw new Exception405(resp, "Cannot get User");
			}
		} else {
			resp.setStatus(403);
			throw new Exception405(resp, "Cannot get User Session");
		}
	}
	
	//Post/Forms
	public static void postUser(HttpServletRequest req, HttpServletResponse resp) {
		User user = new User(req.getParameter("firstname") + " " + req.getParameter("lastname"), req.getParameter("user_type"), req.getParameter("username"), req.getParameter("email"));
		UserService.saveUser(user, req.getParameter("firstname"), req.getParameter("lastname"));
		postEmail(user, user.getUsername(), user.getPassword());
		//Send email
	}
	
	public static void postTicket(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession sesh = req.getSession(false);
		resp.setContentType("application/json");
		if(sesh != null) {
			User user = (User)sesh.getAttribute("User");
			ObjectMapper om = new ObjectMapper(); 
			Ticket ticket = om.readValue(req.getReader(), Ticket.class); 
			//Ticket ticket = new Ticket(req.getParameter("title"), user, Integer.parseInt(req.getParameter("tickettype")),  req.getParameter("description")/*, req.getParameter("image")*/);
			ticket.setUser(user);
			TicketService.submitTicket(ticket);
			
			RequestDispatcher redis = req.getRequestDispatcher("/ticketComplete.html");
			redis.forward(req, resp);
		} else {
			resp.sendRedirect("http://localhost:8080/ExpReimburse/expr");
		}
	}
	
	public static void postEmail(User user, String msg) {
		try {
			MailService.sendEmail(user, msg);
		} catch(MailException e) {
			log.warn(e.toString());
		}
	}
	
	public static void postEmail(User user, String username, String password) {
		try {
			MailService.sendEmail(user, username, password);
		} catch(MailException e) {
			log.warn(e.toString());
		}
	}
	
	public static void postEmail(User user) {
		try {
			MailService.sendEmail(user);
		} catch(MailException e) {
			log.warn(e.toString());
		}
	}
	
	public static void postEmail(HttpServletRequest req, HttpServletResponse resp) {
		
	}
	
	public static void sendEmailWithAttachment(User user, String msg) throws MessagingException {
		try {
			MailService.sendEmail(user, msg);
		} catch(MailException e) {
			log.warn(e.toString());
		}
	}
	
	public static void sendEmailWithAttachment(User user) throws MessagingException {
		try {
			MailService.sendEmail(user);
		} catch(MailException e) {
			log.warn(e.toString());
		}
	}
	
	public static void sendEmailWithAttachment(HttpServletRequest req, HttpServletResponse resp) {
		
	}
	
	//Puts
	public static void acceptTicket(HttpServletRequest req, HttpServletResponse resp) {
		TicketService.acceptTicket(Integer.parseInt(req.getParameter("id")));
		postEmail(TicketService.getUserWithTicketId(Integer.parseInt(req.getParameter("id"))), "Your ticket " + req.getParameter("id") + " has been approved.");
	}
	
	public static void rejectTicket(HttpServletRequest req, HttpServletResponse resp) {
		TicketService.rejectTicket(Integer.parseInt(req.getParameter("id")));
		postEmail(TicketService.getUserWithTicketId(Integer.parseInt(req.getParameter("id"))), "Your ticket " + req.getParameter("id") + " has been rejected.");
	}
	
	//Delete
	
	public static void removeUser(HttpServletRequest req, HttpServletResponse resp) {
		UserService.deleteUser(Integer.parseInt(req.getParameter("id")));
	}
	
	public static void removeTicket(HttpServletRequest req, HttpServletResponse resp) {
		TicketService.deleteTicket(Integer.parseInt(req.getParameter("id")));
	}
	
	public static void removeAllUsers(HttpServletRequest req, HttpServletResponse resp) {
		UserService.deleteAllUsers();
	}
	
	public static void removeAllTickets(HttpServletRequest req, HttpServletResponse resp) {
		TicketService.deleteAllTickets();
	}
}

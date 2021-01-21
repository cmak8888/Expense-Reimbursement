package com.revature.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.exceptions.Exception403;
import com.revature.exceptions.Exception404;
import com.revature.exceptions.Exception405;


public class RequestHelper {
    public final static Logger log = Logger.getLogger(RequestHelper.class);
    
	public static void processGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, Exception405, Exception403, Exception404 {
		String endpoint = req.getRequestURI();
		log.info("In Get" + endpoint);
		switch(endpoint) {
//		case "/ExpReimburse/":
//			resp.sendRedirect("http://localhost:8080/ExpReimburse/expr");
//			break;
		case "/ExpReimburse/expr":
			LoginController.getHomePage(req, resp);
			break;
		case "/ExpReimburse/expr/home":
			LoginController.getHomePage(req, resp);
			break;
		case "/ExpReimburse/expr/dashboard":
			DashboardController.getDashboardPage(req, resp);
			break;
		case "/ExpReimburse/expr/CreateTicket":
			DashboardController.submitTicketPage(req, resp);
			break;
		case "/ExpReimburse/expr/ViewTicket":
			DashboardController.viewTicketPage(req, resp);
			break;
		case "/ExpReimburse/expr/ViewTickets":
			DashboardController.viewTicketsPage(req, resp);
			break;
		case "/ExpReimburse/expr/TicketComplete":
			RequestDispatcher redis = req.getRequestDispatcher("/ticketComplete.html");
			redis.forward(req,resp);
			break;
		case "/ExpReimburse/expr/login":
			LoginController.getLoginPage(req, resp);
			break;
		case "/ExpReimburse/expr/logout":
			LogoutController.getLogOutPage(req, resp);
			break;
		case "/ExpReimburse/expr/api/logout":
			LogoutController.logout(req, resp);
			break;
		case "/ExpReimburse/expr/api/viewTicket":
			DashboardController.getTicket(req, resp);
			break;
		case "/ExpReimburse/expr/api/viewTickets":
			DashboardController.getTickets(req, resp);
			break;
		case "/ExpReimburse/expr/api/getUser":
			DashboardController.getUser(req, resp);
			break;
		case "/ExpReimburse/expr/api/getUserType":
			DashboardController.getUserType(req, resp);
			break;
		case "/ExpReimburse/expr/api/endPoints":
			SpecialController.getEndpoints(req,resp);
			break;
		default:
			resp.setStatus(404);
			resp.sendRedirect("http://localhost:8080/ExpReimburse/expr/home");			
		}
	}
	
	public static void processPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/JSON");
		String endpoint = req.getRequestURI();
		log.info("In Post" + endpoint);
		switch(endpoint) {
		case "/ExpReimburse/expr/api/login":
			LoginController.login(req, resp);
			break;
		case "/ExpReimburse/expr/api/newuser":
			DashboardController.postUser(req, resp);
			break;
		case "/ExpReimburse/expr/api/postTicket":
			DashboardController.postTicket(req,resp);
			break;
		case "/ExpReimburse/expr/api/sendEmail":
			DashboardController.postEmail(req,resp);
			break;
		case "ExpReimburse/expr/api/sendEmailWithAttachment":
			DashboardController.sendEmailWithAttachment(req,resp);
			break;			
		default:
			resp.setStatus(404);
			resp.sendRedirect("http://localhost:8080/ExpReimburse/expr/home");
		}
	}
	
	public static void processPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String endpoint = req.getRequestURI();
		log.info("In Put" + endpoint);
		switch(endpoint) {
		case "/ExpReimburse/expr/api/accept":
			DashboardController.acceptTicket(req, resp);
			break;
		case "/ExpReimburse/expr/api/reject":
			DashboardController.rejectTicket(req, resp);
			break;
			
		default:
			resp.setStatus(404);
			resp.sendRedirect("http://localhost:8080/ExpReimburse/expr/home");
		}
	}
	
	public static void processDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String endpoint = req.getRequestURI();
		log.info("In Delete" + endpoint);
		switch(endpoint) {
		case "ExpReimburse/expr/api/deleteuser":
			DashboardController.removeUser(req, resp);
			break;
		case "ExpReimburse/expr/api/deleteticket":
			DashboardController.removeTicket(req, resp);
			break;
		case "ExpReimburse/expr/api/deleteAllUsers":
			DashboardController.removeAllUsers(req,resp);
			break;
		case "ExpReimburse/expr/api/deleteAllTickets":
			DashboardController.removeAllTickets(req,resp);
			break;
		default: 
			resp.setStatus(404);
		}
	}
}

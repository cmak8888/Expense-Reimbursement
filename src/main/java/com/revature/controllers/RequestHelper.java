package com.revature.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.exceptions.Exception405;


public class RequestHelper {
    public final static Logger log = Logger.getLogger(RequestHelper.class);
    
	public static void processGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, Exception405 {
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
		case "/ExpReimburse/expr/login":
			LoginController.getLoginPage(req, resp);
			break;
		case "/ExpReimburse/expr/logout":
			LogoutController.getLogOutPage(req, resp);
			break;
		case "/ExpReimburse/expr/api/viewTicket":
			DashboardController.viewTicket(req, resp);
			break;
		case "/ExpReimburse/expr/api/viewTickets":
			DashboardController.viewTickets(req, resp);
			break;
		case "/ExpReimburse/expr/api/viewMyTickets":
			DashboardController.viewMyTickets(req, resp);
			break;
		case "/ExpReimburse/expr/api/endPoints":
			SpecialController.getEndpoints(req,resp);
			break;
		default:
			PageNotFound.error(req,resp);
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
			DashboardController.createNewUser(req, resp);
			break;
		case "/ExpReimburse/expr/api/submitTicket":
			DashboardController.makeTicket(req,resp);
			break;
		case "/ExpReimburse/expr/api/sendEmail":
			DashboardController.sendEmail(req,resp);
			break;
		case "ExpReimburse/expr/api/sendEmailWithAttachment":
			DashboardController.sendEmailWithAttachment(req,resp);
			break;			
		default:
			PageNotFound.error(req,resp);
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
			PageNotFound.error(req,resp);
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
		}
	}
}

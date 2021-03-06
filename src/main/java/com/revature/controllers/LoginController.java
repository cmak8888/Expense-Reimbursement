package com.revature.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserService;

public class LoginController {

    public final static Logger log = Logger.getLogger(LoginController.class);
    
	public static void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		System.out.println(req.getMethod());
		if(req.getMethod().equals("POST")) {
			//Grab information via a form 
//			JSONObject info = new JSONObject(req.);
////			JSONObject jsonObject = new JSONParser().parse(req.getReader()).getAsJsonObject();
			log.info(req.getParameter("username"));
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			if(username == null || username == null) {
				log.warn("No data, drop");
				resp.setStatus(401);
				return;
			}
			if(UserService.verifyUser(username, password)) {
				System.out.println("adding username now!");
				//if username matches the masterUsere we will define a session 
					HttpSession sesh = req.getSession();
					sesh.setAttribute("MasterAccess", true);
					sesh.setAttribute("User", UserService.getUser(req.getParameter("username")));
//					sesh.setAttribute("User", UserService.masterUser);
					log.info(sesh.getAttribute("User").toString());
					
					//we will redirect to the homecontroller research http session to redirect to either dashboard!
//					JSONObject jObc = new JSONObject();
//					jObc.put("url", "http://localhost:8080/ExpReimburse/expr/home");
//					resp.getWriter().write(jObc.toString());
					resp.sendRedirect("http://localhost:8080/ExpReimburse/expr/home");
					return;
			}else {
				resp.setStatus(403);
				log.warn("Error 403: Invalid User");
//				JSONObject jObc = new JSONObject();
//				jObc.put("url", "http://localhost:8080/ExpReimburse/expr/home");
//				resp.getWriter().write(jObc.toString());
				resp.sendRedirect("http://localhost:8080/ExpReimburse/expr/home");
			}
			
		}else {
			resp.setStatus(405);
			log.warn("Error 405 Invalid method");
//			JSONObject jObc = new JSONObject();
//			jObc.put("url", "http://localhost:8080/ExpReimburse/expr/home");
//			resp.getWriter().write(jObc.toString());
			resp.sendRedirect("http://localhost:8080/ExpReimburse/expr/404");
		}
		
	}
	
	public static void getHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("getHomePage() Retrieving home page");
		User user;
		HttpSession sesh = req.getSession(false);
		if(sesh == null) {
			log.info("Not logged in");
			RequestDispatcher redis = req.getRequestDispatcher("/login.html");
			redis.forward(req,resp);
		} else {
			user = (User) req.getSession().getAttribute("User");
			log.info(user.toString());
			log.info(user.getUserType());
			RequestDispatcher redis = req.getRequestDispatcher("/expr/dashboard");
			redis.forward(req,resp);
			return;
		}		
		
	}

	public static void getLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher redis = req.getRequestDispatcher("/login.html");
		redis.forward(req,resp);
		return;
	}
	
}

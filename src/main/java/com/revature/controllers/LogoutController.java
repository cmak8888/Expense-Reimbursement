package com.revature.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class LogoutController {
    public final static Logger log = Logger.getLogger(LogoutController.class);
	public static void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.getSession().invalidate();
		resp.sendRedirect("http://localhost:8080/ExpReimburse/expr/logout");
	}
	
	public static void done(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.sendRedirect("http://localhost:8080/ExpReimburse/expr/login");
	}
	
	public static void getLogOutPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher redis = req.getRequestDispatcher("/logout.html");
		redis.forward(req,resp);		
	}
}

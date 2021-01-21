package com.revature.exceptions;

import javax.servlet.http.HttpServletResponse;

import com.revature.services.Log;;

public class Exception403 extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2791958324577494989L;
	public Exception403(HttpServletResponse resp, String msg) {
		super(msg);
		resp.setStatus(403);
		System.out.println(msg);
		Log.warn("Error 403 " + msg);
	}
}

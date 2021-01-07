package com.revature.exceptions;

import javax.servlet.http.HttpServletResponse;

import com.revature.services.Log;;

public class Exception405 extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2791958324577494989L;
	public Exception405(HttpServletResponse resp, String msg) {
		resp.setStatus(405);
		System.out.println(msg);
		Log.warn("Error 405 " + msg);
	}
}

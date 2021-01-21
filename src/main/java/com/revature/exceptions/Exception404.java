package com.revature.exceptions;

import javax.servlet.http.HttpServletResponse;

import com.revature.services.Log;

public class Exception404 extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4986309553995991816L;

	public Exception404(HttpServletResponse resp, String msg) {
		resp.setStatus(404);
		System.out.println(msg);
		Log.warn("Error 404 " + msg);
	}
}

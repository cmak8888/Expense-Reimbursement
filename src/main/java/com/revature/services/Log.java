package com.revature.services;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.revature.ExpReimburse;
import com.revature.controllers.DashboardController;
import com.revature.models.Ticket;
import com.revature.models.User;

public class Log {
    public final static Logger loggy = Logger.getLogger(ExpReimburse.class);
    
	    public static void startLogger() {
	        loggy.setLevel(Level.ALL);
	        //loggy.info("This is an info block!");
	        
	        loggy.info("The customer has started their online banking process");
	    }
	    
	    public static void info(String message) {
	        loggy.info(message);
	    }
	    
	    public static void info(User message) {
	        loggy.info(message.toString());
	    }
	    
	    public static void info(Ticket message) {
	        loggy.info(message.toString());
	    }
	    
	    public static void warn(String message) {
	        loggy.warn(message);
	    }

}


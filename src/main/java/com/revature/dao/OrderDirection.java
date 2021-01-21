package com.revature.dao;

import com.revature.services.Log;

public enum OrderDirection {
	ASC, DESC;
	
	public static String getString(OrderDirection order) {
		if(order.equals(OrderDirection.ASC)) {
			return "ASC";
		} else if(order.equals(OrderDirection.DESC)) {
			return "DESC";
		} else {
			Log.warn("Error, provided Direction does not exist");
		}
		return "";
	}
}

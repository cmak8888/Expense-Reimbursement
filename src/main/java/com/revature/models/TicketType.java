package com.revature.models;

public enum TicketType {
	LODGING,
	TRAVEL,
	FOOD,
	OTHER;
	
	public static String getTicketString(TicketType type) {
		if(type.equals(TicketType.LODGING)) {
			return "LODGING";
		} else if (type.equals(TicketType.TRAVEL)) {
			return "TRAVEL";
		} else if (type.equals(TicketType.FOOD)) {
			return "FOOD";
		} else if (type.equals(TicketType.OTHER)) {
			return "OTHER";
		}
		return "";
	}
	
	public static int getTicketValue(TicketType type) {
		if(type.equals(TicketType.LODGING)) {
			return 0;
		} else if (type.equals(TicketType.TRAVEL)) {
			return 1;
		} else if (type.equals(TicketType.FOOD)) {
			return 2;
		} else if (type.equals(TicketType.OTHER)) {
			return 3;
		}
		return 0;
	}
}

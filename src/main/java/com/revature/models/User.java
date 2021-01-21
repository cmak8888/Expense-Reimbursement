package com.revature.models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.revature.services.PasswordService;

public class User {

	private int id;
	private String name;
	private String userType; //Financial Manager or Employee
	private String username;
	private String password;
	private String email;
	private String timeStamp;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTimeStamp() {
		return timeStamp;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public User() {
		
	}
	
	public User(int id, String name, String type, String username, String password, String email, String timestamp) {
		this.id = id;
		this.name = name;
		this.userType = type;
		this.username = username;
		this.password = password;
		this.email = email;
		this.timeStamp = timestamp;
	}
	
	public User(String name, String type, String username, String password, String email) {
		this.name = name;
		this.userType = type;
		this.username = username;
		this.password = password;
		this.email = email;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		this.timeStamp = sdf.format(timestamp.getTime());
	}
	
	public User(String name, String type, String username,  String email) {
		this.name = name;
		this.userType = type;
		this.username = username;
		this.password = PasswordService.generatePassword();
		this.email = email;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		this.timeStamp = sdf.format(timestamp.getTime());
	}
	
	public String toString() {
		String output ="";
		output += "Name: " + name
				+ "\nType: " + userType 
				+ "\nUsername: " + username
				+ "\nPassword: ";
		for(int i = 0; i < password.length();i++) {
			output += "*";
		}
		output += "\nEmail: " + email;
		return output;
	}
}

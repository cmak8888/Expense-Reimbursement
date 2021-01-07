package com.revature.models;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;

import com.revature.services.UserService;

public class Ticket {
	private int id;
	private String title;
	private User user;
	private TicketType ticketType;
	private String description;
	private byte[] image;
	private String timeStamp;
	private boolean approved;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TicketType getTicketType() {
		return ticketType;
	}
	
	public int getTicketValue() {
		return TicketType.getTicketValue(ticketType);
	}

	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public Ticket() {
		
	}
	
	public Ticket(int id, int user, String title, String description, int ticketType, byte[] image, boolean approved, String timestamp) {
		this.id = id;
		this.title = title;
		this.user = UserService.getUser(user);
		this.ticketType = TicketType.values()[ticketType];
		this.description = description;
		this.image = image;
		this.approved = approved;
		this.timeStamp = timestamp;
	}
	
	public Ticket(String title, int user, int ticketType, String description, byte[] image) {
		this.title = title;
		this.user = UserService.getUser(user);
		this.ticketType = TicketType.values()[ticketType];
		this.description = description;
		this.image = image;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		this.timeStamp = sdf.format(timestamp.getTime());
	}
	
	public Ticket(String title, User user, int ticketType, String description, String imageLocation) throws IOException {
		this.title = title;
		this.user = user;
		this.ticketType = TicketType.values()[ticketType];
		this.description = description;
		saveImage(imageLocation);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		this.timeStamp = sdf.format(timestamp.getTime());
	}
	
	public Ticket(String title, User user, int ticketType, String description) {
		this.title = title;
		this.user = user;
		this.ticketType = TicketType.values()[ticketType];
		this.description = description;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		this.timeStamp = sdf.format(timestamp.getTime());
	}
	
	public void saveImage(String imageLocation) throws IOException {
		ByteArrayOutputStream baos = null;
		try{
		    BufferedImage originalImage = ImageIO.read(new File(imageLocation));
		    baos = new ByteArrayOutputStream();
		    ImageIO.write( originalImage, "jpg", baos );
		    baos.flush();
		    byte[] imageInByte = baos.toByteArray();
		    this.image = imageInByte;
		    //save imageInByte as blob in database
	    }catch(IOException e){
	        System.out.println(e.getMessage());
	    } finally{
	       baos.close();
	       //close database connection
	    }
	}
	
	public String toString() {
		String output = ""
				+ "Title: " + title
				+ "\nUser: " + user.getName()
				+ "\nTicketType: " + TicketType.getTicketString(ticketType)
				+ "\nDescription: " + description
				+ "\nCreated: " + timeStamp;
		return output;
	}
}

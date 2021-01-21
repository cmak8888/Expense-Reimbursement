package com.revature.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.revature.models.User;

public class MailService {
	private static JavaMailSender javaMailSender;
	
	public static void sendEmail(User user) throws MailException {
		Log.info("Created an email to send to user");
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setSubject("Reimbursement Service For you!");
		mail.setText("Sending Message to you!");
		
		javaMailSender.send(mail);
	}
	
	public static void sendEmail(User user, String msg) {
		Log.info("Created an email to send to user");
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setSubject("Reimbursement Service For you!");
		mail.setText(msg);
		
		javaMailSender.send(mail);
	}
	
	public static void sendEmail(User user, String username, String password) {
		Log.info("Created an email to send to user");
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setSubject("Expense Reimbursement: New Login Information has been made for you!");
		mail.setText("Username: " + username + "\nPassword: " + password);
		
		javaMailSender.send(mail);
	}
	
	public static void sendEmailWithAttachment(User user) throws MailException, MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setTo(user.getEmail());
		helper.setSubject("Testing Mail API with Attachment");
		helper.setText("Please find the attached document below.");

		ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
		helper.addAttachment(classPathResource.getFilename(), classPathResource);

		javaMailSender.send(mimeMessage);
	}
}

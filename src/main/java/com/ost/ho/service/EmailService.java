package com.ost.ho.service;



import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ost.ho.daos.UserDAO;
import com.ost.ho.pojo.User;

@Service("emailService")
@PropertySource("classpath:application.properties")
public class EmailService {
	
	@Autowired
	UserDAO userDAO;
	@Autowired 
	JavaMailSender mailSender;
	@Value("${spring.datasource.username}")
	String username;
	 
	public boolean sendEmail(String email) {
		
		User user = userDAO.getUser(email);
		
		  MimeMessage message = mailSender.createMimeMessage(); 
		  MimeMessageHelper helper;
		 
		try {
			
			  helper = new MimeMessageHelper(message, true); 
			  helper.setTo(user.getEmail());
			  helper.setFrom(username);
			  helper.setSubject("Password Reset"); 
			  StringBuilder text = new
			  StringBuilder(); 
			  text.append("--Start Message--").append(System.lineSeparator())
			  .append("Your password is: " )
			  .append(user.getPassword())
			  .append(System.lineSeparator())
			  . append("----End Message ---------");
			  
			  helper.setText(text.toString());
			
			  mailSender.send(message);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}

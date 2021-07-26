package com.ost.ho.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ost.ho.service.EmailService;

@RestController
public class EmailController {
	
	@Autowired
	EmailService emailService;
	
	@RequestMapping(value = "email", method= RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sendEmail(@RequestBody String email) {
		ResponseEntity<?> entity = null;
		try {
			boolean value = emailService.sendEmail(email);
			entity = new ResponseEntity<Boolean>(value, HttpStatus.OK);
			if(value) {
				return entity;
			}else {
				entity = new ResponseEntity<String>("Error occurred while sending email", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}catch(Exception ex) {
			entity = new ResponseEntity<String>("Error occurred while sending email", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
		
	}

}

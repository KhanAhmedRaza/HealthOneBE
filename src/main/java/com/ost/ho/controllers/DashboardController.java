package com.ost.ho.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ost.ho.model.DashboardFilter;
import com.ost.ho.model.DashboardResult;
import com.ost.ho.service.DashboardService;

@RestController
public class DashboardController {
	@Autowired
	DashboardService dashboardService;
	
	@RequestMapping(value="dashboard", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getInitialValues() {
		ResponseEntity<?> entity = null;
		//<Drug> drugList = null;
		try {
			DashboardResult result = dashboardService.getInitialResult();
			entity = new ResponseEntity<DashboardResult>(result, HttpStatus.OK);
			//entity = new ResponseEntity<User>(user, HttpStatus.OK);l
			System.out.println(entity);
		}catch(Exception ex) {
 			System.out.println(ex);
			entity = new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	@RequestMapping(value="dashboard", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getFiteredValues(@RequestBody DashboardFilter request) {
		ResponseEntity<?> entity = null;
		//<Drug> drugList = null;
		try {
			DashboardResult result = dashboardService.getFilteredResult(request);
			entity = new ResponseEntity<DashboardResult>(result, HttpStatus.OK);
			//entity = new ResponseEntity<User>(user, HttpStatus.OK);
			System.out.println(entity);
		}catch(Exception ex) {
 			System.out.println(ex);
			entity = new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
}

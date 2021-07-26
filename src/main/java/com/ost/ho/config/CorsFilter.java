package com.ost.ho.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CorsFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String method  = request.getMethod();
		response.setHeader("Access-Control-Allow-Origin", "http://ec2-18-218-177-174.us-east-2.compute.amazonaws.com:8000");
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-XSRF-TOKEN");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		if(method.equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			filterChain.doFilter(request,response);
		}
	}

}

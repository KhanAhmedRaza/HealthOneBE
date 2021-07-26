package com.ost.ho.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = { "com.ost.ho.controller" })
public class WebConfig implements WebMvcConfigurer {
	
	public static final String protocol = "smtp";
	public static final String authentication = "true";
	public static final String enable = "true";
	public static final String debug = "true";

	@Value("${spring.javax.email.host}")
	String host;
	@Value("${spring.javax.email.port}")
	String port;
	@Value("${spring.datasource.username}")
	String username;
	@Value("${spring.datasource.password}")
	String password;
	
	/*
	 * public static final String host = "smtp.gmail.com"; public static final int
	 * port = 465; public static final String username = "ostf.healthone@gmail.com";
	 * public static final String password = "Gre@tH1malaya";
	 */
	
 
   @Override
   public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/").setViewName("index");
   }
 
   @Bean
   public ViewResolver viewResolver() {
      InternalResourceViewResolver bean = new InternalResourceViewResolver();
 
      bean.setViewClass(JstlView.class);
      bean.setPrefix("/WEB-INF/");
      bean.setSuffix(".jsp");
 
      return bean;
   }
   
   
   @Bean
   public JavaMailSender getMailSender() {
	   JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	   mailSender.setHost(host);
	   mailSender.setPort(Integer.valueOf(port));
	   mailSender.setUsername(username);
	   mailSender.setPassword(password);
	  // mailSender.
	   
	   
	   Properties properties = new Properties();
	   properties.put("mail.transport.protocol", protocol);
	   properties.put("mail.smtp.auth", authentication);
	   properties.put("mail.smtp.starttls.enabled", enable);
	   properties.put("mail.debug", debug);
	   properties.put("mail.smtp.EnableSSL.enable","true");

	   properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	   properties.setProperty("mail.smtp.port", "465"); 
	   properties.setProperty("mail.smtp.socketFactory.port", "465"); 
	   
	   mailSender.setJavaMailProperties(properties);
	   return mailSender;
   }
}
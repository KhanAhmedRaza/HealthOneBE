package com.ost.ho.config;


import org.apache.tomcat.jdbc.pool.DataSource;
import
org.springframework.beans.factory.annotation.Value;
import
org.springframework.context.annotation.Bean;
import
org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class MySqlDBConfiguration {
	  
	 

	@Value("${spring.datasource.driver-class-name}")
	String driverName;
	@Value("${spring.datasource.url}")
	String url;
	@Value("${spring.datasource.username}")
	String username;
	@Value("${spring.datasource.password}")
	String password;
	
	
	 @Bean(name="hoDataSource")
	 public DataSource getDataSource() {
		 DataSource hoDataSource = new DataSource();
		 hoDataSource.setDriverClassName(driverName);
		 hoDataSource.setUrl(url);
		 hoDataSource.setUsername(username);
		 hoDataSource.setPassword(password);
		 hoDataSource.setValidationQuery("SELECT 1 FROM DUAL");
		 hoDataSource.setTestOnBorrow(true);
		 hoDataSource.setLogValidationErrors(true);
		 return hoDataSource;
	 }
	 
	 @Bean(name="hoJdbcTemplate")
	 public JdbcTemplate getJdbcTemplate() {
		 JdbcTemplate hoJdbcTemplate = new JdbcTemplate(getDataSource());
		 return hoJdbcTemplate;
	 }
	 
	 @Bean(name="hoNamedJdbcTemplate")
	 public NamedParameterJdbcTemplate getNamedJdbcTemplate() {
		 NamedParameterJdbcTemplate hoNamedJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		 return hoNamedJdbcTemplate;
	 }
}

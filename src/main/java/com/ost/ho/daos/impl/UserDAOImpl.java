package com.ost.ho.daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ost.ho.daos.UserDAO;
import com.ost.ho.pojo.User;
import com.ost.ho.util.QueryBean;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	@Autowired
	JdbcTemplate hoJdbcTemplate;

	
	  @Autowired QueryBean queryBean;
	 
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		String userSelect = queryBean.getQuery("UserDAO.insertuser"); 
		String userDetailSelect = queryBean.getQuery("UserDAO.insertuserDetails"); 
		/*
		 * try {
		 * 
		 * }catch(Exception ex) {
		 * 
		 * }
		 */
		hoJdbcTemplate.update(userSelect, new Object[] { user.getEmail(), user.getPassword()});
		hoJdbcTemplate.update(userDetailSelect, new Object[] {user.getEmail(),user.getFirstName(), user.getLastName(), 
				user.getProfession() });
		

	}

	@Override
	public User authenticate(User usr) {
		String userSelect = queryBean.getQuery("UserDAO.selectuser"); 
		List<User> users = hoJdbcTemplate.query(userSelect, new Object[] {usr.getEmail()}, 
		(rs, rownum) -> {
			User user = new User();
			user.setEmail(rs.getString("userid"));
			user.setPassword(rs.getString("password"));
			return user;
		});
		return users.get(0);
	}

	@Override
	public User getUser(String  userName) {
		// TODO Auto-generated method stub
		String userSelect = queryBean.getQuery("UserDAO.selectuser"); 
		
		  List<User> users = hoJdbcTemplate.query(userSelect, new Object[] {userName},
		  (rs, rownum) -> { User user = new User();
		  user.setEmail(rs.getString("userid"));
		  user.setPassword(rs.getString("password")); return user; });
		  return users.get(0);
		 
		
		//User user = hoJdbcTemplate.queryForObject(userSelect, new Object[] {userName},User.class);
		//return user;
	}

}

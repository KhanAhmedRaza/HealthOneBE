package com.ost.ho.daos;

import org.springframework.stereotype.Repository;

import com.ost.ho.pojo.User;

@Repository
public interface UserDAO {
	public void addUser(User user) throws Exception; 
	public User authenticate(User user);
	public User getUser(String userName);
}

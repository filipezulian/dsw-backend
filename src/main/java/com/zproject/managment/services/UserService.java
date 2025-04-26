package com.zproject.managment.services;

import java.util.List;

import com.zproject.managment.model.Credential;
import com.zproject.managment.model.User;

public interface UserService {

	Long add(User user);
	
	User getById(Long id);
	
	User edit(User user);
	
	void delete(Long id);
	
	User getByName(String name);

	User getByEmail(String email);
	
	List<User> getAll();
	
	User auth(Credential credential);
	
}

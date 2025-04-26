package com.zproject.managment.services;

import java.util.List;

import com.zproject.managment.model.Project;
import com.zproject.managment.model.User;
import com.zproject.managment.model.UserProject;

public interface UserProjectService {

	Long add(UserProject user_project);
	
	void delete(Long id);
	
	List<User> getUsersByProfile(Long profile_id);
	
	List<User> getUsersByProject(Long project_id);
	
	List<Project> getProjectsByUser(Long user_id);
	
}

package com.zproject.managment.services;

import java.util.List;

import com.zproject.managment.model.UserProject;

public interface UserProjectService {

	Long add(UserProject user_project);
	
	void delete(Long id);
	
	List<UserProject> getUsersByProfile(Long profile_id);
	
	List<UserProject> getUsersByProject(Long project_id);
	
	List<UserProject> getProjectsByUser(Long user_id);
	
}

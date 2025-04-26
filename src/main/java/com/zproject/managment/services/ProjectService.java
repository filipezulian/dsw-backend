package com.zproject.managment.services;

import java.util.List;

import com.zproject.managment.model.Project;

public interface ProjectService {

	List<Project> getAll();
	
	Long save(Project project);
	
	Project edit(Project project);
	
	void delete(Long id);
	
}

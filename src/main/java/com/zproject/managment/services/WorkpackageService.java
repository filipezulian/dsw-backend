package com.zproject.managment.services;

import java.util.List;

import com.zproject.managment.model.Workpackage;

public interface WorkpackageService {

	List<Workpackage> getAllByProject(Long project_id);
	
	Integer getTotalHours(Long project_id);
	
	Long save(Workpackage workpackage);
	
	Workpackage edit(Workpackage workpackage);
	
	void delete(Long id);
	
}

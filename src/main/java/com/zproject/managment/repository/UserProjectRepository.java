package com.zproject.managment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zproject.managment.model.UserProject;

public interface UserProjectRepository extends JpaRepository<UserProject, Long>{

	Optional<UserProject> findById(Long id);
	
	Optional<UserProject> findByUser(Long user_id);
	
	List<UserProject> findByProject(Long project_id);
	
	List<UserProject> findAll();
	
}

package com.zproject.managment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zproject.managment.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

	Optional<Project> findById(Long id);
	
	List<Project> findAll();

    @Query("SELECT u FROM projects p JOIN user_projects up on p.project_id = up.project_id WHERE up.user_id = :id")
	List<Project> findAllByUser(@Param("id") int id);
	
}

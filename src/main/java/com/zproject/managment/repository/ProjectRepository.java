package com.zproject.managment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zproject.managment.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{
	@Query("SELECT p FROM Project p JOIN p.users up WHERE up.user.id = :userId")
	List<Project> findAllByUser(@Param("id") int id);
	
}

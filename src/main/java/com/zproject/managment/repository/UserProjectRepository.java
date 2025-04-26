package com.zproject.managment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zproject.managment.model.User;
import com.zproject.managment.model.UserProject;

public interface UserProjectRepository extends JpaRepository<UserProject, Long>{
	
	Optional<UserProject> findByUser(User user);

	@Query("SELECT up FROM UserProject up WHERE up.project.id = :project_id")
	List<UserProject> findByProject(@Param("project_id") long project_id);
	
}

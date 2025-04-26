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

	@Query("SELECT up FROM UserProject up WHERE up.project_id = :project_id")
	List<UserProject> findByProject(@Param("project_id") long project_id);

	@Query("SELECT up FROM UserProject up WHERE up.profile_id = :profile_id")
	List<UserProject> findByProfile(@Param("profile_id") long profile_id);

	@Query("SELECT up FROM UserProject up WHERE up.user_id = :user_id")
	List<UserProject> findByUser(@Param("user_id") long user_id);
}

package com.zproject.managment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zproject.managment.model.Profile;
import com.zproject.managment.model.Project;
import com.zproject.managment.model.User;
import com.zproject.managment.model.UserProject;

public interface UserProjectRepository extends JpaRepository<UserProject, Long>{
	
	List<UserProject> findByUser(User user);

    List<UserProject> findByProfile(Profile profile);

    List<UserProject> findByProject(Project project);
}

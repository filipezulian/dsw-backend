package com.zproject.managment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zproject.managment.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{	
}

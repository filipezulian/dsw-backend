package com.zproject.managment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zproject.managment.model.Workpackage;

public interface WorkpackageRepository extends JpaRepository<Workpackage, Long> {

	Optional<Workpackage> findById(Long id);

    @Query("SELECT u FROM workpackage w WHERE w.project_id = :project_id")
	List<Workpackage> findByProject(@Param("project_id") int project_id);
	
	List<Workpackage> findAll();
	
}
package com.zproject.managment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zproject.managment.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

	Optional<Status> findById(Long id);
	
	List<Status> findAll();
	
}
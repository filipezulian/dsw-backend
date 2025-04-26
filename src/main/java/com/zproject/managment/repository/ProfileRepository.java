package com.zproject.managment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zproject.managment.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{

	Optional<Profile> findById(Long id);
	
	List<Profile> findAll();
	
}
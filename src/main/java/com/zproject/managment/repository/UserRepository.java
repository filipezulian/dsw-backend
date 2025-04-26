package com.zproject.managment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zproject.managment.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findById(Long id);
	
	Optional<User> findByName(Long name);
	
	List<User> findAll();
	
	Optional<User> findByEmail(String email);

}

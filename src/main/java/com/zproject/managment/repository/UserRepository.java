package com.zproject.managment.repository;

import com.zproject.managment.model.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findById(long id);
	
	Optional<User> findByName(String name);
	
	List<User> findAll();

    @Query("SELECT u FROM Users u WHERE u.profile = :profile")
    List<User> findByPermissao(@Param("profile") int profile);
	
	Optional<User> findByEmail(String email);
	
}

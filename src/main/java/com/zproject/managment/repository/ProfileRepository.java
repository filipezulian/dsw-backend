package com.zproject.managment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zproject.managment.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
}
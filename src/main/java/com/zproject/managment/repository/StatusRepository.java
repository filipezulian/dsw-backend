package com.zproject.managment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zproject.managment.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
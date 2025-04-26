package com.zproject.managment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zproject.managment.model.Workpackage;

public interface WorkpackageRepository extends JpaRepository<Workpackage, Long> {

    List<Workpackage> findByProject_Id(long project_id);

    @Query("SELECT SUM(w.hours) FROM Workpackage w WHERE w.project.id = :projectId")
    Integer sumHoursByProject_Id(@Param("projectId") long projectId);
}
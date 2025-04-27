package com.zproject.managment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "workpackages")
public class Workpackage {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workpackage_id")
    private Long id;

 	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
 	@JsonBackReference
	private Project project;
	
	private String name;
	
	private String description;
	
	private Integer hours;
	
	private String start_dt;
	
	private String end_dt;	
}

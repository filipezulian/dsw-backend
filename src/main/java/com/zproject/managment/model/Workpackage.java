package com.zproject.managment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "workpackages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Workpackage {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workpackage_id")
    private Long id;
	
	private Integer project_id;
	
	private String name;
	
	private String description;
	
	private Integer hours;
	
	private String start_dt;
	

	private String end_dt;	
}

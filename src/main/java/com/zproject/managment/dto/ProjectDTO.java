package com.zproject.managment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectDTO {
    private Long projectId;
    private String name;
    private String description;
    private String startDt;
    private String endDt;
    private Integer totalTime;
    private long status;
}
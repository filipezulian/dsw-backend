package com.zproject.managment.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zproject.managment.dto.ProjectDTO;
import com.zproject.managment.model.Project;
import com.zproject.managment.services.ProjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/all")
    public ResponseEntity<List<ProjectDTO>> getAll() {
        List<ProjectDTO> projects = projectService.getAll()
	            .stream()
	            .map(project -> new ProjectDTO(project.getId(), project.getName(), project.getDescription(), project.getStart_dt(), project.getEnd_dt(), project.getTotal_time(), project.getStatus().getId()))
	            .collect(Collectors.toList());
        return ResponseEntity.ok(projects);
    }

    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody Project project) {
        Long id = projectService.save(project);
        return ResponseEntity.status(201).body(id);
    }

    @PutMapping("/edit")
    public ResponseEntity<Project> edit(@RequestBody Project project) {
        Project updatedProject = projectService.edit(project);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id/{id}")
    public Optional<Project> getById(@PathVariable Long id) {
        Optional<Project> project = projectService.get(id);
        return project;
    }
}

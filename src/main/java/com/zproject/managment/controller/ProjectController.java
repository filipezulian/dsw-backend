package com.zproject.managment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zproject.managment.model.Project;
import com.zproject.managment.services.ProjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAll() {
        List<Project> projects = projectService.getAll();
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
}

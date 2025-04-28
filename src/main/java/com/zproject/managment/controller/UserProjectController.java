package com.zproject.managment.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zproject.managment.dto.UserDTO;
import com.zproject.managment.model.Project;
import com.zproject.managment.model.UserProject;
import com.zproject.managment.services.UserProjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user-projects")
@RequiredArgsConstructor
public class UserProjectController {

    private final UserProjectService userProjectService;

    @PostMapping("/add")
    public ResponseEntity<Long> add(@RequestBody UserProject userProject) {
        Long id = userProjectService.add(userProject);
        return ResponseEntity.status(201).body(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userProjectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usersByProject/{profile_id}")
    public ResponseEntity<List<UserDTO>> getUsersByProfile(@PathVariable Long profile_id) {
        List<UserDTO> users = userProjectService.getUsersByProfile(profile_id)
            .stream()
            .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getProfile()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/usersByProject/{project_id}")
    public ResponseEntity<List<UserDTO>> getUsersByProject(@PathVariable Long project_id) {
        List<UserDTO> users = userProjectService.getUsersByProject(project_id)
            .stream()
            .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getProfile()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/projectsByUser/{user_id}")
    public ResponseEntity<List<Project>> getProjectsByUser(@PathVariable Long user_id) {
        List<Project> projects = userProjectService.getProjectsByUser(user_id);
        return ResponseEntity.ok(projects);
    }
}

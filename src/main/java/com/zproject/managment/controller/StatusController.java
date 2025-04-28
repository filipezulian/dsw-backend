package com.zproject.managment.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zproject.managment.dto.StatusDTO;
import com.zproject.managment.services.StatusService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/statuses")
@RequiredArgsConstructor
public class StatusController {

    private final StatusService statusService;

    @GetMapping("/all")
    public ResponseEntity<List<StatusDTO>> getAll() {
        List<StatusDTO> statuses = statusService.getAll()
            .stream()
            .map(status -> new StatusDTO(status.getId(), status.getName()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(statuses);
    }
    
    @PostMapping("/create")
    public ResponseEntity<String> createAll() {
        boolean created = statusService.createAll();
        if (created) {
	        return ResponseEntity.status(201).body("Statuses created successfully.");
	    } else {
	        return ResponseEntity.ok("Statuses already exist. No action taken.");
	    }
    }
}
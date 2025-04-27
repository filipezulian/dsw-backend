package com.zproject.managment.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zproject.managment.dto.ProfileDTO;
import com.zproject.managment.services.ProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

	private final ProfileService profileService;
	
	@GetMapping("/all")
    public ResponseEntity<List<ProfileDTO>> getAll() {
		List<ProfileDTO> profiles = profileService.getAll()
	            .stream()
	            .map(profile -> new ProfileDTO(profile.getId(), profile.getName()))
	            .collect(Collectors.toList());
	        return ResponseEntity.ok(profiles);
    }
	
	@PostMapping("/create")
	public ResponseEntity<String> createAll() {
		boolean created = profileService.createAll();
		if (created) {
	        return ResponseEntity.status(201).body("Profiles created successfully.");
	    } else {
	        return ResponseEntity.ok("Profiles already exist. No action taken.");
	    }
	}
	
}

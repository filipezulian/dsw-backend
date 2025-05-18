package com.zproject.managment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zproject.managment.dto.UserDTO;
import com.zproject.managment.model.Credential;
import com.zproject.managment.model.User;
import com.zproject.managment.security.JwtService;
import com.zproject.managment.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
    private final JwtService jwtService;
	
	@PostMapping("/create")
    public ResponseEntity<Long> createUser(@RequestBody User user) {
        Long user_id = userService.add(user);
        return new ResponseEntity<>(user_id, HttpStatus.CREATED);
    }
	
	@PostMapping("/auth")
    public ResponseEntity<Map<String, String>> auth(@RequestBody Credential credential) {
		User authUser = userService.auth(credential);
		
        String jwtToken = jwtService.generateToken(authUser.getEmail(), authUser.getId(), authUser.getProfile().getId());

        Map<String, String> response = new HashMap<String, String>();
        response.put("token", jwtToken);

        return ResponseEntity.ok(response);
    }
	
	@GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAll()
	            .stream()
	            .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getProfile()))
	            .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @PutMapping("/edit")
    public ResponseEntity<User> editUser(@RequestBody User user) {
        User updatedUser = userService.edit(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.getByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
        User user = userService.getByName(name);
        return ResponseEntity.ok(user);
    }
    
}

package com.zproject.managment.controller;

import java.util.List;

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

import com.zproject.managment.exception.ErrorAuth;
import com.zproject.managment.model.Credential;
import com.zproject.managment.model.User;
import com.zproject.managment.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@PostMapping("/create")
    public ResponseEntity<Long> createUser(@RequestBody User user) {
        Long user_id = userService.add(user);
        return new ResponseEntity<>(user_id, HttpStatus.CREATED);
    }
	
	@PostMapping("/auth")
    public ResponseEntity<User> auth(@RequestBody Credential credential) {
        try {
            User authUser = userService.auth(credential);
            return ResponseEntity.ok(authUser);
        } catch (ErrorAuth e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
	
	@GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/edit")
    public ResponseEntity<User> editUser(@RequestBody User user) {
        User updatedUser = userService.edit(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/id/{id}")
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

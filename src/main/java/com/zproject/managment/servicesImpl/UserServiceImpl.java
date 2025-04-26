package com.zproject.managment.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.zproject.managment.exception.DuplicateEmailException;
import com.zproject.managment.exception.ResourceNotFoundException;
import com.zproject.managment.model.Credential;
import com.zproject.managment.model.User;
import com.zproject.managment.repository.UserRepository;
import com.zproject.managment.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    @Override
    public Long add(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateEmailException(user.getEmail() + " is already being used.");
        }

        user.setPassword(bcrypt.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id = " + id + " not found"));
    }

    @Override
    public User edit(User user) {
        User existingUser = getById(user.getId());

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existingUser.setPassword(bcrypt.encode(user.getPassword()));
        }

        return userRepository.save(existingUser);
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User with id = " + id + " not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User getByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("User with name = " + name + " not found"));
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email = " + email + " not found"));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User auth(Credential credential) {
        User user = userRepository.findByEmail(credential.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid email or password"));

        if (!bcrypt.matches(credential.getPassword(), user.getPassword())) {
            throw new ResourceNotFoundException("Invalid email or password");
        }

        return user;
    }

}

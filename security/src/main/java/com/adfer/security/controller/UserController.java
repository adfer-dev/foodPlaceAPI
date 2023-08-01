package com.adfer.security.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adfer.security.model.User;
import com.adfer.security.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping
	public ResponseEntity<Object> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			userRepository.findAll().forEach(users::add);
		} catch (Exception e) {
			return ResponseEntity.status(404).body("Not found");
		}
		
		return ResponseEntity.status(200).body(users);
	}
}

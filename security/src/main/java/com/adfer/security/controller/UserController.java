package com.adfer.security.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.adfer.security.model.UserDTO;
import com.adfer.security.repository.UserRepository;
import com.adfer.security.service.UserDTOMapper;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserRepository userRepository;
	private final UserDTOMapper userDTOMapper;
	
	public UserController(UserRepository userRepository, UserDTOMapper userDTOMapper) {
		this.userRepository = userRepository;
		this.userDTOMapper = userDTOMapper;
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getUsers() {
		
		return ResponseEntity.status(200).body(userRepository.findAll()
				.stream()
				.map(userDTOMapper)
				.collect(Collectors.toList()));
	}
}

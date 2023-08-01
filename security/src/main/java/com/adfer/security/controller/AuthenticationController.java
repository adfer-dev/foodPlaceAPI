package com.adfer.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adfer.security.model.AuthenticationRequest;
import com.adfer.security.model.AuthenticationResponse;
import com.adfer.security.model.RegisterRequest;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
	
	private final AuthenticationService authenticationService;
	
	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest body) {
		return ResponseEntity.ok(authenticationService.register(body));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest body) {
		return ResponseEntity.ok(authenticationService.authenticate(body));
	}
}

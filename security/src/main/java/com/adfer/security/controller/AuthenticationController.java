package com.adfer.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.adfer.security.model.AuthenticationRequest;
import com.adfer.security.model.AuthenticationResponse;
import com.adfer.security.model.RegisterRequest;
import com.adfer.security.service.AuthenticationService;

import io.jsonwebtoken.io.IOException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Class that handles authentication endpoints (register, authenticate and refresh token)
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
	
	private final AuthenticationService authenticationService;
	
	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@Operation(summary = "Register a new user", description = "Returns the new user's token and refresh token.")
	@ApiResponses(value = {
			 @ApiResponse(responseCode = "201", description = "User successfully added."), 
		     @ApiResponse(responseCode = "400", description = "Bad request. Some mandatory field was not provided or wrong formatted.")
	    })
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest body) {
		return ResponseEntity.ok(authenticationService.register(body));
	}
	
	@Operation(summary = "Authenticates a user, given its email and password.", description = "Returns the user's token and refresh token.")
	@ApiResponses(value = {
			 @ApiResponse(responseCode = "200", description = "Food place successfully added."),
		     @ApiResponse(responseCode = "400", description = "Bad request. Some mandatory field was not provided or wrong formatted."),
		     @ApiResponse(responseCode = "403", description = "Wrong email or username.")
	    })
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest body) {
		return ResponseEntity.ok(authenticationService.authenticate(body));
	}
	
	@Operation(summary = "Gives the user a new token, given a refresh token.", description = "Returns the user's new token and refresh token.")
	@ApiResponses(value = {
			 @ApiResponse(responseCode = "200", description = "Food place successfully added."),
		     @ApiResponse(responseCode = "403", description = "Refresh token expired.")
	    })
	@PostMapping("/refresh-token")
	 public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		    authenticationService.refreshToken(request, response);
	}
}

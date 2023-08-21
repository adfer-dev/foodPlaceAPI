package com.adfer.security.service;

import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.adfer.security.auth.JwtService;
import com.adfer.security.model.AuthenticationRequest;
import com.adfer.security.model.AuthenticationResponse;
import com.adfer.security.model.RegisterRequest;
import com.adfer.security.model.Token;
import com.adfer.security.model.TokenType;
import com.adfer.security.model.User;
import com.adfer.security.model.UserRole;
import com.adfer.security.repository.TokenRepository;
import com.adfer.security.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Class that perform authentication operations
 */
@Service
public class AuthenticationService {

	private final UserRepository userRepository;
	private final TokenRepository tokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	
	public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder,
			JwtService jwtService, TokenRepository tokenRepository) {
		this.userRepository = userRepository;
		this.tokenRepository = tokenRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}


	public AuthenticationResponse register(RegisterRequest registerRequest) {
		User user = new User(registerRequest.getFirstName(), registerRequest.getEmail(), passwordEncoder.encode(registerRequest.getPassword()), UserRole.ADMIN, null);
		Token token = new Token(jwtService.generateToken(user), TokenType.BEARER, user);
		
		user.setToken(token);
		userRepository.save(user);
		tokenRepository.save(token);
		
		return new AuthenticationResponse(token.getTokenValue(), jwtService.generateRefreshToken(user));
	}
	
	public AuthenticationResponse authenticate (AuthenticationRequest authenticationRequest) {
		User user = userRepository.findByEmail(authenticationRequest.getEmail())
				.orElseThrow();
		
		
		Token token = user.getToken();
		
		if (jwtService.isTokenExpired(token.getTokenValue())) {
			token.setTokenValue(jwtService.generateToken(user));
			tokenRepository.save(token);
		}
		
		return new AuthenticationResponse(token.getTokenValue(), jwtService.generateRefreshToken(user));
	}
	
	public void refreshToken  (HttpServletRequest request, HttpServletResponse response) {
	    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
	    final String refreshToken;
	    final String userEmail;
	    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
	      return;
	    }
	    refreshToken = authHeader.substring(7);
	    userEmail = jwtService.extractUserName(refreshToken);
	    if (userEmail != null) {
	      var user = userRepository.findByEmail(userEmail)
	              .orElseThrow();
	      if (jwtService.isTokenValid(refreshToken, user)) {
	    	String token = jwtService.generateToken(user);
	        user.getToken().setTokenValue(token);
	        tokenRepository.save(user.getToken());
	        AuthenticationResponse authResponse = new AuthenticationResponse(token, refreshToken);
	        try {
				new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
			} catch (java.io.IOException e) {
				e.printStackTrace();
			}
	      }
	    }
	  }
}

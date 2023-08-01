package com.adfer.security.controller;

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
		
		return new AuthenticationResponse(token.getTokenValue());
	}
	
	public AuthenticationResponse authenticate (AuthenticationRequest authenticationRequest) {
		User user = userRepository.findByEmail(authenticationRequest.getEmail())
				.orElseThrow();
		
		
		Token token = user.getToken();
		
		if (jwtService.isTokenExpired(token.getTokenValue())) {
			token.setTokenValue(jwtService.generateToken(user));
			tokenRepository.save(token);
		}
		
		return new AuthenticationResponse(token.getTokenValue());
	}
	
}

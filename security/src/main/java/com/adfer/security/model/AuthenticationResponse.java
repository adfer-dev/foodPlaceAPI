package com.adfer.security.model;

public class AuthenticationResponse {
	private String token;

	public AuthenticationResponse(String tokenString) {
		this.token = tokenString;
	}

	public String getTokenString() {
		return token;
	}

	public void setTokenString(String tokenString) {
		this.token = tokenString;
	}
	
	
}

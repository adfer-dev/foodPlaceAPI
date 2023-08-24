package com.adfer.security.config;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Class that handles unauthorized requests (unregistered users or expired tokens)
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		Map<String, String> errorMessageMap = Collections.singletonMap(
				"error", 
				"Token expired or Authorization header not provided. Please refresh your token or register.");
		ObjectMapper jsonMapper = new ObjectMapper();		
		
		response.setStatus(401);
		response.setContentType("application/json");
		response.getWriter().write(jsonMapper.writeValueAsString(errorMessageMap));

	}

}

package com.adfer.security.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getPropertyName() + " :" + ex.getMessage() , 
        		new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation (ConstraintViolationException ex, WebRequest request) {
    	
    	// Build response message in JSON format
    	Map<String, String> constraintViolations = new LinkedHashMap<String, String>();
    	
    	ex.getConstraintViolations().forEach(constraint -> {
    		constraintViolations.put(
    				String.valueOf(constraint.getInvalidValue()), 
    				constraint.getMessage());
    	});
    	
    	ObjectMapper jsonMapper = new ObjectMapper();

    	// Add response headers
    	MultiValueMap<String, String> responseHeaders = new LinkedMultiValueMap<String, String>();
    	responseHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
    	
    	return handleExceptionInternal(
    			ex, 
    			jsonMapper.writeValueAsString(constraintViolations), 
    			new HttpHeaders(responseHeaders), 
    			HttpStatus.BAD_REQUEST, request);
    }
}


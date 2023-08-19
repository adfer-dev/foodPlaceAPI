package com.adfer.security.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;
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
    	StringBuilder stringBuilder = new StringBuilder();
    	
    	ex.getConstraintViolations().forEach(constraint -> {
    		stringBuilder.append(constraint.getMessage() + "\n");
    	});
    	
    	return handleExceptionInternal(ex, stringBuilder , 
    			new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}


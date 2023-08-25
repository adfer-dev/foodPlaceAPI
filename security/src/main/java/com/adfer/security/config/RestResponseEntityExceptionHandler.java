package com.adfer.security.config;

import java.util.Collections;
import java.util.LinkedHashMap;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolationException;

/**
 * Class that handles the API response when some exceptions take place.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Exception handler for the MethodArgumentTypeMismatchException
	 * @param ex the MethodArgumentTypeMismatchException exception to be handled
	 * @param request the current request
	 * @return the response with the selected message, headers and status
	 * @throws JsonProcessingException 
	 */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) throws JsonProcessingException {
    	
    	Map<String, String> errorMessageMap = Collections.singletonMap(
    			ex.getPropertyName(),
    			"Must be " + ex.getRequiredType().getSimpleName());
    	MultiValueMap<String, String> headersMap = new LinkedMultiValueMap<String, String>();
    	ObjectMapper jsonMapper = new ObjectMapper();
    	
    	// Add response headers
    	headersMap.add(HttpHeaders.CONTENT_TYPE, "application/json");
    	
    	return handleExceptionInternal(
        		ex, 
        		jsonMapper.writeValueAsString(errorMessageMap), 
        		new HttpHeaders(headersMap),
        		HttpStatus.BAD_REQUEST, request);
    }
    
    /**
	 * Exception handler for the ConstraintViolationException
	 * @param ex the ConstraintViolationException exception to be handled
	 * @param request the current request
	 * @return the response with the selected message, headers and status
	 */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation (ConstraintViolationException ex, WebRequest request) throws JsonProcessingException {
    	
    	// Build response message in JSON format
    	Map<String, String> constraintViolations = new LinkedHashMap<String, String>();
    	MultiValueMap<String, String> responseHeaders = new LinkedMultiValueMap<String, String>();
    	ObjectMapper jsonMapper = new ObjectMapper();
    	
    	// Add failing constraints and its messages to a map object so that they can be mapped to a JSON format.
    	ex.getConstraintViolations().forEach(constraint -> {
    		constraintViolations.put(
    				String.valueOf(constraint.getPropertyPath()), 
    				constraint.getMessage());
    	});

    	// Add response headers
    	responseHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
    	
    	return handleExceptionInternal(
    			ex, 
    			jsonMapper.writeValueAsString(constraintViolations), 
    			new HttpHeaders(responseHeaders), 
    			HttpStatus.BAD_REQUEST, request);
    }
}


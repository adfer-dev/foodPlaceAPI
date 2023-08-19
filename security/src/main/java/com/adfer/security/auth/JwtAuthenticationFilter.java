package com.adfer.security.auth;

import java.io.IOException;

import javax.naming.AuthenticationException;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    
    private final JwtService jwtService;
    private UserDetailsService userDetailsService;
    
    

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}



	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        String userEmail = null;
        
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        jwtToken = authHeader.substring(7);
        
        try {
        	userEmail = jwtService.extractUserName(jwtToken);
		} catch (ExpiredJwtException e) {
			System.err.print(e);
		}
        
       
        // Check that user mail is not null and user hasn't been authenticated yet.
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if(jwtService.isTokenValid(jwtToken, userDetails)) {
            	UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            			userDetails,
            			null,
            			userDetails.getAuthorities()
            	);
            	authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            	SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        
            filterChain.doFilter(request, response);
    }
    
}

package com.adfer.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.adfer.security.auth.JwtAuthenticationFilter;
import static com.adfer.security.model.UserRole.ADMIN;;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authenticationProvider;
	private final CustomAuthenticationEntryPoint entryPoint;
	
	public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider, CustomAuthenticationEntryPoint entryPoint) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.authenticationProvider = authenticationProvider;
		this.entryPoint = entryPoint;
	}
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler () {
		return new CustomAccessDeniedHandler();
	}
		
	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
		    .authorizeHttpRequests(authorize -> authorize
		        .requestMatchers(
		        		 "/api/v1/auth/**",
		                 "/v2/api-docs",
		                 "/v3/api-docs",
		                 "/v3/api-docs/**",
		                 "/swagger-resources",
		                 "/swagger-resources/**",
		                 "/configuration/ui",
		                 "/configuration/security",
		                 "/swagger-ui/**",
		                 "/webjars/**",
		                 "/swagger-ui.html"
		        )
		        .permitAll()
		        .requestMatchers(HttpMethod.POST, "/api/v1/**").hasRole(ADMIN.name())
		        .requestMatchers(HttpMethod.PUT, "/api/v1/**").hasRole(ADMIN.name())
		        .requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole(ADMIN.name())
		        .anyRequest().authenticated()
		    )
		    .exceptionHandling(handler -> handler
		    		.accessDeniedHandler(accessDeniedHandler())
		    		.authenticationEntryPoint(entryPoint)
		    )
		    .sessionManagement(session -> session
		        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		    )
		    .authenticationProvider(authenticationProvider)
		    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	return http.build();
	}
	
	

}

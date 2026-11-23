package com.commerce.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.commerce.user_service.util.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

	private final JwtAuthenticationFilter JwtFilter = new JwtAuthenticationFilter();
	
	@Bean
	public PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder(); // BCryptPasswordEncoder → A strong hashing algorithm used to store passwords securely in the database.
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) {
		
		 http.csrf(csrf -> csrf.disable()) // CSRF (Cross-Site Request Forgery) protection is disabled.
		.sessionManagement(sess ->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // stateless and scalable
		.authorizeHttpRequests(auth -> auth.requestMatchers("/api/users/register", "/api/users/login", "/actuator/**").permitAll() // authorize who can access this
		.anyRequest().authenticated())
		.addFilterBefore(JwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
}

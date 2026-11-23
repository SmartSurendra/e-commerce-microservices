package com.commerce.user_service.util;

import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.lang.Collections;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor  // create constructor and required field as final automatically
public class JwtAuthenticationFilter extends OncePerRequestFilter {  // This class execute for every HTTP request

	private final JwtUtil jwtUtil = null;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
	final String authHeader = request.getHeader("Authorization");  // Check and Extract Token
	
	if(authHeader !=null && authHeader.startsWith("Bearer")) { 
		   final String token = authHeader.substring(7);
		   
	   try {
				jwtUtil.validateToken(token);                     // Validate Token and Authenticate 
				Long userId = jwtUtil.getUserIdFromToken(token);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId,null,Collections.emptyList());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (Exception e) {
				
				SecurityContextHolder.clearContext();
			}
		}
		
	    filterChain.doFilter(request, response);
   }
}

package com.commerce.user_service.mapper;

import org.springframework.stereotype.Component;
import com.commerce.user_service.dto.LoginResponseDto;
import com.commerce.user_service.entity.User;

@Component
public class AuthMapper {
	
	public LoginResponseDto toLoginResponse(User user, String token) {
		return new LoginResponseDto(token,"Bearer", user.getId(),user.getEmail(),user.getName());
	}
}

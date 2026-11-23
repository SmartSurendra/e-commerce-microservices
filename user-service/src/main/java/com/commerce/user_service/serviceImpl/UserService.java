package com.commerce.user_service.serviceImpl;

import com.commerce.user_service.dto.LoginRequestDto;
import com.commerce.user_service.dto.LoginResponseDto;
import com.commerce.user_service.dto.UserCreateRequestDto;
import com.commerce.user_service.dto.UserResponseDto;

public interface UserService {

	UserResponseDto register(UserCreateRequestDto dto);
	LoginResponseDto login(LoginRequestDto dto);
    UserResponseDto getById(Long id);	
}

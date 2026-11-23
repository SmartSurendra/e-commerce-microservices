package com.commerce.user_service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.commerce.user_service.dto.LoginRequestDto;
import com.commerce.user_service.dto.LoginResponseDto;
import com.commerce.user_service.dto.UserCreateRequestDto;
import com.commerce.user_service.dto.UserResponseDto;
import com.commerce.user_service.entity.User;
import com.commerce.user_service.exception.ResourceNotFoundException;
import com.commerce.user_service.mapper.AuthMapper;
import com.commerce.user_service.mapper.UserMapper;
import com.commerce.user_service.repository.UserRepository;
import com.commerce.user_service.util.JwtUtil;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	@Autowired
	private final UserRepository userRepo;
	
	@Autowired
	private final UserMapper userMapper;
	
	private final JwtUtil jwtUtil;
	
	private final PasswordEncoder passwordEncoder;
	
	private final AuthMapper authMapper;
	
	@Override
	public UserResponseDto register(UserCreateRequestDto dto) {
		
		if(userRepo.existsByEmail(dto.getEmail())) {
			throw new RuntimeException("Email already exist");
		}
		
		User user = userMapper.toEntity(dto);
		User saved = userRepo.save(user);
		return userMapper.toDto(saved);
	}

	@Override
	public LoginResponseDto login(LoginRequestDto dto) {
		 
		 User user =  userRepo.findByEmail(dto.getEmail()).orElseThrow(()-> new ResourceNotFoundException("invalid credential"));
		 boolean matches =  passwordEncoder.matches(dto.getPassword(), user.getPassword());
		 
		if(!matches) {
			throw new ResourceNotFoundException("invalid credentials");
		}
		
	//	User user1 = userRepo.findByEmail(dto.getEmail()).orElseThrow(()-> new ResourceNotFoundException("invalid cred"));
		
		String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRoles());
		return authMapper.toLoginResponse(user, token);
	}

	@Override
	public UserResponseDto getById(Long id) {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found exception" +id));
		return userMapper.toDto(user);
				
	}
}

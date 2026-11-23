package com.commerce.user_service.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

	private Long id;
	private String name;
	private String email;
	private String phone;
	private String roles;
	private Instant createdAt;
	private Instant updatedAt;
}

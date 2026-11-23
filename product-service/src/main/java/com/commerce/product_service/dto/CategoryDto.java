package com.commerce.product_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

	private Long id;
	@NotBlank(message = "Categorie name not be empt")
	private String name;
	private String description;
	private Long parentId;
	
	
}

package com.commerce.product_service.mapper;

import org.springframework.stereotype.Component;

import com.commerce.product_service.dto.CategoryDto;
import com.commerce.product_service.entity.Category;

@Component
public class CategoryMapper {
		public CategoryDto toDto(Category category) {
			return CategoryDto.builder()
					.id(category.getId())
					.name(category.getName())
					.description(category.getDescription())
					.parentId(category.getParentId())
					.build();
		}
		public Category toEntity(CategoryDto dto) {
			return Category.builder()
					.name(dto.getName())
					.description(dto.getDescription())
					.parentId(dto.getParentId())
					.build();
		}
}
package com.commerce.product_service.dto.service;

import java.util.List;
import com.commerce.product_service.dto.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto dto);
	CategoryDto updateCategory(Long id, CategoryDto dto);
	void deleteCategory(Long id);
	CategoryDto getCategory(Long id);
	List<CategoryDto> getAllCategories();
}

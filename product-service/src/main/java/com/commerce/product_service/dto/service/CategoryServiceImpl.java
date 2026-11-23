package com.commerce.product_service.dto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commerce.product_service.dto.CategoryDto;
import com.commerce.product_service.entity.Category;
import com.commerce.product_service.mapper.CategoryMapper;
import com.commerce.product_service.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private final CategoryRepository categoryRepo;
	@Autowired
	private final CategoryMapper categoryMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto dto) {
		Category category = categoryMapper.toEntity(dto);
		return categoryMapper.toDto(categoryRepo.save(category));
	}

	@Override
	public CategoryDto updateCategory(Long id, CategoryDto dto) {
		
		Category category = categoryRepo.findById(id).orElseThrow(()-> new RuntimeException("category not found"));
		category.setName(dto.getName());
		category.setDescription(dto.getDescription());
		category.setParentId(dto.getParentId());
		return categoryMapper.toDto(categoryRepo.save(category));
	}

	@Override
	public void deleteCategory(Long id) {
		categoryRepo.deleteById(id);
		
	}

	@Override
	public CategoryDto getCategory(Long id) {
		// TODO Auto-generated method stub
		return categoryRepo.findById(id).map(categoryMapper::toDto).orElseThrow(()-> new RuntimeException("Category not found"));
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		// TODO Auto-generated method stub
		return categoryRepo.findAll().stream().map(categoryMapper::toDto).toList();
	}
}

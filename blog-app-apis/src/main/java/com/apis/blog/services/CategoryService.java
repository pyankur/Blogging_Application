package com.apis.blog.services;

import java.util.List;import com.apis.blog.entities.Category;
import com.apis.blog.payloads.CategoryDto;

public interface CategoryService {

	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	//delete
	void deleteCategory(Integer categoryId);
	
	//getAll
	List<CategoryDto> getCategories();
}

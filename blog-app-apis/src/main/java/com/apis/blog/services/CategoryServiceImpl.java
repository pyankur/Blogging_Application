package com.apis.blog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apis.blog.entities.Category;
import com.apis.blog.entities.User;
import com.apis.blog.exceptions.ResourceNotFoundException;
import com.apis.blog.payloads.CategoryDto;
import com.apis.blog.payloads.UserDto;
import com.apis.blog.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category saveCategory=this.categoryRepo.save(dtoToCategory(categoryDto));
		return this.CategoryTodto(saveCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category saveCategory=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
		
		saveCategory.setCategoryTitle(categoryDto.getCategoryTitle());
		saveCategory.setCategoryDesc(categoryDto.getCategoryDesc());
		
		
		Category updatedCategory = this.categoryRepo.save(saveCategory);
		CategoryDto categoryDto1=this.CategoryTodto(updatedCategory);
		return categoryDto1;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
		this.categoryRepo.delete(category);
		
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories=this.categoryRepo.findAll();
		List<CategoryDto> CategoryDtos = categories.stream().map(category->this.CategoryTodto(category)).collect(Collectors.toList());
		return CategoryDtos;
	}
	public Category dtoToCategory(CategoryDto categoryDto){
		Category category=this.modelMapper.map(categoryDto,Category.class);
//		user.setId(userDto.getId());
//		user.setEmail(userDto.getEmail());
//		user.setName(userDto.getName());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		return category;
	}
	public CategoryDto CategoryTodto(Category category){
		CategoryDto categoryDto=this.modelMapper.map(category, CategoryDto.class);
//		userDto.setId(user.getId());
//		userDto.setEmail(user.getEmail());
//		userDto.setName(user.getName());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		return categoryDto;
	}

}

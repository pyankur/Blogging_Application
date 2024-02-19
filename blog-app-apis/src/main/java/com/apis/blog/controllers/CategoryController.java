package com.apis.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apis.blog.payloads.ApiResponse;
import com.apis.blog.payloads.CategoryDto;
import com.apis.blog.payloads.UserDto;
import com.apis.blog.services.CategoryService;
import com.apis.blog.services.UserService;

@Controller
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryservice;
	
	//createUser
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createUser(@RequestBody CategoryDto categoryDto){
//		validateCategoryDto(categoryDto); 
		CategoryDto createCategoryDto=null;
		try {
			createCategoryDto = this.categoryservice.createCategory(categoryDto);
			
		} catch (Exception e) {
			System.out.println("error is "+e);
		}
		return new ResponseEntity<>(createCategoryDto,HttpStatus.CREATED);
	}
	
	//update User
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateUser(@RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
		validateCategoryDto(categoryDto); 
		CategoryDto updatedCategory = this.categoryservice.updateCategory(categoryDto,categoryId);
		return ResponseEntity.ok(updatedCategory);

	}
	
	//DELETE
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer categoryId){
		this.categoryservice.deleteCategory(categoryId);
		return new ResponseEntity<>(new ApiResponse("Category deleted successfully",true),HttpStatus.OK);

	}
	
	//Get all Users
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllUsers(){
		List<CategoryDto> allCategories = this.categoryservice.getCategories();
		return ResponseEntity.ok(allCategories);

	}
	
//	//Get single Users
//	@GetMapping("/{userId}")
//	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
//		UserDto allUser = this.userservice.getUserById(userId);
//		return ResponseEntity.ok(allUser);
//
//	}
	// Helper method to validate UserDto fields
    private void validateCategoryDto(CategoryDto categoryDto) {
        if (categoryDto.getCategoryTitle() == null || categoryDto.getCategoryTitle().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }

        if (categoryDto.getCategoryDesc() == null || categoryDto.getCategoryDesc().isEmpty()) {
            throw new IllegalArgumentException("Description is required");
        }

        

        // Perform other validations if needed
    }
}

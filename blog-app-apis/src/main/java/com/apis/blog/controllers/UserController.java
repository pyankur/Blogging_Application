package com.apis.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apis.blog.payloads.ApiResponse;
import com.apis.blog.payloads.UserDto;
import com.apis.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userservice;
	
	//createUser
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		validateUserDto(userDto); 
		UserDto createUserDto = this.userservice.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	
	//update User
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable Integer userId){
		validateUserDto(userDto); 
		UserDto updatedUser = this.userservice.updateUser(userDto,userId);
		return ResponseEntity.ok(updatedUser);

	}
	
	//DELETE
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		this.userservice.deleteUser(userId);
		return new ResponseEntity<>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);

	}
	
	//Get all Users
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> allUser = this.userservice.getAllUser();
		return ResponseEntity.ok(allUser);

	}
	
	//Get single Users
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
		UserDto allUser = this.userservice.getUserById(userId);
		return ResponseEntity.ok(allUser);

	}
	// Helper method to validate UserDto fields
    private void validateUserDto(UserDto userDto) {
        if (userDto.getName() == null || userDto.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }

        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }

        // Perform other validations if needed
    }
	
}

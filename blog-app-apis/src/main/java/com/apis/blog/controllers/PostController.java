package com.apis.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apis.blog.payloads.ApiResponse;
import com.apis.blog.payloads.PostDto;
import com.apis.blog.payloads.UserDto;
import com.apis.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId){
    	PostDto createPost=this.postService.createPost(postDto, userId, categoryId);
    	return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
    }
	
	//get Post by User
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		List<PostDto> posts = this.postService.getAllPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	//get Post by Category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
		List<PostDto> posts = this.postService.getAllPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	} 
	
	//Get single Users
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getSingleUser(@PathVariable Integer postId){
		PostDto posts = this.postService.getPostById(postId);
		return ResponseEntity.ok(posts);

	}
	//Get all Posts
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPost(){
		List<PostDto> singlePost = this.postService.getAllPost();
		return new ResponseEntity<List<PostDto>>(singlePost, HttpStatus.OK);

	}
	//DELETE
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deleteUser(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ApiResponse("Post deleted successfully",true);

	}
	//update User
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
//		validateUserDto(userDto); 
		PostDto updatedPost = this.postService.updatePost(postDto,postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);

	}
	
}

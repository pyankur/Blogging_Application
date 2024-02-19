package com.apis.blog.services;

import java.util.List;

import com.apis.blog.entities.Post;
import com.apis.blog.payloads.PostDto;

public interface PostService {

	//create Post
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto,Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//all
	List<PostDto> getAllPost();
	
	//get Post
	PostDto getPostById(Integer postId);
	
	//get all posts by category
	List<PostDto> getAllPostByUser(Integer userId);
	
	//get all posts by category
    List<PostDto> getAllPostByCategory(Integer categoryId);

    //search Posts
    List<Post> searchPost(String keyword);
}

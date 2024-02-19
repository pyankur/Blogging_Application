package com.apis.blog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apis.blog.entities.Category;
import com.apis.blog.entities.Post;
import com.apis.blog.entities.User;
import com.apis.blog.exceptions.ResourceNotFoundException;
import com.apis.blog.payloads.PostDto;
import com.apis.blog.payloads.UserDto;
import com.apis.blog.repositories.CategoryRepository;
import com.apis.blog.repositories.PostRepository;
import com.apis.blog.repositories.UserRepo;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
		
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		User user=this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User id", userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category id", categoryId));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate("Present date");
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost=this.postRepo.save(post);
	
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto,Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		
		Post updatedPost = this.postRepo.save(post);
		PostDto postDto1=this.PostTodto(updatedPost);
		return postDto1;
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
		System.out.println(post);
		this.postRepo.delete(post);
		
	}
	
	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
		PostDto postDto=this.PostTodto(post);
		return postDto;
	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post> posts=this.postRepo.findAll();
		System.out.println(posts);
		List<PostDto> postDtos = posts.stream().map(post->this.PostTodto(post)).collect(Collectors.toList());
		System.out.println(postDtos);	
		return postDtos;
		
	}
    
	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		User user=this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
//		System.out.println("user"+user);
		List<Post> posts=this.postRepo.findByUser(user);
		System.out.println("posts"+posts);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		System.out.println("postDtos"+postDtos);
		return postDtos;
	}

	@Override
	public List<PostDto> getAllPostByCategory(Integer categoryId) {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
		List<Post> posts=this.postRepo.findByCategory(category);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<Post> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Post dtoToPost(PostDto postDto){
		Post post=this.modelMapper.map(postDto,Post.class);
//		user.setId(userDto.getId());
//		user.setEmail(userDto.getEmail());
//		user.setName(userDto.getName());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		return post;
	}
	public PostDto PostTodto(Post post){
		PostDto postDto=this.modelMapper.map(post, PostDto.class);
//		userDto.setId(user.getId());
//		userDto.setEmail(user.getEmail());
//		userDto.setName(user.getName());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		return postDto;
	}

	

	
}

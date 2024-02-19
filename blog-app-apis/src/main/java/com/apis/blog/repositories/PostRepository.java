package com.apis.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apis.blog.entities.Category;
import com.apis.blog.entities.Post;
import com.apis.blog.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer>{

//	@Query(value = "select * from posts where user_id = :userId", nativeQuery = true)
//	List<Post> findByUser(@Param("userId") int userId);
//	@Query(value = "SELECT * FROM posts p JOIN users u ON p.user_id=u.id WHERE u.id = :userId", nativeQuery = true)
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
}

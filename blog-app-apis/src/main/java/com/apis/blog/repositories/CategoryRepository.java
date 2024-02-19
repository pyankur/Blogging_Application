package com.apis.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apis.blog.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}

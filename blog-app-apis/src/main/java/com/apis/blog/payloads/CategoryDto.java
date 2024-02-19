package com.apis.blog.payloads;



public class CategoryDto {
	
	private Integer categoryId;
	private String categoryTitle;
	private String categoryDesc;
	public CategoryDto(Integer categoryId, String categoryTitle, String categoryDesc) {
		super();
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.categoryDesc = categoryDesc;
	}
	
	public CategoryDto() {
		super();
	}

	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
}

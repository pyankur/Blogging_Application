package com.apis.blog.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	String 	resourceName;
	String fieldName;
	long fieldVaue;
	
	
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldVaue) {
		super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldVaue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldVaue = fieldVaue;
	}


	public String getResourceName() {
		return resourceName;
	}


	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}


	public String getFieldName() {
		return fieldName;
	}


	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}


	public long getFieldVaue() {
		return fieldVaue;
	}


	public void setFieldVaue(long fieldVaue) {
		this.fieldVaue = fieldVaue;
	}
	
	
	
}

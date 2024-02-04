package com.blog.exceptions;

public class ResouceNotFoundException extends RuntimeException {
	String resouceName;
	String fieldName;
	long fieldValue;
	public ResouceNotFoundException(String resouceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s: %s",resouceName,fieldName,fieldValue));
		this.resouceName = resouceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	

}

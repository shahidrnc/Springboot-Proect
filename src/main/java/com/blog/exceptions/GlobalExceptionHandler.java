package com.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.payloads.Apiresponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResouceNotFoundException.class)
	public ResponseEntity<Apiresponse> resourceNotFoundException(ResouceNotFoundException ex){
		String Message=ex.getMessage();
		Apiresponse apiResponse=new Apiresponse(Message,false);
		return new ResponseEntity<Apiresponse>(apiResponse,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodargsNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> resp=new HashMap<>();
	 ex.getBindingResult().getAllErrors().forEach((error)->{
		 String fieldName=((FieldError)error).getField();
		 String message=error.getDefaultMessage();
		 resp.put(fieldName,message);
	 });
	 
	 return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
			
		
	}

}

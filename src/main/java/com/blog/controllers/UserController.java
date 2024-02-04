package com.blog.controllers;

import java.util.List;

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

import com.blog.payloads.Apiresponse;
import com.blog.payloads.userDto;
import com.blog.services.UserService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<userDto> createUser(@Valid @RequestBody userDto UserDt){
		userDto createUserDto=this.userService.createUser(UserDt);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
		
		
	}
	@PutMapping("/{userId}")
	public ResponseEntity<userDto> updateUser(@Valid @RequestBody userDto userDt,@PathVariable("userId") Integer uId){
		userDto updatedUser=this.userService.updateUser(userDt, uId);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Apiresponse> deleteUser(@PathVariable("userId") Integer uid){
		this.userService.deleteUser(uid);
		return new ResponseEntity<Apiresponse>(new Apiresponse("User deleted Successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<userDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
    @GetMapping("/{userId}")
    public ResponseEntity<userDto> getSingleUser(@PathVariable Integer userId){
    	return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}

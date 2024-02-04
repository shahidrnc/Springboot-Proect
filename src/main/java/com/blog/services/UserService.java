package com.blog.services;

import java.util.List;

import com.blog.payloads.userDto;

public interface UserService {
	
	userDto createUser(userDto user);
	userDto updateUser(userDto user,Integer id);
	userDto getUserById(Integer userId);
	List<userDto> getAllUsers();
	void deleteUser(Integer userId);
	

}

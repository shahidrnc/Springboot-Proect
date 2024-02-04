package com.blog.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.exceptions.ResouceNotFoundException;
import com.blog.payloads.userDto;
import com.blog.repositories.UserRepo;
import com.blog.services.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	public ModelMapper modelMapper;

	@Override
	public userDto createUser(userDto UserDto) {
		User user=this.DtoToUser(UserDto);
		User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
		
	}

	@Override
	public userDto updateUser(userDto UserDto, Integer userId) {
		User user=this.userRepo.findById(userId)
				.orElseThrow(()->new ResouceNotFoundException("User","id",userId));
		user.setName(UserDto.getName());
		user.setEmail(UserDto.getEmail());
		user.setPassword(UserDto.getPassword());
		user.setAbout(UserDto.getAbout());
		
		User updatedUser=this.userRepo.save(user);
		userDto userDto1=this.userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public userDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId).
				orElseThrow(()->new ResouceNotFoundException("User", "id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<userDto> getAllUsers() {
		List<User> users=this.userRepo.findAll();
		List<userDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).
				orElseThrow(()->new ResouceNotFoundException("User","id", userId));
		this.userRepo.delete(user);
		

	}
   
	private User DtoToUser(userDto UserDto) {
		User user=this.modelMapper.map(UserDto, User.class);
//		User user=new User();
//		user.setId(UserDto.getId());
//		user.setName(UserDto.getName());
//		user.setEmail(UserDto.getEmail());
//		user.setPassword(UserDto.getPassword());
//		user.setAbout(UserDto.getAbout());
		return user;
		
	}
	private userDto userToDto(User user) {
		userDto UserDto=this.modelMapper.map(user, userDto.class);
//		userDto UserDto=new userDto();
//		UserDto.setId(user.getId());
//		UserDto.setName(user.getName());
//		UserDto.setEmail(user.getEmail());
//		UserDto.setPassword(user.getPassword());
//		UserDto.setAbout(user.getAbout());
	return UserDto;
		
	}
}

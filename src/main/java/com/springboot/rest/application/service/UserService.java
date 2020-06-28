package com.springboot.rest.application.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.springboot.rest.application.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	
	public UserDto createUser(UserDto user);
	public UserDto getUser(String  email);
	public UserDto getUserByUserId(String id);
	public UserDto updateUser(UserDto user, String id);
	public void deleteUser(String id);
	public List<UserDto> getUsers(int page, int limit);

}

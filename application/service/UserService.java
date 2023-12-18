package com.blog.application.service;

import java.util.List;

import com.blog.application.payloads.UserDto;

public interface UserService {

	UserDto registerNewUser(UserDto user);

	UserDto updateUser(UserDto user, Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers();

	void deleteUser(Integer userId);

}

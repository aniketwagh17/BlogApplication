package com.blog.application.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.application.payloads.ApiResponse;
import com.blog.application.payloads.UserDto;
import com.blog.application.service.UserService;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 
	 * @param userDto
	 * @param uid
	 * @return
	 */
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
	}

	/**
	 * 
	 * @param uid
	 * @return
	 */

	@DeleteMapping("/deleteUserById/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/getAllUser")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> userDto = this.userService.getAllUsers();

		return new ResponseEntity<List<UserDto>>(userDto, HttpStatus.OK);
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */

	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
		UserDto userDto = this.userService.getUserById(userId);

		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}

}

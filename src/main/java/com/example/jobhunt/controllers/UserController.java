package com.example.jobhunt.controllers;


import com.example.jobhunt.Dto.UserRequestDto;
import com.example.jobhunt.Dto.UserResponseDto;
import com.example.jobhunt.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService){
		this.userService = userService;
	}
	
	
	@GetMapping("/")
	public String health(){
		return "okk";
	}
	
	// Register user (POST /users)
	@PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserResponseDto>  registeruser(@Valid @RequestBody UserRequestDto user){
		return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
	
	}
}

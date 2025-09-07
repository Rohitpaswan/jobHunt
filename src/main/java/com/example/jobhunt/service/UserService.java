package com.example.jobhunt.service;


import com.example.jobhunt.Dto.UserRequestDto;
import com.example.jobhunt.Dto.UserResponseDto;
import com.example.jobhunt.entity.User;

public interface UserService {
	public UserResponseDto registerUser(UserRequestDto userRequest);
	
}

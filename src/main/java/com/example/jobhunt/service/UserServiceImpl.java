package com.example.jobhunt.service;

import com.example.jobhunt.Dto.UserRequestDto;
import com.example.jobhunt.Dto.UserResponseDto;
import com.example.jobhunt.entity.User;
import com.example.jobhunt.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service

public class UserServiceImpl implements UserService{
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	
	public UserServiceImpl(UserRepository userRepositorty, ModelMapper modelMapper){
		this.userRepository = userRepositorty;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public UserResponseDto registerUser(UserRequestDto userRequest) {
		log.info("Registering new user with email: {}", userRequest.getEmail());
		
		User user = modelMapper.map(userRequest, User.class);
		User savedUser = userRepository.save(user);
		
		log.debug("User saved with ID: {}", savedUser.getId());
		
		return modelMapper.map(savedUser, UserResponseDto.class);
	}
	
}

package com.example.jobhunt.service;

import com.example.jobhunt.Dto.UserRequestDto;
import com.example.jobhunt.Dto.UserResponseDto;
import com.example.jobhunt.entity.User;
import com.example.jobhunt.exception.EmailAlreadyExistsException;
import com.example.jobhunt.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class UserServiceImpl implements UserService{
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepositorty, ModelMapper modelMapper, PasswordEncoder passwordEncoder){
		this.userRepository = userRepositorty;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public UserResponseDto registerUser(UserRequestDto userRequest) {
		//check user is already exist
		if(userRepository.existsByEmail(userRequest.getEmail())){
			 throw new EmailAlreadyExistsException("user already exists");
		}
		log.info("Registering new user with email: {}", userRequest.getEmail());
		userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		
		User user = modelMapper.map(userRequest, User.class);
		User savedUser = userRepository.save(user);
		
		return modelMapper.map(savedUser, UserResponseDto.class);
	}
	
}

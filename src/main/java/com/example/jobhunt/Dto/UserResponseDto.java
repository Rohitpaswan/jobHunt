package com.example.jobhunt.Dto;

import com.example.jobhunt.entity.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
	private String name;
	private String email;
	private AccountType accountType;
	private Instant createdAt;
	private Instant updatedAt;
}

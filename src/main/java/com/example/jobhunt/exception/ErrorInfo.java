package com.example.jobhunt.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorInfo {
	private String erroMessage;
	private int errorCode;
	private LocalDateTime timestamp;
}

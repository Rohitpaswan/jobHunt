package com.example.jobhunt.utils;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorInfo {
	private String erroMessage;
	private String errorCode;
	private LocalDateTime timestamp;
}

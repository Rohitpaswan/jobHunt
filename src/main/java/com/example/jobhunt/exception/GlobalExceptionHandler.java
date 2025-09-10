package com.example.jobhunt.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * Handle validation errors for @Valid request bodies (DTOs).
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.collect(Collectors.toMap(
						fieldError -> fieldError.getField(),
						fieldError -> fieldError.getDefaultMessage(),
						(msg1, msg2) -> msg1 + "; " + msg2 // merge messages if multiple errors for same field
				));
		
		return ResponseEntity.badRequest().body(errors);
	}
	
	
	/**
	 * Handle validation errors for @Validated parameters (e.g. @RequestParam, @PathVariable).
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, String>> handleConstraintViolation(ConstraintViolationException ex) {
		Map<String, String> errors = ex.getConstraintViolations()
				.stream()
				.collect(Collectors.toMap(
						violation -> violation.getPropertyPath().toString(),
						violation -> violation.getMessage(),
						(msg1, msg2) -> msg1 + "; " + msg2 // merge messages if multiple errors for same param
				));
		
		return ResponseEntity.badRequest().body(errors);
	}
	
	/**
	 * Handle all uncaught exceptions (fallback).
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> handleGeneralException(Exception exception) {
		ErrorInfo errorInfo = new ErrorInfo(
				exception.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				LocalDateTime.now()
		);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorInfo);
	}
}

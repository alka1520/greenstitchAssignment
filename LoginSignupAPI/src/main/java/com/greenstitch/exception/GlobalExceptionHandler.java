package com.greenstitch.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException mp){
		
		MyErrorDetails err= new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage("Validation error");
		err.setDetails(mp.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> noHandlerFoundExceptionHandler(NoHandlerFoundException nfe,WebRequest req)  {
			
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(nfe.getMessage());
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
					
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> userExceptionHandler(UserException ce, WebRequest req){
		
		MyErrorDetails err= new MyErrorDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(ce.getMessage());
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> otherExceptionHandler(Exception se, WebRequest req){
		
		MyErrorDetails err= new MyErrorDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(se.getMessage());
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	
	
}

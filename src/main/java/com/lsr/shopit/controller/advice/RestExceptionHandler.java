package com.lsr.shopit.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.lsr.shopit.exceptions.BadRequestException;
import com.lsr.shopit.exceptions.DataConflictException;
import com.lsr.shopit.exceptions.DataNotFoundException;
import com.lsr.shopit.models.ResponseWrapper;


@ControllerAdvice
public class RestExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	ResponseWrapper<Void> validationError(MethodArgumentNotValidException ex) {
		ResponseWrapper<Void> errorResponse = new ResponseWrapper<>();
		errorResponse.setMessage(ex.getBindingResult().getFieldError().getDefaultMessage());
		errorResponse.setSuccess(false);
		return errorResponse;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ MissingServletRequestParameterException.class, BadRequestException.class })
	@ResponseBody
	ResponseWrapper<Void> badRequest(Exception ex) {
		ResponseWrapper<Void> errorResponse = new ResponseWrapper<>();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setSuccess(false);
		return errorResponse;
	}
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(DataConflictException.class)
	@ResponseBody
	ResponseWrapper<Void> dataConflict(Exception ex) {
		ResponseWrapper<Void> errorResponse = new ResponseWrapper<>();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setSuccess(false);
		return errorResponse;
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(DataNotFoundException.class)
	@ResponseBody
	ResponseWrapper<Void> dataNotFound(Exception ex) {
		ResponseWrapper<Void> errorResponse = new ResponseWrapper<>();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setSuccess(false);
		return errorResponse;
	}
}

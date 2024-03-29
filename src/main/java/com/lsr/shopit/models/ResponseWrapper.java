package com.lsr.shopit.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class ResponseWrapper<T> {
	private PageDetails pageDetails;
	private T data;
	private String message;
	private boolean success;
}

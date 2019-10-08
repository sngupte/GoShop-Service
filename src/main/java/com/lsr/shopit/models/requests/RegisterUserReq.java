package com.lsr.shopit.models.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterUserReq {
	@Email(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Please provide valid email Id")
	@NotNull(message = "Email id is required")
	@Size(min = 1, message = "Email id is required")
	private String emailId;
	@NotNull(message = "first name is required")
	@Size(min = 1, message = "first name is required")
	private String firstName;
	@NotNull(message = "last name is required")
	@Size(min = 1, message = "last name is required")
	private String lastName;
	@NotNull(message = "phone number is required")
	@Size(min = 1, message = "last name is required")
	@Pattern(regexp="\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}")
	private String phoneNumber;
	@NotNull(message = "country is required")
	@Size(min = 1, message = "country is required")
	private String country;
	@NotNull(message = "country is required")
	@Size(min = 1, message = "country is required")
	private String city;
	@NotNull(message = "country is required")
	@Size(min = 1, message = "country is required")
	private String password;
}

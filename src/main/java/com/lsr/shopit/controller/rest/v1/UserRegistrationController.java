package com.lsr.shopit.controller.rest.v1;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lsr.shopit.entities.User;
import com.lsr.shopit.models.ResponseWrapper;
import com.lsr.shopit.models.requests.LoginReq;
import com.lsr.shopit.models.requests.RegisterUserReq;
import com.lsr.shopit.service.impl.UserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/shop")
public class UserRegistrationController {

	@Autowired
	private UserDetailsService userDetailsService;

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public ResponseWrapper<Void> registerUser(@RequestBody @Valid User user) {
		log.info("request recieved to register user {}", user);
		return userDetailsService.registerUser(user);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseWrapper<User> login(@RequestBody @Valid LoginReq loginReq) {
		log.info("request recieved to login user {}", loginReq);
		return userDetailsService.login(loginReq);
	}
}

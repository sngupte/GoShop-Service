package com.lsr.shopit.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsr.shopit.entities.User;
import com.lsr.shopit.exceptions.BadRequestException;
import com.lsr.shopit.exceptions.DataConflictException;
import com.lsr.shopit.exceptions.DataNotFoundException;
import com.lsr.shopit.models.ResponseWrapper;
import com.lsr.shopit.models.requests.LoginReq;
import com.lsr.shopit.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsService {

	@Autowired
	UserRepository userRepository;

	public ResponseWrapper<Void> registerUser(User user) {
		log.info("Registering User {}", user);

		validateRegisterUser(user);

		userRepository.save(user);

		ResponseWrapper<Void> wrapper = new ResponseWrapper<>();
		wrapper.setSuccess(true);
		wrapper.setMessage("Successfully registered user");
		return wrapper;
	}

	private void validateRegisterUser(User user) {

		// Check for the duplicate entry
		Optional<User> resultOpt = userRepository.findByEmail(user.getEmail());
		if (resultOpt.isPresent()) {
			throw new DataConflictException("User with email id: " + user.getEmail() + " already present");
		}
	}

	public ResponseWrapper<User> login(LoginReq login) {
		log.info("login :: {}", login);

		Optional<User> userOpt = userRepository.findByUsernameOrEmail(login.getEmail());

		if (!userOpt.isPresent()) {
			throw new BadRequestException("Invalid Username.");
		}

		User user = userOpt.get();

		if (!user.getPassword().equals(login.getPassword())) {
			throw new BadRequestException("Password does not match");
		}

		ResponseWrapper<User> wrapper = new ResponseWrapper<>();
		wrapper.setData(user);
		wrapper.setMessage("Successfully logged in");
		wrapper.setSuccess(true);

		return wrapper;
	}

}

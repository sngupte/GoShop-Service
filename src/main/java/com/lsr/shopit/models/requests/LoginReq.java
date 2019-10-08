package com.lsr.shopit.models.requests;

import lombok.Data;

@Data
public class LoginReq {
	private String email;
	private String password;
}

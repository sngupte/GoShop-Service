package com.lsr.shopit.controller.rest.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shop")
public class PingController {

	@GetMapping("/ping")
	public String ping() {
		return "ShopIT service is running succefully";
	}
}

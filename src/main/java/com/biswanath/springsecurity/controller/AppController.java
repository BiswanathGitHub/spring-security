package com.biswanath.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biswanath.springsecurity.service.UserService;

@RestController
@RequestMapping("/api")
public class AppController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/hello-world")
	public String helloWorld() {
		userService.createAndSaveUsers();
		return "Hello World !";
	}

}

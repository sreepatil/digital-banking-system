package com.exam.sec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.sec.dto.LoginRequest;
import com.exam.sec.service.PublicService;

@RestController
public class PublicController {

	@Autowired
	PublicService publicService;

	@PostMapping("/api/users/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		return publicService.login(loginRequest);
	}

	@GetMapping("/api/models")
	public ResponseEntity<?> getAllActiveModels() {
		return publicService.getAllActiveModels();
	}

}

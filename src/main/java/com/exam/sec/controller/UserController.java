package com.exam.sec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.sec.model.PromptSubmission;
import com.exam.sec.model.User;
import com.exam.sec.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserDetailsService userDetailsService;

	@PostMapping("/api/prompts")
	public ResponseEntity<?> savePrompt(@RequestBody PromptSubmission prompt, Authentication authentication) {
		User user = getUserFromAuth(authentication);
		return userService.savePrompt(prompt, user);
	}

	@GetMapping("/api/prompts/my")
	public ResponseEntity<?> getMyPromptHistory(Authentication authentication) {
		User user = getUserFromAuth(authentication);
		return userService.getMyPromptHistory(user);
	}

	private User getUserFromAuth(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		user = (User) userDetailsService.loadUserByUsername(user.getUsername());
		return user;
	}

}

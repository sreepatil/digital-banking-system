package com.exam.sec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.exam.sec.model.User;
import com.exam.sec.service.CommonService;

@RestController
public class CommonController {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	CommonService commonService;

	@GetMapping("/api/prompts/{submissionId}")
	public ResponseEntity<?> viewPromptById(@PathVariable int submissionId, Authentication authentication) {
		User user = getUserFromAuth(authentication);
		return commonService.viewPromptById(user, submissionId);
	}

	private User getUserFromAuth(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		user = (User) userDetailsService.loadUserByUsername(user.getUsername());
		return user;
	}

}

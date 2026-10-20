package com.exam.sec.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.exam.sec.model.PromptSubmission;
import com.exam.sec.model.User;
import com.exam.sec.repo.PromptRepo;

@Service
public class CommonService {

	@Autowired
	PromptRepo promptRepo;

	public ResponseEntity<?> viewPromptById(User user, int submissionId) {
		Map<String, String> errorResponse = new HashMap<>();
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		boolean isAdmin = false;
		for (GrantedAuthority authority : authorities) {
			if (authority.getAuthority().equals("ADMIN")) {
				isAdmin = true;
			}
		}
		Optional<PromptSubmission> promptOptn = promptRepo.findById(submissionId);
		if (promptOptn.isEmpty()) {
			errorResponse.put("error", "Prompt not found.");
			return ResponseEntity.status(404).body(errorResponse);
		}
		PromptSubmission prompt = promptOptn.get();
		if (isAdmin) {
			return ResponseEntity.status(200).body(prompt);
		} else if (prompt.getUserId() != user.getUserId()) {
			// Error
			errorResponse.put("error", "You are not allowed to view this prompt.");
			return ResponseEntity.status(403).body(errorResponse);
		} else {
			// All good user can access
			return ResponseEntity.status(200).body(prompt);
		}
	}

}

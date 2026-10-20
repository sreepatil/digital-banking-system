package com.exam.sec.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exam.sec.model.Model;
import com.exam.sec.model.PromptSubmission;
import com.exam.sec.model.User;
import com.exam.sec.repo.ModelRepo;
import com.exam.sec.repo.PromptRepo;

@Service
public class UserService {

	@Autowired
	PromptRepo promptRepo;

	@Autowired
	ModelRepo modelRepo;

	public ResponseEntity<?> savePrompt(PromptSubmission prompt, User user) {
		Map<String, String> errorResponse = new HashMap<>();
		Optional<Model> modelOptn = modelRepo.findById(prompt.getModelId());
		if (modelOptn.isEmpty()) {
			errorResponse.put("error", "Model not found.");
			return ResponseEntity.status(404).body(errorResponse);
		}
		Model model = modelOptn.get();
		if (!model.isActive()) {
			errorResponse.put("error", "Model is inactive.");
			return ResponseEntity.status(403).body(errorResponse);
		}
		prompt.setUserId(user.getUserId());
		prompt.setSubmittedAt(LocalDateTime.now());
		PromptSubmission savedPrompt = promptRepo.save(prompt);
		return ResponseEntity.status(201).body(savedPrompt);
	}

	public ResponseEntity<?> getMyPromptHistory(User user) {
		List<PromptSubmission> userPrompts = promptRepo.findByUserId(user.getUserId());
		return ResponseEntity.status(200).body(userPrompts);
	}

}

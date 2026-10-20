package com.exam.sec.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exam.sec.dto.LoginRequest;
import com.exam.sec.dto.LoginResponse;
import com.exam.sec.model.Model;
import com.exam.sec.model.User;
import com.exam.sec.repo.ModelRepo;
import com.exam.sec.repo.UserRepo;

@Service
public class PublicService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	JwtService jwtService;

	@Autowired
	ModelRepo modelRepo;

	public ResponseEntity<?> login(LoginRequest loginRequest) {
		Optional<User> userOptn = userRepo.findByUsername(loginRequest.getUsername());
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", "Invalid username or password.");
		if (userOptn.isEmpty()) {
			return ResponseEntity.status(401).body(errorResponse);
		}
		User user = userOptn.get();
		if (!user.getPassword().equals(loginRequest.getPassword())) {
			return ResponseEntity.status(401).body(errorResponse);
		}
		String token = jwtService.generateToken(user);
		LoginResponse loginResponse = new LoginResponse(token, user.getUsername(), user.getRoles());
		return ResponseEntity.status(200).body(loginResponse);
	}

	public ResponseEntity<?> getAllActiveModels() {
		List<Model> activeModels = modelRepo.findByIsActive(true);
		return ResponseEntity.status(200).body(activeModels);
	}

}

package com.exam.sec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.sec.model.Model;
import com.exam.sec.service.AdminService;

import jakarta.validation.Valid;

@RestController
public class AdminController {

	@Autowired
	AdminService adminService;

	@PostMapping("/api/admin/models")
	public ResponseEntity<?> saveNewModel(@RequestBody @Valid Model model) {
		return adminService.saveNewModel(model);
	}

	@PutMapping("/api/admin/models/{modelId}")
	public ResponseEntity<?> updateModel(@PathVariable int modelId, @RequestBody Model model) {
		return adminService.updateModel(modelId, model);
	}

	@PutMapping("/api/admin/models/{modelId}/deactivate")
	public ResponseEntity<?> putMethodName(@PathVariable int modelId) {
		return adminService.deactivateModel(modelId);
	}

	@GetMapping("/api/admin/prompts")
	public ResponseEntity<?> viewAllSubmissions() {
		return adminService.viewAllSubmissions();
	}

	@GetMapping("/api/admin/stats")
	public ResponseEntity<?> getAdminStats() {
		return adminService.getAdminStats();
	}

	@DeleteMapping("/api/admin/prompts/{submissionId}")
	public ResponseEntity<?> deletePromptById(@PathVariable int submissionId) {
		return adminService.deletePromptById(submissionId);
	}

}

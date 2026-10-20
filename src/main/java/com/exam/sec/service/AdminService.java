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
import com.exam.sec.repo.ModelRepo;
import com.exam.sec.repo.PromptRepo;

@Service
public class AdminService {

	@Autowired
	ModelRepo modelRepo;

	@Autowired
	PromptRepo promptRepo;

	public ResponseEntity<?> saveNewModel(Model model) {
		Map<String, Object> response = new HashMap<>();
		model.setActive(true);
		model.setCreatedAt(LocalDateTime.now());
		Model savedModel = modelRepo.save(model);
		response.put("modelId", savedModel.getModelId());
		response.put("message", "Model added successfully.");
		return ResponseEntity.status(201).body(response);
	}

	public ResponseEntity<?> updateModel(int modelId, Model model) {
		Map<String, String> response = new HashMap<>();
		Optional<Model> modelOptn = modelRepo.findById(modelId);
		if (modelOptn.isEmpty()) {
			response.put("error", "Model not found.");
			return ResponseEntity.status(404).body(response);
		}
		Model modelDB = modelOptn.get();

		modelDB.setDescription(model.getDescription());
		modelDB.setVersion(model.getVersion());
		modelRepo.save(modelDB);
		response.put("message", "Model updated successfully.");
		return ResponseEntity.status(200).body(response);
	}

	public ResponseEntity<?> deactivateModel(int modelId) {
		Map<String, String> response = new HashMap<>();
		Optional<Model> modelOptn = modelRepo.findById(modelId);
		if (modelOptn.isEmpty()) {
			response.put("error", "Model not found.");
			return ResponseEntity.status(404).body(response);
		}
		Model modelDB = modelOptn.get();
		modelDB.setActive(false);
		modelRepo.save(modelDB);
		response.put("message", "Model marked as inactive.");
		return ResponseEntity.status(200).body(response);
	}

	public ResponseEntity<?> viewAllSubmissions() {
		List<PromptSubmission> allPrompts = promptRepo.findAll();
		return ResponseEntity.status(200).body(allPrompts);
	}

	public ResponseEntity<?> getAdminStats() {
		Map<String, Object> response = new HashMap<>();
		long totalPrompts = promptRepo.count();

		Object[] mostUsedModelDetails = (Object[]) promptRepo.getMostUsedModel()[0];
		int modelId = (int) mostUsedModelDetails[1];
		Model model = modelRepo.findById(modelId).get();
		double averagePromptLength = promptRepo.averagePromptLength();
		response.put("totalPrompts", totalPrompts);
		response.put("mostUsedModel", model.getName());
		response.put("averagePromptLength", averagePromptLength);
		return ResponseEntity.status(200).body(response);
	}

	public ResponseEntity<?> deletePromptById(int submissionId) {
		Map<String, String> response = new HashMap<>();
		Optional<PromptSubmission> promptOptn = promptRepo.findById(submissionId);
		if (promptOptn.isEmpty()) {
			response.put("error", "Prompt not found.");
			return ResponseEntity.status(404).body(response);
		}
		PromptSubmission prompt = promptOptn.get();
		promptRepo.delete(prompt);
		return ResponseEntity.status(204).body(null);
	}

}

package com.exam.sec.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exam.sec.model.PromptSubmission;
import java.util.List;

@Repository
public interface PromptRepo extends JpaRepository<PromptSubmission, Integer> {

	List<PromptSubmission> findByUserId(int userId);

	@Query("SELECT count(modelId) as model_count, modelId FROM PromptSubmission ORDER BY model_count DESC LIMIT 1")
	Object[] getMostUsedModel();

	@Query("SELECT AVG(LENGTH(promptText)) FROM PromptSubmission")
	double averagePromptLength();

}

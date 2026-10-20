package com.exam.sec.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PromptSubmission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int submissionId;

	@Column
	private int modelId;

	@Column
	private int userId;

	@Column
	private String promptText;

	@Column
	private LocalDateTime submittedAt;

	public int getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(int submissionId) {
		this.submissionId = submissionId;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPromptText() {
		return promptText;
	}

	public void setPromptText(String promptText) {
		this.promptText = promptText;
	}

	public LocalDateTime getSubmittedAt() {
		return submittedAt;
	}

	public void setSubmittedAt(LocalDateTime submittedAt) {
		this.submittedAt = submittedAt;
	}

	@Override
	public String toString() {
		return "PromptSubmission [submissionId=" + submissionId + ", modelId=" + modelId + ", userId=" + userId
				+ ", promptText=" + promptText + ", submittedAt=" + submittedAt + "]";
	}

}

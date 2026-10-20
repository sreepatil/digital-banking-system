package com.exam.sec.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "_models")
public class Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int modelId;

	@Column
	@NotEmpty
	private String name; // e.g., GPT-4, Gemini 1

	@Column
	@NotEmpty
	private String provider; // e.g., OpenAI, Google

	@Column
	@NotEmpty
	private String version; // e.g., v4, v1.0

	@Column
	@NotEmpty
	private String description;

	@Column
	private boolean isActive;

	@Column
	private LocalDateTime createdAt;

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Model [modelId=" + modelId + ", name=" + name + ", provider=" + provider + ", version=" + version
				+ ", description=" + description + ", isActive=" + isActive + ", createdAt=" + createdAt + "]";
	}

}

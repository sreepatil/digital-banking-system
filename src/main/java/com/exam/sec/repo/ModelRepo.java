package com.exam.sec.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.sec.model.Model;
import java.util.List;

@Repository
public interface ModelRepo extends JpaRepository<Model, Integer> {

	List<Model> findByIsActive(boolean active);
}

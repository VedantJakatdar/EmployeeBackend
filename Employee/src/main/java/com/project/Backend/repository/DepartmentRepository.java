package com.project.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Backend.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

	Department findByname(String name);
	
}

package com.project.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Backend.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

	Department findByname(String name);
	
}

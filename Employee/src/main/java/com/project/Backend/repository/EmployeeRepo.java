package com.project.Backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Backend.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	
	public List<Employee> findByDepartmentName(String department);
	
	public List<Employee> findByPositionTitle(String position);
}

package com.project.Backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;

import com.project.Backend.model.Position;
import com.project.Backend.model.Department;
import com.project.Backend.model.Employee;
import com.project.Backend.repository.DepartmentRepository;
import com.project.Backend.repository.EmployeeRepository;
import com.project.Backend.repository.PositionRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private PositionRepository positionRepository;
	
	public List<Employee> viewAllEmployees() {
		return employeeRepository.findAll();
	}
	
	public Employee viewEmployee(long postId) {
		return employeeRepository.findById(postId).orElse(null);
	}
	
	public Employee addEmployee(Employee employee) {
		   		
		Department existingDepartment = departmentRepository.findByname(employee.getDepartment().getName());
		        
				if (existingDepartment != null) { 
		            employee.setDepartment(existingDepartment);
		        } else {
		            Department newDepartment = employee.getDepartment();
		            employee.setDepartment(newDepartment);
		        }
		        
		Position existingPosition = positionRepository.findBytitle(employee.getPosition().getTitle());
		        
				if (existingPosition != null) {
		            employee.setPosition(existingPosition);
		        } else {
		            Position newPosition = employee.getPosition();
		            employee.setPosition(newPosition);
		        }
		        
		        return	employeeRepository.save(employee);
	       
	}
	
	public Employee updateEmployee(Employee employee,long id) {
		
		Employee employee1 = employeeRepository.findById(id).orElse(null);
        
		if (employee1 != null) {
            // Update basic employee information
            employee1.setFirstName(employee.getFirstName());
            employee1.setLastName(employee.getLastName());
            employee1.setEmailId(employee.getEmailId());
            
            Department existingDepartment = departmentRepository.findByname(employee.getDepartment().getName());
	        if (existingDepartment != null) { 
	            employee1.setDepartment(existingDepartment);
	        } else {
	            Department newDepartment = employee.getDepartment();
	            employee1.setDepartment(newDepartment);
	        }
	        
	        Position existingPosition = positionRepository.findBytitle(employee.getPosition().getTitle());
	        if (existingPosition != null) {
	            employee1.setPosition(existingPosition);
	        } else {
	            Position newPosition = employee.getPosition();
	            employee1.setPosition(newPosition);
	        }

            return employeeRepository.save(employee1);
        } 
		else {   	
            return null;
        }
		
	}
		
	public void deleteEmployee(long postId) {
		employeeRepository.deleteById(postId);
	}
	
	public List<Employee> searchByDepartmentName(String department) {
		return employeeRepository.findByDepartmentName(department);
	}

	public List<Employee> searchByPositionTitle(String position) {
		return employeeRepository.findByPositionTitle(position);
	}	
	
	public List<String> getAllDepartmentNames() {
		List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(Department::getName).collect(Collectors.toList());
	}

	public List<String> getAllPositionNames() {
		List<Position> positions = positionRepository.findAll();
        return positions.stream().map(Position::getTitle).collect(Collectors.toList());
	}
	
}

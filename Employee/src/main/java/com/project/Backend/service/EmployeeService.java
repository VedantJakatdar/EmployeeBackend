package com.project.Backend.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;

import com.project.Backend.model.Position;
import com.project.Backend.model.Department;
import com.project.Backend.model.Employee;
import com.project.Backend.repository.DepartmentRepository;
import com.project.Backend.repository.EmployeeRepo;
import com.project.Backend.repository.PositionRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo repo;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private PositionRepository positionRepository;
	
	public List<Employee> viewAllEmployees() {
		return repo.findAll();
	}
	
	public Employee addEmployee(Employee employee) {
		   
//			Department existingDepartment = departmentRepository.findById(employee.getDepartment().getId()).orElse(null);
			Department existingDepartment = departmentRepository.findByname(employee.getDepartment().getName());
	        if (existingDepartment != null) { 
	            employee.setDepartment(existingDepartment);
	        } else {
	            Department newDepartment = employee.getDepartment();
	            employee.setDepartment(newDepartment);
	        }

//	        Position existingPosition = positionRepository.findById(employee.getPosition().getId()).orElse(null);
	        Position existingPosition = positionRepository.findBytitle(employee.getPosition().getTitle());
	        if (existingPosition != null) {
	            employee.setPosition(existingPosition);
	        } else {
	            Position newPosition = employee.getPosition();
	            employee.setPosition(newPosition);
	        }
	        
		return	repo.save(employee);
	}
	
	public Employee viewEmployee(long postId) {
		return repo.findById(postId).orElse(null);
	}
	
	public Employee updateEmployee(Employee employee,long id) {
		
		Employee employee1 = repo.findById(id).orElse(null);
        
		if (employee1 != null) {
            // Update basic employee information
            employee1.setEmpId(employee.getEmpId());
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

            return repo.save(employee1);
        } else {
        	
            return null;
        }
	}
	
	
	public void deleteEmployee(long postId) {
		repo.deleteById(postId);
	}
	
	public List<Employee> load() {
		List<Employee> employees = new ArrayList<Employee>(Arrays.asList(
				
						new Employee(1,"Vedant","Jakatdar","vedant@tcs.com"),
						new Employee(2,"Shubham","Jadhav","shubham@capgemini.com"),
						new Employee(3,"Sandip","Khaire","sandip@polycab.com"),
						new Employee(4,"Rohan","Chavan","rohan@hdfc.com")
				
				));
				
		return repo.saveAll(employees);
	}

	public List<Employee> searchByKeyword(String firstName) {
		return repo.findByfirstNameContaining(firstName);
	}	

}

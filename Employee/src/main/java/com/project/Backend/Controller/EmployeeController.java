package com.project.Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Backend.model.Employee;
import com.project.Backend.service.EmployeeService;

@RestController
@RequestMapping("employees")
@CrossOrigin("*")
public class EmployeeController {
	
	@Autowired
	private	EmployeeService employeeService;
	
	//viewAllEmployees
	@GetMapping
	public List<Employee> viewAllEmployees() {
		return employeeService.viewAllEmployees();
	}
	
	//viewEmployeeById
	@GetMapping("{empId}")
	public Employee viewEmployee(@PathVariable("empId") long postId) {
		return employeeService.viewEmployee(postId);
	}
	
	//addEmployee
	@PostMapping
	public Employee addEmployee(@RequestBody Employee employee) {
		 employeeService.addEmployee(employee);
		 return employeeService.viewEmployee(employee.getEmpId());
	}
	
	//updateEmployee
	@PutMapping("{empId}")
	public Employee updateEmployee(@RequestBody Employee employee,@PathVariable("empId") long id) {
		employeeService.updateEmployee(employee,id);
		return employeeService.viewEmployee(employee.getEmpId());
	}
	
	//deleteEmployee
	@DeleteMapping("{empId}")
	public String deleteEmployee(@PathVariable("empId") long postId) {
		employeeService.deleteEmployee(postId);
		return "Deleted";
	}
	
	//searchEmployeeByDepartmentName
	@GetMapping("search/department/{department}")
	public List<Employee> searchByDepartmentName(@PathVariable("department") String department) {
		return employeeService.searchByDepartmentName(department);
	}
	
	//searchEmployeeByPositionTiltle
	@GetMapping("search/position/{position}")
	public List<Employee> searchByPositionTiltle(@PathVariable("position") String position) {
		return employeeService.searchByPositionTitle(position);
	}
	
	//forGettingAllDepartmentsNames
	@GetMapping("/departments")
    public List<String> getDepartmentNames() {
		return employeeService.getAllDepartmentNames();
    }

	//forGettingAllPositionsNames
    @GetMapping("/positions")
    public List<String> getPositionTitles() {
    	return employeeService.getAllPositionNames();
    }
	
}

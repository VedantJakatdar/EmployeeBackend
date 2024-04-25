package com.project.Backend;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.Backend.model.Employee;
import com.project.Backend.service.EmployeeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryTest {
    
	@Autowired
    private EmployeeService employeeService;
	
	Employee newEmployee = new Employee("Lal", "Killa", "lal@killa.com", "Mechanical", "Mechanical Engg");
	
	static long empId;
	
    @Test
    @Order(1)
    public void testSaveEmployee() {
        // Test for saving an employee
    	Employee savedEmployee = employeeService.addEmployee(newEmployee);
    	empId = savedEmployee.getEmpId();
    	assertEquals(newEmployee.getFirstName(), savedEmployee.getFirstName());
    	assertEquals(newEmployee.getLastName(), savedEmployee.getLastName());
    	assertEquals(newEmployee.getEmailId(), savedEmployee.getEmailId());
    }

    @Test
    @Order(2)
    public void testFindById() {
        // Test for finding an employee by ID
    	Employee viewEmployee = employeeService.viewEmployee(EmployeeRepositoryTest.empId);
    	assertEquals(newEmployee.getFirstName(), viewEmployee.getFirstName());
    	assertEquals(newEmployee.getLastName(), viewEmployee.getLastName());
    	assertEquals(newEmployee.getEmailId(), viewEmployee.getEmailId());
    }

    @Test
    @Order(3)
    public void testDeleteEmployee() {
        // Test for deleting an employee
    	employeeService.deleteEmployee(EmployeeRepositoryTest.empId);
        Employee getEmployee = employeeService.viewEmployee(EmployeeRepositoryTest.empId);
        assertNull(getEmployee);
    }
    
}

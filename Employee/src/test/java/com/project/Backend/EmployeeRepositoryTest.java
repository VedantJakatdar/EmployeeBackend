package com.project.Backend;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.Backend.model.Employee;
import com.project.Backend.repository.EmployeeRepo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepo employeeRepository;
    
    Employee newEmployee = new Employee(12, "John", "Doe", "john@example.com", "Engineering", "Software Engineer");

    @Test
    @Order(1)
    public void testSaveEmployee() {
        // Test for saving an employee
	    Employee savedEmployee = employeeRepository.save(newEmployee);
	    assertEquals("John", savedEmployee.getFirstName());  
    }

    @Test
    @Order(2)
    public void testFindById() {
        // Test for finding an employee by ID
        Optional<Employee> optionalEmployee = employeeRepository.findById(newEmployee.getEmpId());
        assertTrue(optionalEmployee.isPresent());
        assertEquals("John", optionalEmployee.get().getFirstName());
    }

    @Test
    @Order(3)
    public void testDeleteEmployee() {
        // Test for deleting an employee
        employeeRepository.deleteById(newEmployee.getEmpId());
        assertFalse(employeeRepository.findById(newEmployee.getEmpId()).isPresent());
    }
    
}

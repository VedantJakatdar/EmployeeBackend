package com.project.Backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.Backend.model.Department;
import com.project.Backend.model.Employee;
import com.project.Backend.model.Position;
import com.project.Backend.repository.DepartmentRepository;
import com.project.Backend.repository.EmployeeRepository;
import com.project.Backend.repository.PositionRepository;
import com.project.Backend.service.EmployeeService;

@SpringBootTest
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private PositionRepository positionRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    @Order(1)
    public void testAddEmployee() {
    	
    	Employee employee = new Employee("John", "Doe", "john.doe@example.com", "Robotics", "Robotics Engg");
        Department department = new Department(employee.getDepartment().getName());
        Position position = new Position(employee.getPosition().getTitle());
        
        when(departmentRepository.findByname(employee.getDepartment().getName())).thenReturn(department);
        when(positionRepository.findBytitle(employee.getPosition().getTitle())).thenReturn(position);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee addedEmployee = employeeService.addEmployee(employee);

        assertEquals(employee.getFirstName(), addedEmployee.getFirstName());
        assertEquals(employee.getLastName(), addedEmployee.getLastName());
        assertEquals(employee.getEmailId(), addedEmployee.getEmailId());
        assertEquals(employee.getDepartment().getName(), addedEmployee.getDepartment().getName());
        assertEquals(employee.getPosition().getTitle(), addedEmployee.getPosition().getTitle());
    }

    @Test
    @Order(2)
    public void testViewEmployee() {
    	
        Employee employee = new Employee("Jane", "Doe", "jane.doe@example.com", "HR", "Manager");
        employee.setEmpId(1L);
        when(employeeRepository.findById(employee.getEmpId())).thenReturn(Optional.of(employee));

        Employee getEmployee = employeeService.viewEmployee(employee.getEmpId());

        assertEquals(employee.getFirstName(), getEmployee.getFirstName());
        assertEquals(employee.getLastName(), getEmployee.getLastName());
        assertEquals(employee.getEmailId(), getEmployee.getEmailId());
        assertEquals(employee.getDepartment().getName(), getEmployee.getDepartment().getName());
        assertEquals(employee.getPosition().getTitle(), getEmployee.getPosition().getTitle());
    }

    @Test
    @Order(3)
    public void testDeleteEmployee() {
    	
        Employee employee = new Employee("Jane", "Doe", "jane.doe@example.com", "HR", "Manager");
        employee.setEmpId(1L);

        when(employeeRepository.findById(employee.getEmpId())).thenReturn(Optional.of(employee));
        
        employeeService.deleteEmployee(employee.getEmpId());

        verify(employeeRepository, times(1)).deleteById(1L);
    }
    
}


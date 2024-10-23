package com.ahmet.EmployManagementSystem.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.ahmet.EmployManagementSystem.dto.EmployeeDto;
import com.ahmet.EmployManagementSystem.entity.Employee;
import com.ahmet.EmployManagementSystem.exception.ResourceNotFoundException;
import com.ahmet.EmployManagementSystem.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private EmployeeDto employeeDto;

    @BeforeEach
    void setUp() {
        employeeDto = new EmployeeDto();
        employeeDto.setId(1);
        employeeDto.setName("John");
        employeeDto.setSurname("Doe");
        employeeDto.setEmail("john.doe@example.com");
    }

    @Test
    void testFindEmployeeById() {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        EmployeeDto found = employeeService.getEmployeeById(1);

        assertNotNull(found);
        assertEquals(employeeDto.getId(), found.getId());
        assertEquals(employeeDto.getName(), found.getName());
        assertEquals(employeeDto.getEmail(), found.getEmail());
    }

    @Test
    void testFindEmployeeById_NotFound() {
        when(employeeRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.getEmployeeById(1);
        });
    }

    @Test
    void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setSurname(employeeDto.getSurname()); // Ensure surname is set
        employee.setEmail(employeeDto.getEmail());

        when(employeeRepository.save(employee)).thenReturn(employee);

        EmployeeDto saved = employeeService.addEmployee(employeeDto);

        assertNotNull(saved);
        assertEquals(employeeDto.getId(), saved.getId());
        assertEquals(employeeDto.getName(), saved.getName());
        assertEquals(employeeDto.getSurname(), saved.getSurname()); // Add assertion for surname
        assertEquals(employeeDto.getEmail(), saved.getEmail());
    }

    @Test
    void testSaveEmployee_InvalidData() {
        EmployeeDto invalidEmployeeDto = new EmployeeDto();
        invalidEmployeeDto.setId(2);
        invalidEmployeeDto.setName(null); // Invalid name

        assertThrows(IllegalArgumentException.class, () -> {
            employeeService.addEmployee(invalidEmployeeDto);
        });
    }

    @Test
    void testUpdateEmployee() {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(employee);

        EmployeeDto updated = employeeService.updateEmployee(1, employeeDto);

        assertNotNull(updated);
        assertEquals(employeeDto.getId(), updated.getId());
        assertEquals(employeeDto.getName(), updated.getName());
        assertEquals(employeeDto.getEmail(), updated.getEmail());
    }

    @Test
    void testUpdateEmployee_NotFound() {
        when(employeeRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.updateEmployee(1, employeeDto);
        });
    }

    @Test
    void testDeleteEmployee() {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).deleteById(1);

        employeeService.deleteEmployee(1);

        verify(employeeRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteEmployee_NotFound() {
        when(employeeRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.deleteEmployee(1);
        });
    }
}
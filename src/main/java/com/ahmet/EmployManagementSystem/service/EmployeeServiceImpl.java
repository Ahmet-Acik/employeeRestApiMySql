package com.ahmet.EmployManagementSystem.service;

import com.ahmet.EmployManagementSystem.dto.EmployeeDto;
import com.ahmet.EmployManagementSystem.entity.Employee;
import com.ahmet.EmployManagementSystem.exception.EmployeeAlreadyExistsException;
import com.ahmet.EmployManagementSystem.exception.ResourceNotFoundException;
import com.ahmet.EmployManagementSystem.mapper.EmployeeMapper;
import com.ahmet.EmployManagementSystem.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;


    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper::mapToEmployeeDto).toList();
    }

    @Override
    public EmployeeDto getEmployeeById(int id) {
        Employee employeeById = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return EmployeeMapper.mapToEmployeeDto(employeeById);
    }

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        if (employeeDto.getName() == null || employeeDto.getSurname() == null || employeeDto.getEmail() == null) {
            throw new IllegalArgumentException("Employee name, surname, and email cannot be null");
        }
        if (!employeeDto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Email should be valid");
        }
        if (employeeRepository.existsByEmail(employeeDto.getEmail())) {
            throw new EmployeeAlreadyExistsException("Employee with email " + employeeDto.getEmail() + " already exists");
        }
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto updateEmployee(Integer id, EmployeeDto employeeDto) {

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employee.setName(employeeDto.getName());
        employee.setSurname(employeeDto.getSurname());
        employee.setEmail(employeeDto.getEmail());
        employee.setJobTitle(employeeDto.getJobTitle());
        employee.setPhone(employeeDto.getPhone());
        employee.setImageUrl(employeeDto.getImageUrl());
        employee.setEmployeeCode(employeeDto.getEmployeeCode());

        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);

    }

    @Override
    public void deleteEmployee(int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employeeRepository.deleteById(id);
    }

}

package com.ahmet.EmployManagementSystem.service;

import com.ahmet.EmployManagementSystem.dto.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

        List<EmployeeDto> getAllEmployees();

        EmployeeDto getEmployeeById(int id);

        EmployeeDto addEmployee(EmployeeDto employeeDto);

        EmployeeDto updateEmployee(Integer id, EmployeeDto employeeDto);

        void deleteEmployee(int id);
}

package com.ahmet.EmployManagementSystem.mapper;

import com.ahmet.EmployManagementSystem.dto.EmployeeDto;
import com.ahmet.EmployManagementSystem.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(employee.getId(), employee.getName(), employee.getSurname(), employee.getEmail(), employee.getJobTitle(), employee.getPhone(), employee.getImageUrl(), employee.getEmployeeCode());
    }
    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return new Employee(employeeDto.getId(), employeeDto.getName(), employeeDto.getSurname(), employeeDto.getEmail(), employeeDto.getJobTitle(), employeeDto.getPhone(), employeeDto.getImageUrl(), employeeDto.getEmployeeCode());
    }
}

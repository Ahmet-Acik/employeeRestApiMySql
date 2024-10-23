package com.ahmet.EmployManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private int id;

    private String name;
    private String surname;

    private String email;

    private String jobTitle;
    private String phone;
    private String imageUrl;
    private String employeeCode;


}

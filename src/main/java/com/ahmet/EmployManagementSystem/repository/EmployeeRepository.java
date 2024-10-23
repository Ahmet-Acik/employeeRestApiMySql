package com.ahmet.EmployManagementSystem.repository;

import com.ahmet.EmployManagementSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    boolean existsByEmail(String email);
}

package com.example.hellowicket.service;

import com.example.hellowicket.Employee;
import com.example.hellowicket.model.EmployeeEntity;

import java.util.List;

public interface IEmployeeService {
    List<Employee> getAllEmployees();
    EmployeeEntity addEmployee(Employee employee);
}

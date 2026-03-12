package com.example.hellowicket.service;

import com.example.hellowicket.Employee;
import com.example.hellowicket.model.EmployeeEntity;

import java.util.List;

public interface IEmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Integer id);
    Employee createEmployee(Integer employeeId, String firstName, String lastName);
    EmployeeEntity saveEmployee(Employee employee);
    void deleteEmployee(Integer id);
}

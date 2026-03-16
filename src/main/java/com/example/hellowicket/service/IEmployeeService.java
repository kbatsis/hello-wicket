package com.example.hellowicket.service;

import com.example.hellowicket.Employee;
import com.example.hellowicket.Supervisor;
import com.example.hellowicket.model.EmployeeEntity;
import com.example.hellowicket.model.Role;

import java.util.List;

public interface IEmployeeService {
    List<Employee> getAllEmployees();
    List<Supervisor> getAllSupervisors();
    Employee getEmployeeById(Integer id);
    Employee createEmployee(Integer employeeId, String firstName, String lastName, Supervisor supervisor, Role role);
    EmployeeEntity saveEmployee(Employee employee);
    void deleteEmployee(Integer id);
}

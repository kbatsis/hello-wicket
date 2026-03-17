package com.example.hellowicket.service;

import com.example.hellowicket.Employee;
import com.example.hellowicket.Supervisor;
import com.example.hellowicket.model.EmployeeEntity;
import com.example.hellowicket.model.Role;
import com.example.hellowicket.service.exception.EntityConstraintViolationException;

import java.util.List;

public interface IEmployeeService {
    List<Employee> getAllEmployees();
    List<Supervisor> getSupervisors(Employee employee);
    List<Employee> getSubordinates(Employee employee);
    Employee getEmployeeById(Integer id);
    Employee createEmployee(Integer employeeId, String firstName, String lastName, Supervisor supervisor, Role role);
    EmployeeEntity saveEmployee(Employee employee) throws EntityConstraintViolationException;
    void deleteEmployee(Integer id);
}

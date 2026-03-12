package com.example.hellowicket.service;

import com.example.hellowicket.Employee;
import com.example.hellowicket.Mapper.Mapper;
import com.example.hellowicket.model.EmployeeEntity;
import com.example.hellowicket.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();

        for (EmployeeEntity employeeEntity : employeeEntities) {
            Employee employee = Mapper.mapEmployeeEntityToEmployee(employeeEntity);
            employees.add(employee);
        }

        return employees;
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return Mapper.mapEmployeeEntityToEmployee(employeeRepository.findById(id).get());
    }

    @Override
    public Employee createEmployee(Integer employeeId, String firstName, String lastName) {
        return new Employee(employeeId, firstName, lastName);
    }

    @Override
    public EmployeeEntity addEmployee(Employee employee) {
        return employeeRepository.save(Mapper.mapEmployeeToEntity(employee));
    }

    @Override
    public EmployeeEntity editEmployee(Employee employee) {
        EmployeeEntity employeeToUpdate = employeeRepository.findById(employee.getId()).get();

        return employeeRepository.save(Mapper.mapEmployeeEntityFields(employeeToUpdate, employee));
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }
}

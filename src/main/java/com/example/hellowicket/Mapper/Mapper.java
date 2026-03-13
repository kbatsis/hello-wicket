package com.example.hellowicket.Mapper;

import com.example.hellowicket.Employee;
import com.example.hellowicket.model.EmployeeEntity;

public class Mapper {
    private Mapper() {}

    public static EmployeeEntity mapEmployeeToEntity(Employee employee) {
        return new EmployeeEntity(null, employee.getFirstName(), employee.getLastName(), null, employee.getRole());
    }

    public static Employee mapEmployeeEntityToEmployee(EmployeeEntity entity) {
        return new Employee(entity.getId(), entity.getFirstName(), entity.getLastName(), null, entity.getRole());
    }

    public static EmployeeEntity mapEmployeeEntityFields(EmployeeEntity entity, Employee employee) {
        entity.setFirstName(employee.getFirstName());
        entity.setLastName(employee.getLastName());
        entity.setRole(employee.getRole());
        return entity;
    }
}

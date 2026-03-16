package com.example.hellowicket.Mapper;

import com.example.hellowicket.Employee;
import com.example.hellowicket.Supervisor;
import com.example.hellowicket.model.EmployeeEntity;

public class Mapper {
    private Mapper() {}

    public static EmployeeEntity mapEmployeeToEntity(Employee employee, EmployeeEntity supervisor) {
        return new EmployeeEntity(employee.getId(), employee.getFirstName(), employee.getLastName(), supervisor, employee.getRole());
    }

    public static Employee mapEmployeeEntityToEmployee(EmployeeEntity entity) {
        Supervisor supervisor = null;
        if (entity.getSupervisor() != null) {
            supervisor = new Supervisor(entity.getSupervisor().getId(), entity.getSupervisor().getFirstName() + " " + entity.getSupervisor().getLastName());
        }

        return new Employee(entity.getId(), entity.getFirstName(), entity.getLastName(), supervisor, entity.getRole());
    }

    public static EmployeeEntity mapEmployeeEntityFields(EmployeeEntity entity, Employee employee, EmployeeEntity supervisor) {
        entity.setFirstName(employee.getFirstName());
        entity.setLastName(employee.getLastName());
        entity.setRole(employee.getRole());
        entity.setSupervisor(supervisor);
        return entity;
    }

    public static Supervisor mapEmployeeEntityToSupervisor(EmployeeEntity entity) {
        return new Supervisor(entity.getId(), entity.getFirstName() + " " + entity.getLastName());
    }
}

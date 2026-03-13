package com.example.hellowicket.Mapper;

import com.example.hellowicket.Employee;
import com.example.hellowicket.model.EmployeeEntity;
import com.example.hellowicket.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Mapper {
    private Mapper() {}

    public static EmployeeEntity mapEmployeeToEntity(Employee employee, EmployeeEntity supervisor) {
        EmployeeEntity supervisorEntity = null;
        if (employee.getSupervisor() != null) {
            supervisorEntity = supervisor;
        }

        return new EmployeeEntity(null, employee.getFirstName(), employee.getLastName(), supervisorEntity, employee.getRole());
    }

    public static Employee mapEmployeeEntityToEmployee(EmployeeEntity entity) {
        Employee supervisorDTO = null;
        if (entity.getSupervisor() != null) {
            supervisorDTO =  mapEmployeeEntityToEmployee(entity.getSupervisor());
        }

        return new Employee(entity.getId(), entity.getFirstName(), entity.getLastName(), supervisorDTO, entity.getRole());
    }

    public static EmployeeEntity mapEmployeeEntityFields(EmployeeEntity entity, Employee employee, EmployeeEntity supervisor) {
        entity.setFirstName(employee.getFirstName());
        entity.setLastName(employee.getLastName());
        entity.setRole(employee.getRole());
        if (employee.getSupervisor() != null) {
            entity.setSupervisor(supervisor);
        }
        return entity;
    }
}

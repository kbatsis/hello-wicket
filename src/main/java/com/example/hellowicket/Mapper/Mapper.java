package com.example.hellowicket.Mapper;

import com.example.hellowicket.Employee;
import com.example.hellowicket.dto.EmployeeInsertDTO;
import com.example.hellowicket.dto.EmployeeReadOnlyDTO;
import com.example.hellowicket.model.EmployeeEntity;

public class Mapper {
    private Mapper() {}

    public static EmployeeEntity mapEmployeeToEntity(Employee employee) {
        return new EmployeeEntity(null, employee.getFirstName(), employee.getLastName());
    }

    public static Employee mapEmployeeEntityToEmployee(EmployeeEntity entity) {
        return new Employee(entity.getId(), entity.getFirstName(), entity.getLastName());
    }
}

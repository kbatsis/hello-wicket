package com.example.hellowicket.service;

import com.example.hellowicket.dto.EmployeeInsertDTO;
import com.example.hellowicket.model.EmployeeEntity;

import java.util.List;

public interface IEmployeeService {
    List<EmployeeEntity> getAllEmployees();
    EmployeeEntity addEmployee(EmployeeInsertDTO dto);
}

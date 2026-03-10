package com.example.hellowicket.service;

import com.example.hellowicket.Mapper.Mapper;
import com.example.hellowicket.dto.EmployeeInsertDTO;
import com.example.hellowicket.model.EmployeeEntity;
import com.example.hellowicket.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public EmployeeEntity addEmployee(EmployeeInsertDTO dto) {
        return employeeRepository.save(Mapper.mapEmployeeEntityToDTO(dto));
    }
}

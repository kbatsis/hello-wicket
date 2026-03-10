package com.example.hellowicket.rest;

import com.example.hellowicket.Mapper.Mapper;
import com.example.hellowicket.dto.EmployeeInsertDTO;
import com.example.hellowicket.dto.EmployeeReadOnlyDTO;
import com.example.hellowicket.model.EmployeeEntity;
import com.example.hellowicket.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private final EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeRestController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeReadOnlyDTO>> getAllEmployees() {
        List<EmployeeReadOnlyDTO> employeeReadOnlyDTOS = new ArrayList<>();
        List<EmployeeEntity> employeeEntities = employeeService.getAllEmployees();

        for (EmployeeEntity employeeEntity : employeeEntities) {
            employeeReadOnlyDTOS.add(Mapper.mapEmployeeEntityToEmployReadOnlyDTO(employeeEntity));
        }

        return new ResponseEntity<>(employeeReadOnlyDTOS, HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeReadOnlyDTO> insertEmployee(@RequestBody EmployeeInsertDTO dto) {
        EmployeeReadOnlyDTO employeeReadOnlyDTO = Mapper.mapEmployeeEntityToEmployReadOnlyDTO(employeeService.addEmployee(dto));

        return new ResponseEntity<>(employeeReadOnlyDTO, HttpStatus.CREATED);
    }
}
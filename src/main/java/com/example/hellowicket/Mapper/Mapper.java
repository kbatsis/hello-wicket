package com.example.hellowicket.Mapper;

import com.example.hellowicket.dto.EmployeeInsertDTO;
import com.example.hellowicket.dto.EmployeeReadOnlyDTO;
import com.example.hellowicket.model.EmployeeEntity;

public class Mapper {
    private Mapper() {}

    public static EmployeeEntity mapEmployeeEntityToDTO(EmployeeInsertDTO dto) {
        return new EmployeeEntity(null, dto.getFirstName(), dto.getLastName());
    }

    public static EmployeeReadOnlyDTO mapEmployeeEntityToEmployReadOnlyDTO(EmployeeEntity entity) {
        return new EmployeeReadOnlyDTO(entity.getId(), entity.getFirstName(), entity.getLastName());
    }
}

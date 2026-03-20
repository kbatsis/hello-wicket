package com.example.hellowicket.repository;

import com.example.hellowicket.model.EmployeeEntity;
import com.example.hellowicket.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    Page<EmployeeEntity> findBySupervisorEquals(EmployeeEntity supervisor, Pageable page);
    List<EmployeeEntity> findByRoleIn(List<Role> roles);
    long countBySupervisorEquals(EmployeeEntity supervisor);
}

package com.example.hellowicket.repository;

import com.example.hellowicket.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.*;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

}

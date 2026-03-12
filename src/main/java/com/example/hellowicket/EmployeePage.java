package com.example.hellowicket;

public class EmployeePage extends BasePage {
    public EmployeePage() {
        add(new EmployeeForm("employeeForm"));
    }

    public EmployeePage(Integer id) {
        add(new EmployeeForm("employeeForm", id));
    }
}

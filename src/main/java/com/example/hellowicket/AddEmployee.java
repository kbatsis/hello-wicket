package com.example.hellowicket;

import org.apache.wicket.markup.html.WebPage;

import java.util.List;

public class AddEmployee extends WebPage {
    public AddEmployee(List<Employee> employees) {
        add(new AddEmployeeForm("addEmployeeForm", employees));
    }
}

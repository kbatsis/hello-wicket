package com.example.hellowicket;

import org.apache.wicket.markup.html.WebPage;

public class AddEmployee extends WebPage {
    public AddEmployee() {
        add(new EmployeeForm("employeeForm"));
    }
}

package com.example.hellowicket;

import org.apache.wicket.markup.html.WebPage;

public class EditEmployee extends WebPage {
    public EditEmployee(Integer employeeId) {
        add(new EmployeeForm("employeeForm", employeeId));
    }
}

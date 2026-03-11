package com.example.hellowicket;

import org.apache.wicket.markup.html.WebPage;

public class EditEmployee extends WebPage {
    public EditEmployee(Integer employeeId, String firstName, String lastName) {
        add(new EditEmployForm("editEmployeeForm", employeeId, firstName, lastName));
    }
}

package com.example.hellowicket;

import com.example.hellowicket.service.IEmployeeService;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class EditEmployForm extends Panel {
    //private final TextField<Integer> idField;
    private final TextField<String> firstNameField;
    private final TextField<String> lastNameField;
    private final Form<Employee> employeeForm;

    @SpringBean
    IEmployeeService employeeService;

    public EditEmployForm(String id, Integer employeeId, String firstName, String lastName) {
        super(id);

        employeeForm = new Form<>("editEmployeeForm") {
            @Override
            protected void onSubmit() {
                String firstName = firstNameField.getModelObject();
                String lastName = lastNameField.getModelObject();

                Employee employee = employeeService.createEmployee(employeeId, firstName, lastName);
                employeeService.editEmployee(employee);

                setResponsePage(ListPage.class);
            }
        };
        firstNameField = new TextField<>("firstName", Model.of(firstName));
        lastNameField = new TextField<>("lastName", Model.of(lastName));

        add(employeeForm);
        employeeForm.add(firstNameField);
        employeeForm.add(lastNameField);
    }
}

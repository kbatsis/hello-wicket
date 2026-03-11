package com.example.hellowicket;

import com.example.hellowicket.service.IEmployeeService;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class AddEmployeeForm extends Panel {
    private final TextField<String> firstNameField;
    private final TextField<String> lastNameField;
    private final Form<Employee> employeeForm;

    @SpringBean
    private IEmployeeService employeeService;

    public AddEmployeeForm(String id) {
        super(id);

        employeeForm = new Form<>("addEmployeeForm") {
            @Override
            protected void onSubmit() {
                String firstName = firstNameField.getModelObject();
                String lastName = lastNameField.getModelObject();

                employeeService.addEmployee(new Employee(null, firstName, lastName));

                setResponsePage(ListPage.class);
            }
        };
        firstNameField = new TextField<>("firstName", Model.of(""));
        lastNameField = new TextField<>("lastName", Model.of(""));

        add(employeeForm);
        employeeForm.add(firstNameField);
        employeeForm.add(lastNameField);
    }
}

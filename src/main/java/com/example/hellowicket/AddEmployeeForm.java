package com.example.hellowicket;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.List;

public class AddEmployeeForm extends Form<Employee> {
    private TextField idField;
    private TextField firstNameField;
    private TextField lastNameField;
    private List<Employee> employees;

    public AddEmployeeForm(String id, List<Employee> employees) {
        super(id);
        this.employees = employees;

        idField = new TextField<Integer>("id", Model.of(0));
        firstNameField = new TextField<String>("firstName", Model.of(""));
        lastNameField = new TextField<String>("lastName", Model.of(""));

        add(idField);;
        add(firstNameField);
        add(lastNameField);
    }

    @Override
    protected void onSubmit() {
        Integer id = (Integer) idField.getModelObject();
        String firstName = (String) firstNameField.getModelObject();
        String lastName = (String) lastNameField.getModelObject();

        Employee employeeToAdd = new Employee(id, firstName, lastName);
        employees.add(employeeToAdd);

        ListPage listPage = new ListPage(employees);
        setResponsePage(listPage);
    }
}

package com.example.hellowicket;

import com.example.hellowicket.service.EmployeeServiceImpl;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class AddEmployeeForm extends Form<Employee> {
    //private TextField idField;
    private TextField firstNameField;
    private TextField lastNameField;

    @SpringBean
    private EmployeeServiceImpl employeeService;

    public AddEmployeeForm(String id) {
        super(id);

        //idField = new TextField<Integer>("id", Model.of(0));
        firstNameField = new TextField<String>("firstName", Model.of(""));
        lastNameField = new TextField<String>("lastName", Model.of(""));

        //add(idField);;
        add(firstNameField);
        add(lastNameField);
    }

    @Override
    protected void onSubmit() {
        //Integer id = (Integer) idField.getModelObject();
        String firstName = (String) firstNameField.getModelObject();
        String lastName = (String) lastNameField.getModelObject();

        employeeService.addEmployee(new Employee(null, firstName, lastName));

        setResponsePage(ListPage.class);
    }
}

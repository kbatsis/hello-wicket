package com.example.hellowicket;

import com.example.hellowicket.model.Role;
import com.example.hellowicket.service.IEmployeeService;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EnumChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Arrays;
import java.util.List;

public class EmployeeForm extends Panel {
    private TextField<String> firstNameField;
    private TextField<String> lastNameField;
    private DropDownChoice<Role> roleDropDown;
    private Form<Employee> employeeForm;

    @SpringBean
    private IEmployeeService employeeService;

    private Employee employee;

    public EmployeeForm(String id) {
        super(id);
        employee= new Employee(null, "", "", null, Role.EMPLOYEE );
        addComponents(employee);
    }

    public EmployeeForm(String id, Integer employeeId) {
        super(id);
        Employee employee = employeeService.getEmployeeById(employeeId);
        addComponents(employee);
    }

    protected void addComponents(Employee employee) {
        employeeForm = new Form<>("employeeForm") {
            @Override
            protected void onSubmit() {
                String firstName = firstNameField.getModelObject();
                String lastName = lastNameField.getModelObject();
                Role role = roleDropDown.getModelObject();

                Employee employeeInstance = employeeService.createEmployee(employee.getId(), firstName, lastName, null, role);
                employeeService.saveEmployee(employeeInstance);

                setResponsePage(ListPage.class);
            }
        };

        firstNameField = new TextField<>("firstName", Model.of(employee.getFirstName()));
        lastNameField = new TextField<>("lastName", Model.of(employee.getLastName()));
        roleDropDown = new DropDownChoice<Role>("role", Model.of(employee.getRole()), Arrays.asList(Role.values()), new EnumChoiceRenderer<>(this));

        add(employeeForm);
        employeeForm.add(firstNameField);
        employeeForm.add(lastNameField);
        employeeForm.add(roleDropDown);
    }
}

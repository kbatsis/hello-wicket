package com.example.hellowicket;

import com.example.hellowicket.model.Role;
import com.example.hellowicket.service.IEmployeeService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Arrays;
import java.util.List;

public class EmployeeForm extends Panel {
    private TextField<String> firstNameField;
    private TextField<String> lastNameField;
    private DropDownChoice<Role> roleDropDown;
    private DropDownChoice<Employee> supervisorDropDown;
    private Form<Employee> employeeForm;

    @SpringBean
    private IEmployeeService employeeService;

    private Employee employee;

    public EmployeeForm(String id) {
        super(id);
        employee= new Employee(null, "", "", null, null);
        addComponents(employee);
    }

    public EmployeeForm(String id, Integer employeeId) {
        super(id);
        employee = employeeService.getEmployeeById(employeeId);
        addComponents(employee);
    }

    protected void addComponents(Employee employee) {
        employeeForm = new Form<>("employeeForm") {
            @Override
            protected void onSubmit() {
                String firstName = firstNameField.getModelObject();
                String lastName = lastNameField.getModelObject();
                Employee supervisor = supervisorDropDown.getModelObject();
                Role role = roleDropDown.getModelObject();

                Employee employeeInstance = employeeService.createEmployee(employee.getId(), firstName, lastName, supervisor, role);
                employeeService.saveEmployee(employeeInstance);

                setResponsePage(ListPage.class);
            }
        };

        firstNameField = new TextField<>("firstName", Model.of(employee.getFirstName()));
        lastNameField = new TextField<>("lastName", Model.of(employee.getLastName()));
        roleDropDown = new DropDownChoice<>("role", Model.of(employee.getRole()), Arrays.asList(Role.values()), new EnumChoiceRenderer<>(this));

        List<Employee> employees = employeeService.getAllEmployees();

        IModel<Employee> selectedSupervisorModel = new PropertyModel<>(this, "employee.getSupervisor()");
        supervisorDropDown = new DropDownChoice<>("supervisor", selectedSupervisorModel, employees, new ChoiceRenderer<>() {
            @Override
            public Object getDisplayValue(Employee employee) {
                return employee.getFirstName() + " " + employee.getLastName();
            }

            @Override
            public String getIdValue(Employee employee, int index) {
                return String.valueOf(employee.getId());
            }
        });

        add(employeeForm);
        employeeForm.add(firstNameField);
        employeeForm.add(lastNameField);
        employeeForm.add(roleDropDown);
        employeeForm.add(supervisorDropDown);

        supervisorDropDown.setRequired(true);
        roleDropDown.add(new OnChangeAjaxBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget ajaxRequestTarget) {
                supervisorDropDown.setEnabled(true);
                if (roleDropDown.getModelObject() == Role.CEO) {
                    supervisorDropDown.setEnabled(false);
                }
            }
        });
    }
}

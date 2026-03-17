package com.example.hellowicket;

import com.example.hellowicket.model.Role;
import com.example.hellowicket.service.IEmployeeService;
import com.example.hellowicket.service.exception.EntityConstraintViolationException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EnumChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

import java.util.Arrays;

public class EmployeeForm extends Panel {
    private TextField<String> firstNameField;
    private TextField<String> lastNameField;
    private DropDownChoice<Role> roleDropDown;
    private SupervisorDropDown supervisorDropDown;
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
        employeeForm = new Form<>("employeeForm", new CompoundPropertyModel<Employee>(employee)) {
            @Override
            protected void onSubmit() {

//                String firstName = firstNameField.getModelObject();
//                String lastName = lastNameField.getModelObject();
//                Supervisor supervisor = supervisorDropDown.getModelObject();
//                Role role = roleDropDown.getModelObject();
//                Employee employeeInstance = employeeService.createEmployee(employee.getId(), firstName, lastName, supervisor, role);

                try {
                    employeeService.saveEmployee(employee);
                } catch (EntityConstraintViolationException e) {
                    throw new RuntimeException(e.getMessage());
                }

                setResponsePage(ListPage.class);
            }
        };

        firstNameField = new TextField<>("firstName");
        lastNameField = new TextField<>("lastName");
        roleDropDown = new DropDownChoice<>("role", Arrays.asList(Role.values()), new EnumChoiceRenderer<>(this));
        IModel<Employee> employeeModel = new CompoundPropertyModel<>(employee);
        supervisorDropDown = new SupervisorDropDown("supervisor", employeeModel);

        add(employeeForm);
        employeeForm.add(firstNameField);
        employeeForm.add(lastNameField);
        employeeForm.add(roleDropDown);
        employeeForm.add(supervisorDropDown);

        firstNameField.setRequired(true);
        lastNameField.setRequired(true);
        roleDropDown.setRequired(true);
        supervisorDropDown.setRequired(true);
        firstNameField.add(new StringValidator(1, 30));
        lastNameField.add(new StringValidator(1, 30));

        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        employeeForm.add(feedbackPanel);

        supervisorDropDown.setOutputMarkupId(true);
        if (employee.getRole() == Role.CEO) {
            supervisorDropDown.setEnabled(false);
        }

        roleDropDown.add(new OnChangeAjaxBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget ajaxRequestTarget) {
                supervisorDropDown.setEnabled(true);
                if (roleDropDown.getModelObject() == Role.CEO) {
                    supervisorDropDown.setEnabled(false);
                    supervisorDropDown.setModelObject(null);
                }

                ajaxRequestTarget.add(supervisorDropDown);
            }
        });
    }
}

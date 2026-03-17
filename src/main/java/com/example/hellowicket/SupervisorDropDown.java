package com.example.hellowicket;

import com.example.hellowicket.service.IEmployeeService;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class SupervisorDropDown extends DropDownChoice<Supervisor> {
    @SpringBean
    private IEmployeeService employeeService;

    public SupervisorDropDown(String id, IModel<Employee> employeeModel) {
        super(id);

        setChoices(() -> employeeService.getSupervisors(employeeModel.getObject()));

        setChoiceRenderer(new ChoiceRenderer<>() {
            @Override
            public Object getDisplayValue(Supervisor supervisor) {
                return supervisor.getFullName();
            }

            @Override
            public String getIdValue(Supervisor supervisor, int index) {
                return String.valueOf(supervisor.getId());
            }
        });


    }
}

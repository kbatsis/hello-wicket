package com.example.hellowicket;

import com.example.hellowicket.service.IEmployeeService;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class SupervisorDropDown extends DropDownChoice<Supervisor> {
    @SpringBean
    private IEmployeeService employeeService;

    public SupervisorDropDown(String id, List<? extends Supervisor> choices) {
        super(id, choices, new ChoiceRenderer<>() {
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

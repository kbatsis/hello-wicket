package com.example.hellowicket;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;

import java.util.List;

public class SupervisorDropDown extends DropDownChoice<Supervisor> {
    public SupervisorDropDown(String id, List<Supervisor> choices) {
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

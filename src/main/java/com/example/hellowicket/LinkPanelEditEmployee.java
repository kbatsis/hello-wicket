package com.example.hellowicket;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

public class LinkPanelEditEmployee extends Panel {

    public LinkPanelEditEmployee(String id, IModel<Employee> rowModel) {
        super(id, rowModel);

        add(new Link<Void>("editEmployee") {
            @Override
            public void onClick() {
                Employee employee = rowModel.getObject();

                EmployeePage employeePage = new EmployeePage(employee.getId());
                setResponsePage(employeePage);
            }
        }.setBody(new ResourceModel("editEmployee")));
    }
}
package com.example.hellowicket;

import com.example.hellowicket.service.IEmployeeService;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class LinkPanelRemoveEmployeeWithSubordinates extends Panel {
    @SpringBean
    IEmployeeService employeeService;

    public LinkPanelRemoveEmployeeWithSubordinates(String id, IModel<Employee> rowModel) {
        super(id, rowModel);

        add(new Link<Void>("removeEmployeeWithSubordinates") {
            @Override
            public void onClick() {
                Employee employee = rowModel.getObject();

                employeeService.deleteSupervisorWithSubordinates(employee);
                setResponsePage(ListPage.class);
            }
        }.setBody(new ResourceModel("ListPage.removeEmployeeWithSubordinatesLabel")));
    }
}

package com.example.hellowicket;

import com.example.hellowicket.model.Role;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

public class LinkPanelListSubordinates extends Panel {
    public LinkPanelListSubordinates(String id, IModel<Employee> rowModel) {
        super(id, rowModel);

        add(new Link<Void>("listSubordinates") {
            @Override
            public void onClick() {
                Employee employee = rowModel.getObject();

                SubordinatesPage subordinatesPage = new SubordinatesPage(employee, 0);
                setResponsePage(subordinatesPage);
            }
            @Override
            public void onConfigure() {
                Employee employee = rowModel.getObject();

                super.onConfigure();
                if (employee.getRole() == Role.EMPLOYEE) {
                    setVisible(false);
                }
            }
        }.setBody(new ResourceModel("listSubordinates")));
    }
}

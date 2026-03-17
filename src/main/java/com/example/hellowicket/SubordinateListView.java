package com.example.hellowicket;

import com.example.hellowicket.service.IEmployeeService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class SubordinateListView extends ListView<Employee> {
    @SpringBean
    private IEmployeeService employeeService;

    public SubordinateListView(String id, Employee supervisor) {
        super(id);

        setList(employeeService.getSubordinates(supervisor));
    }

    @Override
    protected void populateItem(ListItem listItem) {
        final Employee employee = (Employee) listItem.getModelObject();
        listItem.add(new Label("id", employee.getId()));
        listItem.add(new Label("firstName", employee.getFirstName()));
        listItem.add(new Label("lastName", employee.getLastName()));
    }
}

package com.example.hellowicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;

import java.util.ArrayList;
import java.util.List;

public class ListPage extends WebPage {
    public ListPage(List<Employee> employees) {
        Employee employee;

        final DataView<Employee> dataView = new DataView<Employee>("employees", new ListDataProvider<>(employees)) {
            @Override
            protected void populateItem(Item item) {
                final Employee employee = (Employee) item.getModelObject();
                item.add(new Label("id", employee.getId()));
                item.add(new Label("firstName", employee.getFirstName()));
                item.add(new Label("lastName", employee.getLastName()));
            }
        };

        dataView.setItemsPerPage(10);
        add(dataView);
        add(new PagingNavigator("navigator", dataView));

        add(new Link<Void>("addEmployee") {
            @Override
            public void onClick() {
                AddEmployee addEmployee = new AddEmployee(employees);
                setResponsePage(addEmployee);
            }
        });
    }
}

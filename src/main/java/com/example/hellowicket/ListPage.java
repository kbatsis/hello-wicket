package com.example.hellowicket;

import com.example.hellowicket.dto.EmployeeReadOnlyDTO;
import com.example.hellowicket.repository.EmployeeRepository;
import com.example.hellowicket.service.EmployeeServiceImpl;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;
import java.util.ArrayList;
import java.util.List;

public class ListPage extends WebPage {
    @SpringBean
    private EmployeeServiceImpl employeeService;

    public ListPage() {
        List<Employee> employees = employeeService.getAllEmployees();

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
                setResponsePage(AddEmployee.class);
            }
        });
    }
}

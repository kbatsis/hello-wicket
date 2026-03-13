package com.example.hellowicket;

import com.example.hellowicket.service.IEmployeeService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class ListPage extends BasePage {
    @SpringBean
    private IEmployeeService employeeService;

    public ListPage() {
        List<Employee> employees = employeeService.getAllEmployees();

        final DataView<Employee> dataView = new DataView<>("employees", new ListDataProvider<>(employees)) {
            @Override
            protected void populateItem(Item item) {
                final Employee employee = (Employee) item.getModelObject();
                item.add(new Label("id", employee.getId()));
                item.add(new Label("firstName", employee.getFirstName()));
                item.add(new Label("lastName", employee.getLastName()));
                item.add(new Label("role", employee.getRole()));

                String supervisorLabel = null;
                if (employee.getSupervisor() != null) {
                    supervisorLabel = employee.getSupervisor().getFirstName() + " " + employee.getSupervisor().getLastName();
                }
                item.add(new Label("supervisor", supervisorLabel));
                item.add(new Link<Void>("editEmployee") {
                    @Override
                    public void onClick() {
                        EmployeePage employeePage = new EmployeePage(employee.getId());
                        setResponsePage(employeePage);
                    }
                });
                item.add(new Link<Void>("removeEmployee") {
                    @Override
                    public void onClick() {
                        employeeService.deleteEmployee(employee.getId());
                        setResponsePage(ListPage.class);
                    }
                });
            }
        };

        dataView.setItemsPerPage(10);
        add(dataView);
        add(new PagingNavigator("navigator", dataView));

        add(new Link<Void>("addEmployee") {
            @Override
            public void onClick() {
                setResponsePage(EmployeePage.class);
            }
        });
    }
}

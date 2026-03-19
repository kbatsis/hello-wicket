package com.example.hellowicket;

import com.example.hellowicket.model.Role;
import com.example.hellowicket.service.IEmployeeService;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.EnumLabel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;

public class ListPage extends BasePage {
    @SpringBean
    private IEmployeeService employeeService;

    public ListPage() {
        List<Employee> employees = employeeService.getAllEmployees();

//        List<IColumn<Employee, String>> columns = new ArrayList<>();
//        columns.add(new PropertyColumn<Employee, String>(new Model<String>("Id"), "firstName", "id"));
//        columns.add(new PropertyColumn<Employee, String>(new Model<String>("First Name"), "firstName", "firstName"));
//        columns.add(new PropertyColumn<Employee, String>(new Model<String>("Last Name"), "lastName"));
//        columns.add(new PropertyColumn<Employee, String>(new Model<String>("Role"), "role"));


        final DataView<Employee> dataView = new DataView<>("employees", new ListDataProvider<>(employees)) {
            @Override
            protected void populateItem(Item item) {
                final Employee employee = (Employee) item.getModelObject();
                item.add(new Label("id", employee.getId()));
                item.add(new Label("firstName", employee.getFirstName()));
                item.add(new Label("lastName", employee.getLastName()));
                item.add(new EnumLabel<>("role", employee.getRole()));
                if (employee.getSupervisor() != null) {
                    item.add(new Label("supervisor", employee.getSupervisor().getFullName()));
                } else {
                    item.add(new Label("supervisor", ""));
                }
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

                item.add(new Link<Void>("removeEmployeeWithSubordinates") {
                    @Override
                    public void onClick() {
                        employeeService.deleteSupervisorWithSubordinates(employee);
                        setResponsePage(ListPage.class);
                    }
                }.setBody(new ResourceModel("ListPage.removeEmployeeWithSubordinatesLabel")));

                item.add(new Link<Void>("listSubordinates") {
                    @Override
                    public void onClick() {
                        SubordinatesPage subordinatesPage = new SubordinatesPage(employee, 0);
                        setResponsePage(subordinatesPage);
                    }
                    @Override
                    public void onConfigure() {
                        super.onConfigure();
                        if (employee.getRole() == Role.EMPLOYEE) {
                            setVisible(false);
                        }
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

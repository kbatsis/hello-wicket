package com.example.hellowicket;

import com.example.hellowicket.model.Role;
import com.example.hellowicket.service.IEmployeeService;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.EnumLabel;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;

public class ListPage extends BasePage {
    @SpringBean
    private IEmployeeService employeeService;

    public ListPage() {
        List<IColumn<Employee, String>> columns = new ArrayList<>();

        columns.add(new PropertyColumn<>(new Model<>("Id"), "id"));
        columns.add(new PropertyColumn<>(new Model<>("First Name"), "firstName"));
        columns.add(new PropertyColumn<>(new Model<>("Last Name"), "lastName"));
        columns.add(new PropertyColumn<>(new Model<>("Role"), "role") {
            @Override
            public void populateItem(Item<ICellPopulator<Employee>> item, String componentId, IModel<Employee> rowModel) {
                item.add(new EnumLabel<>(componentId, rowModel.getObject().getRole()));
            }
        });
        columns.add(new PropertyColumn<>(new Model<>("Supervisor"), "supervisor.getFullName()"));
        columns.add(new LinkColumn<>(Model.of("")) {
            @Override
            public Link<?> createLink(String componentId, IModel<Employee> rowModel) {
                return (Link<?>) new Link<Void>(componentId) {
                    @Override
                    public void onClick() {
                        Employee employee = rowModel.getObject();

                        EmployeePage employeePage = new EmployeePage(employee.getId());
                        setResponsePage(employeePage);
                    }
                }.setBody(new ResourceModel("editEmployee"));
            }
        });
        columns.add(new LinkColumn<>(Model.of("")) {
            @Override
            public Link<?> createLink(String componentId, IModel<Employee> rowModel) {
                return (Link<?>) new Link<Void>(componentId) {
                    @Override
                    public void onClick() {
                        Employee employee = rowModel.getObject();

                        employeeService.deleteEmployee(employee.getId());
                        ListPage listPage = new ListPage();
                        setResponsePage(listPage);
                    }
                }.setBody(new ResourceModel("removeEmployee"));
            }
        });

        columns.add(new LinkColumn<>(Model.of("")) {
            @Override
            public Link<?> createLink(String componentId, IModel<Employee> rowModel) {
                return (Link<?>) new Link<Void>(componentId) {
                    @Override
                    public void onClick() {
                        Employee employee = rowModel.getObject();

                        employeeService.deleteSupervisorWithSubordinates(employee);
                        ListPage listPage = new ListPage();
                        setResponsePage(listPage);
                    }
                }.setBody(new ResourceModel("ListPage.removeEmployeeWithSubordinatesLabel"));
            }

        });
        columns.add(new LinkColumn<>(Model.of("")) {
            @Override
            public Link<?> createLink(String componentId, IModel<Employee> rowModel) {
                return (Link<?>) new Link<Void>(componentId) {
                    @Override
                    public void onClick() {
                        Employee employee = rowModel.getObject();

                        SubordinatesPage subordinatesPage = new SubordinatesPage(employee);
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
                }.setBody(new ResourceModel("listSubordinates"));
            }
        });

        EmployeeDataProvider dataProvider = new EmployeeDataProvider();
        DataTable<Employee, String> employeesDataTable = new DataTable<>("employeesDataTable", columns, dataProvider, 10);
        employeesDataTable.addTopToolbar(new HeadersToolbar<>(employeesDataTable, null));
        employeesDataTable.addBottomToolbar(new NavigationToolbar(employeesDataTable));
        add(employeesDataTable);

//        final DataView<Employee> dataView = new DataView<>("employees", new ListDataProvider<>(employees)) {
//            @Override
//            protected void populateItem(Item item) {
//                final Employee employee = (Employee) item.getModelObject();
//                item.add(new Label("id", employee.getId()));
//                item.add(new Label("firstName", employee.getFirstName()));
//                item.add(new Label("lastName", employee.getLastName()));
//                item.add(new EnumLabel<>("role", employee.getRole()));
//                if (employee.getSupervisor() != null) {
//                    item.add(new Label("supervisor", employee.getSupervisor().getFullName()));
//                } else {
//                    item.add(new Label("supervisor", ""));
//                }
//                item.add(new Link<Void>("editEmployee") {
//                    @Override
//                    public void onClick() {
//                        EmployeePage employeePage = new EmployeePage(employee.getId());
//                        setResponsePage(employeePage);
//                    }
//                });
//                item.add(new Link<Void>("removeEmployee") {
//                    @Override
//                    public void onClick() {
//                        employeeService.deleteEmployee(employee.getId());
//                        setResponsePage(ListPage.class);
//                    }
//                });
//
//                item.add(new Link<Void>("removeEmployeeWithSubordinates") {
//                    @Override
//                    public void onClick() {
//                        employeeService.deleteSupervisorWithSubordinates(employee);
//                        setResponsePage(ListPage.class);
//                    }
//                }.setBody(new ResourceModel("ListPage.removeEmployeeWithSubordinatesLabel")));
//
//                item.add(new Link<Void>("listSubordinates") {
//                    @Override
//                    public void onClick() {
//                        SubordinatesPage subordinatesPage = new SubordinatesPage(employee, 0);
//                        setResponsePage(subordinatesPage);
//                    }
//                    @Override
//                    public void onConfigure() {
//                        super.onConfigure();
//                        if (employee.getRole() == Role.EMPLOYEE) {
//                            setVisible(false);
//                        }
//                    }
//                });
//
//            }
//        };
//
//        dataView.setItemsPerPage(10);
//        add(dataView);
//        add(new PagingNavigator("navigator", dataView));

        add(new Link<Void>("addEmployee") {
            @Override
            public void onClick() {
                setResponsePage(EmployeePage.class);
            }
        });
    }
}

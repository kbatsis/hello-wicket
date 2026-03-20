package com.example.hellowicket;

import com.example.hellowicket.service.IEmployeeService;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;

public class ListPage extends BasePage {
    @SpringBean
    private IEmployeeService employeeService;

    private int pageNumber;

    public ListPage(int pageNumber) {
        this.pageNumber = pageNumber;
        List<IColumn<Employee, String>> columns = new ArrayList<>();

        columns.add(new PropertyColumn<>(new Model<>("Id"), "id"));
        columns.add(new PropertyColumn<>(new Model<>("First Name"), "firstName"));
        columns.add(new PropertyColumn<>(new Model<>("Last Name"), "lastName"));
        columns.add(new PropertyColumn<>(new Model<>("Role"), "role"));
        columns.add(new PropertyColumn<>(new Model<>("Supervisor"), "supervisor.getFullName()"));
        columns.add(new LinkColumn<>(Model.of(""), "LinkPanelEditEmployee"));
        columns.add(new LinkColumn<>(Model.of(""), "LinkPanelRemoveEmployee"));
        columns.add(new LinkColumn<>(Model.of(""), "LinkPanelRemoveEmployeeWithSubordinates"));
        columns.add(new LinkColumn<>(Model.of(""), "LinkPanelListSubordinates"));

        EmployeeDataProvider dataProvider = new EmployeeDataProvider(pageNumber, 10);
        DataTable<Employee, String> employeesDataTable = new DataTable<>("employeesDataTable", columns, dataProvider, 10);
        employeesDataTable.addTopToolbar(new HeadersToolbar<>(employeesDataTable, null));
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

        add(new Link<Void>("previousPage") {
            @Override
            public void onClick() {
                reducePageByOne();
            }
        }.setBody(new ResourceModel("ListPage.previous")));

        add(new Link<Void>("nextPage") {
            @Override
            public void onClick() {
                increasePageByOne();
            }
        }.setBody(new ResourceModel("ListPage.next")));
    }
    private void reducePageByOne() {
        if (pageNumber > 0) {
            pageNumber--;
        }
        ListPage listPage = new ListPage(pageNumber);
        setResponsePage(listPage);
    }

    private void increasePageByOne() {
        pageNumber++;
        ListPage listPage = new ListPage(pageNumber);
        setResponsePage(listPage);
    }
}

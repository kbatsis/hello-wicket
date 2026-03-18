package com.example.hellowicket;

import com.example.hellowicket.service.IEmployeeService;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;

public class SubordinatesPage extends BasePage {
    @SpringBean
    private IEmployeeService employeeService;

    public SubordinatesPage(Employee supervisor) {
//        SubordinateListView subordinateListView = new SubordinateListView("subordinates", supervisor);
//        add(subordinateListView);

        List<IColumn<Employee, String>> columns = new ArrayList<>();
        columns.add(new PropertyColumn<>(new Model<>("Id"), "id"));
        columns.add(new PropertyColumn<>(new Model<> ("Firstname"), "firstName"));
        columns.add(new PropertyColumn<>(new Model<>("Lastname"), "lastName"));

        List<Employee> subordinates = employeeService.getSubordinates(supervisor);
        EmployeeDataProvider dataProvider = new EmployeeDataProvider(subordinates);
        DataTable<Employee, String> subordinatesDataTable = new DataTable<>("subordinatesDataTable", columns, dataProvider, 10);
        subordinatesDataTable.addTopToolbar(new HeadersToolbar<>(subordinatesDataTable, null));
        subordinatesDataTable.addBottomToolbar(new NavigationToolbar(subordinatesDataTable));
        add(subordinatesDataTable);
    }
}

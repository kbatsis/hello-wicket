package com.example.hellowicket;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.Model;

import java.util.ArrayList;
import java.util.List;

public class SubordinatesPage extends BasePage {
    private Employee supervisor;

    public SubordinatesPage(Employee supervisor) {
//        SubordinateListView subordinateListView = new SubordinateListView("subordinates", supervisor);
//        add(subordinateListView);
        this.supervisor = supervisor;

        List<IColumn<Employee, String>> columns = new ArrayList<>();
        columns.add(new PropertyColumn<>(new Model<>("Id"), "id"));
        columns.add(new PropertyColumn<>(new Model<>("Firstname"), "firstName"));
        columns.add(new PropertyColumn<>(new Model<>("Lastname"), "lastName"));

        SubordinatesDataProvider dataProvider = new SubordinatesDataProvider(supervisor);
        DataTable<Employee, String> subordinatesDataTable = new DataTable<>("subordinatesDataTable", columns, dataProvider, 10);
        subordinatesDataTable.addTopToolbar(new HeadersToolbar<>(subordinatesDataTable, null));
        add(subordinatesDataTable);
    }
}

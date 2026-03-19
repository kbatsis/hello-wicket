package com.example.hellowicket;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

import java.util.ArrayList;
import java.util.List;

public class SubordinatesPage extends BasePage {
    private int pageNumber;
    private Employee supervisor;

    public SubordinatesPage(Employee supervisor, int pageNumber) {
//        SubordinateListView subordinateListView = new SubordinateListView("subordinates", supervisor);
//        add(subordinateListView);
        this.supervisor = supervisor;
        this.pageNumber = pageNumber;

        List<IColumn<Employee, String>> columns = new ArrayList<>();
        columns.add(new PropertyColumn<>(new Model<>("Id"), "id"));
        columns.add(new PropertyColumn<>(new Model<> ("Firstname"), "firstName"));
        columns.add(new PropertyColumn<>(new Model<>("Lastname"), "lastName"));

        SubordinatesDataProvider dataProvider = new SubordinatesDataProvider(supervisor, pageNumber, 10);
        DataTable<Employee, String> subordinatesDataTable = new DataTable<>("subordinatesDataTable", columns, dataProvider, 10);
        subordinatesDataTable.addTopToolbar(new HeadersToolbar<>(subordinatesDataTable, null));
        add(subordinatesDataTable);

        add(new Link<Void>("previousPage") {
            @Override
            public void onClick() {
                reducePageByOne();
            }
        }.setBody(new ResourceModel("SubordinatesPage.previous")));

        add(new Link<Void>("nextPage") {
            @Override
            public void onClick() {
                increasePageByOne();
            }
        }.setBody(new ResourceModel("SubordinatesPage.next")));
    }

    private void reducePageByOne() {
        if (pageNumber > 0) {
            pageNumber--;
        }
        SubordinatesPage subordinatesPage = new SubordinatesPage(supervisor, pageNumber);
        setResponsePage(subordinatesPage);
    }

    private void increasePageByOne() {
        pageNumber++;
        SubordinatesPage subordinatesPage = new SubordinatesPage(supervisor, pageNumber);
        setResponsePage(subordinatesPage);
    }
}

package com.example.hellowicket;

import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;

public class SubordinatesPage extends BasePage {
    public SubordinatesPage(Employee supervisor) {
        SubordinateListView subordinateListView = new SubordinateListView("subordinates", supervisor);

        add(subordinateListView);
    }
}

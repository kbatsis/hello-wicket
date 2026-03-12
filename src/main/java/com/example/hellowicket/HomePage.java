package com.example.hellowicket;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;

@WicketHomePage
public class HomePage extends BasePage {
   public HomePage() {
       List<Employee> employees = new ArrayList<>();

       employees.add(new Employee(1, "John Doe", "john@doe.com"));
       employees.add(new Employee(2, "Jane Doe", "jane@doe.com"));
       employees.add(new Employee(3, "Jack Doe", "jack@doe.com"));
       employees.add(new Employee(4, "Jack Doe", "jack@doe.com"));

       add(new Label("welcomeMessage", "Welcome"));

       add(new Link<Void>("employeeListLink") {
           @Override
           public void onClick() {
               ListPage listPage = new ListPage(    );
               setResponsePage(listPage);
           }
       });
   }
}

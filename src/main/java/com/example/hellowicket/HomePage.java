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
       add(new Label("welcomeMessage", getString("welcome")));

       add(new Link<Void>("employeeListLink") {
           @Override
           public void onClick() {
               ListPage listPage = new ListPage(0);
               setResponsePage(listPage);
           }
       });
   }
}

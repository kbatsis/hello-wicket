package com.example.hellowicket;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class LinkPanel extends Panel {
    public static final String COMPONENT_ID = "linkId";

    public LinkPanel(String id, Link<?> link) {
        super(id);

        add(link);
    }
}
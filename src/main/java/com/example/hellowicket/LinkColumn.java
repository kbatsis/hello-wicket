package com.example.hellowicket;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

public abstract class LinkColumn<T, S>  extends AbstractColumn<T, S> {
    public LinkColumn(IModel<String> displayModel) {
        super(displayModel);
    }

    @Override
    public void populateItem(Item<ICellPopulator<T>> item, String componentId, IModel<T> model) {
        LinkPanel linkPanel = new LinkPanel(componentId, createLink(LinkPanel.COMPONENT_ID, model));
        item.add(linkPanel);
    }

    public abstract Link<?> createLink(String componentId, IModel<T> model);
}
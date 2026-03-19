package com.example.hellowicket;

import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

public class LinkColumn<T, S>  extends AbstractColumn<T, S> {
    String linkPanel;

    public LinkColumn(IModel<String> displayModel, String linkPanel) {
        super(displayModel);
        this.linkPanel = linkPanel;
    }

    @Override
    public void populateItem(Item item, String componentId, IModel model) {
        switch (linkPanel) {
            case "LinkPanelEditEmployee":
                item.add(new LinkPanelEditEmployee(componentId, model));
                break;
            case "LinkPanelRemoveEmployee":
                item.add(new LinkPanelRemoveEmployee(componentId, model));
                break;
            case "LinkPanelRemoveEmployeeWithSubordinates":
                item.add(new LinkPanelRemoveEmployeeWithSubordinates(componentId, model));
                break;
            case "LinkPanelListSubordinates":
                item.add(new LinkPanelListSubordinates(componentId, model));
                break;
        }
    }
}
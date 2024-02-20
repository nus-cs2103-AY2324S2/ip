package tes.gui;

import javafx.beans.property.SimpleStringProperty;

public class UserGuideItem {
    private final SimpleStringProperty command;
    private final SimpleStringProperty description;

    public UserGuideItem(String command, String description) {
        this.command = new SimpleStringProperty(command);
        this.description = new SimpleStringProperty(description);
    }

    public String getCommand() {
        return command.get();
    }

    public String getDescription() {
        return description.get();
    }
}

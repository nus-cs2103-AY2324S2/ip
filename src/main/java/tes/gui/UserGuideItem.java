package tes.gui;

import javafx.beans.property.SimpleStringProperty;

public class UserGuideItem {
    private final SimpleStringProperty command;
    private final SimpleStringProperty usage;

    public UserGuideItem(String command, String usage) {
        this.command = new SimpleStringProperty(command);
        this.usage = new SimpleStringProperty(usage);
    }

    public String getCommand() {
        return command.get();
    }

    public String getUsage() {
        return usage.get();
    }

}

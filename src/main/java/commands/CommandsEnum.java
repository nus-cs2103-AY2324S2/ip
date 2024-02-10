package commands;

import java.util.ArrayList;

public enum CommandsEnum {
    bye, list, mark, unmark, todo, deadline, event, delete, find, help,
    update;

    public static String getAllCommands() {
        StringBuilder commandsString = new StringBuilder();
        commandsString.append("Possible commands are: \n");

        for (CommandsEnum command : values()) {
            commandsString.append(command.name().toLowerCase()).append(", ");
        }

        // Remove the trailing ", " from the string
        if (commandsString.length() > 0) {
            commandsString.setLength(commandsString.length() - 2);
        }

        return commandsString.toString();
    }
}

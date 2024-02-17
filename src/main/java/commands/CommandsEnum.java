package commands;

/**
 * Represents different commands available in the application.
 */
public enum CommandsEnum {
    bye, list, mark, unmark, todo, deadline, event, delete, find, help,
    update;

    /**
     * Gets a string containing all possible commands.
     *
     * @return A string with a comma-separated list of all possible commands.
     */
    public static String getAllCommands() {
        StringBuilder commandsString = new StringBuilder();
        commandsString.append("Possible commands are: \n");

        for (CommandsEnum command : values()) {
            commandsString.append(command.name().toLowerCase()).append(", ");
        }

        // Removes the trailing ", " from the string
        if (commandsString.length() > 0) {
            commandsString.setLength(commandsString.length() - 2);
        }

        return commandsString.toString();
    }
}

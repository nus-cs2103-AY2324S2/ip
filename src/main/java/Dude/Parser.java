package Dude;

/**
 * A utility class to parse input strings into Command objects.
 * This class interprets user inputs and translates them into actionable
 * commands
 * for the application to execute.
 */
class Parser {

    /**
     * Parses the user input into a Command object.
     * This method identifies the action requested by the user and any associated
     * information.
     *
     * @param input The user input as a String.
     * @return A Command object representing the action to be performed.
     * @throws RuntimeException If the input does not match any known command
     *                          patterns.
     */
    public static Command getCommand(String input) {
        if (Parser.isBye(input)) {
            return new Command(Actions.BYE);
        }
        if (Parser.isList(input)) {
            return new Command(Actions.LIST);
        }
        if (input.startsWith("done ")) {
            return new Command(Actions.DONE);
        }
        if (input.startsWith("undo ")) {
            return new Command(Actions.UNDONE);
        }
        if (input.startsWith("delete ")) {
            return new Command(Actions.DELETE);
        }

        if (input.startsWith("find ")) {
            return new Command(Actions.FIND, input.substring(5).trim());
        }

        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String taskInfo = parts.length > 1 ? parts[1] : "";

        switch (command.toLowerCase()) {
        case "todo":
            return new Command(Actions.TODO, taskInfo);
        case "deadline":
            return new Command(Actions.DEADLINE, taskInfo);
        case "event":
            return new Command(Actions.EVENT, taskInfo);
        default:
            throw new RuntimeException("Invalid input. Try again!");
        }

    }

    public static boolean isBye(String input) {
        return input.equalsIgnoreCase("bye");
    }

    public static boolean isList(String input) {
        return input.equalsIgnoreCase("list");
    }


    /**
     * Extracts the task index from a done command input.
     *
     * @param input The user input as a String.
     * @return The index of the task to be marked as done.
     */
    public static int getDoneIndex(String input) {
        return Integer.parseInt(input.substring(5));
    }

    /**
     * Extracts the task index from a delete command input.
     *
     * @param input The user input as a String.
     * @return The index of the task to be deleted.
     */
    public static int getDeleteIndex(String input, Integer maxSize) {
        int index = Integer.parseInt(input.substring(7));
        if (index < 0 || index >= maxSize) {
            throw new IndexOutOfBoundsException("Task number does not exist.");
        }
        return index;
    }

}
package duke;

import exceptions.BadInputException;

/**
 * Represents a parser to parse the inputs from the user.
 */
public class Parser {
    private final String command;
    private final String arguments;

    /**
     * Constructs a parser to parse the inputs from the user.
     *
     * @param input
     *            the input from the user
     * @throws BadInputException
     *             if the input is empty
     */
    public Parser(String input) throws BadInputException {
        String[] inputParts = input.split(" ", 2);
        if (inputParts.length < 1) {
            throw new BadInputException(
                    "Please enter a command!",
                    "list, mark, unmark, todo, deadline, event, delete, bye",
                    null,
                    null);
        }
        this.command = inputParts[0];
        this.arguments = inputParts.length > 1 ? inputParts[1] : null;
    }

    /**
     * Parses the command from the inputs.
     * @return command word.
     * @throws BadInputException
     *             if the command is not specified
     */
    public String parseCommand() {
        return this.command;
    }

    /**
     * Parses the arguments from the inputs.
     *
     * @return arguments
     */
    public String parseArguments() {
        return this.arguments;
    }

    /**
     * Parses the task number from the inputs.
     * @return task number
     * @throws BadInputException
     *             if the task number is not specified, not an
     *             integer, or out of range
     */
    public int parseTaskID() throws BadInputException {
        if (this.arguments == null || arguments.isEmpty()) {
            throw new BadInputException(
                    "Please specify the task number!",
                    String.format("%s <task number>", this.command),
                    String.format("%s 1", this.arguments),
                    null);
        }

        int taskID;

        try {
            taskID = Integer.parseInt(this.arguments);
        } catch (NumberFormatException e) {
            throw new BadInputException(
                    "Task number must be an integer!",
                    String.format("%s <task number>", this.command),
                    String.format("%s 1", this.arguments),
                    this.arguments);
        }

        return taskID;
    }

    /**
     * Parses the date from provided string.
     * @return date
     * @throws BadInputException
     *             if the date is not specified or not in the
     *             correct format
     */
    public static java.time.LocalDate parseDate(String dateString) throws BadInputException {
        try {
            return java.time.LocalDate.parse(dateString);
        } catch (java.time.format.DateTimeParseException e) {
            throw new BadInputException(
                    "Date must be in the format YYYY-MM-DD!",
                    null,
                    "2020-01-01",
                    dateString);
        }
    }
}

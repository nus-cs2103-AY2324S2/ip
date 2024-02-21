package pyrite;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.stream.IntStream;

import pyrite.command.AddCommand;
import pyrite.command.Command;
import pyrite.command.DeleteCommand;
import pyrite.command.ExitCommand;
import pyrite.command.FilteredListCommand;
import pyrite.command.ListCommand;
import pyrite.command.StatusCommand;
import pyrite.task.Deadline;
import pyrite.task.Event;
import pyrite.task.Task;
import pyrite.task.ToDo;

/**
 * Parses user input and returns a Command.
 */
public class Parser {
    public static final String EMPTY_DESCRIPTION_ERROR_MESSAGE = "The description cannot be empty. "
            + "Add the description after the command.";
    public static final String INVALID_DATETIME_FORMAT_ERROR_MESSAGE = "Use yyyy-mm-ddThh:mm.";
    public static final String UNKNOWN_COMMAND_ERROR_MESSAGE = "Unknown command. "
            + "Valid commands are 'todo', 'deadline', 'event', 'mark', 'unmark', 'delete',"
            + " 'bye'";
    public static final String IMPOSSIBLE_DATES_ERROR_MESSAGE = "Start date cannot be after end date.";
    public static final String INVALID_ID_ERROR_MESSAGE = "Provide a valid id to mark/un-mark.";
    private static int parseID(String[] parameters) {
        int id;
        try {
            id = Integer.parseInt(parameters[1]) - 1;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new PyriteException(INVALID_ID_ERROR_MESSAGE);
        }
        return id;
    }
    private static String getStringFromParameter(String[] parameters, int stringStart, int stringEnd) {
        return String.join(
                " ",
                Arrays.copyOfRange(parameters, stringStart, stringEnd)
        );
    }
    private static String getDescriptionFromParameters(String[] parameters, int descStart, int descEnd) {
        String description = getStringFromParameter(parameters, descStart, descEnd);
        assertNonEmptyDescription(description);
        return description;
    }
    private static void assertDateTimeOrder(LocalDateTime ...dates) {
        for (int i = 0; i < dates.length - 1; i++) {
            if (dates[i].isAfter(dates[i + 1])) {
                throw new PyriteException(IMPOSSIBLE_DATES_ERROR_MESSAGE);
            }
        }
    }
    private static LocalDateTime getDateTimeFromString(String dateTimeString) {
        try {
            return LocalDateTime.parse(dateTimeString);
        } catch (DateTimeParseException e) {
            throw new PyriteException("'" + dateTimeString + "' is not a valid datetime. "
                    + INVALID_DATETIME_FORMAT_ERROR_MESSAGE);
        }
    }
    private static void assertNonEmptyDescription(String description) {
        if (description.equals("")) {
            throw new PyriteException(EMPTY_DESCRIPTION_ERROR_MESSAGE);
        }
    }

    // Solution below reuses code from GitHub Copilot.
    /**
     * Finds the index of a string in an array.
     * Used to find the index of a command in the user input.
     *
     * @param toSearch Array to search.
     * @param toFind   String to find.
     * @return Index of the string in the array.
     */
    private static int findCommand(String[] toSearch, String toFind) {
        int id = IntStream.range(0, toSearch.length)
                .filter(i -> toSearch[i].equals(toFind))
                .findFirst()
                .orElse(-1);
        if (id == -1) {
            throw new PyriteException("Missing " + toFind + " in command.");
        }
        return id;
    }

    // Part of solution below inspired by
    // https://stackoverflow.com/questions/31690570/java-scanner-command-system
    // https://stackoverflow.com/questions/4822256/java-is-there-an-easy-way-to-select-a-subset-of-an-array
    /**
     * Parses user input and returns a Command.
     *
     * @param commandString User input.
     * @return Command to be executed.
     */
    public static Command parse(String commandString) throws PyriteException {
        String[] parameters = commandString.split(" ");
        String commandType = parameters[0];
        switch (commandType) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            int id = parseID(parameters);
            return new StatusCommand(id, Task.Status.DONE);
        case "unmark":
            id = parseID(parameters);
            return new StatusCommand(id, Task.Status.NOT_DONE);
        case "delete":
            id = parseID(parameters);
            return new DeleteCommand(id);
        case "find":
            String keyword = getStringFromParameter(parameters, 1, parameters.length);
            return new FilteredListCommand(keyword);
        case "todo":
            String description = getDescriptionFromParameters(parameters, 1, parameters.length);
            return new AddCommand(new ToDo(description));
        case "deadline":
            int byID = findCommand(parameters, "/by");
            description = getDescriptionFromParameters(parameters, 1, byID);
            String byString = getStringFromParameter(parameters, byID + 1, parameters.length);
            LocalDateTime by = getDateTimeFromString(byString);
            return new AddCommand(new Deadline(description, by));
        case "event":
            int fromID = findCommand(parameters, "/from");
            int toID = findCommand(parameters, "/to");

            int descEnd = Math.min(fromID, toID);
            description = getDescriptionFromParameters(parameters, 1, descEnd);

            String fromString = getStringFromParameter(
                    parameters, fromID + 1, toID < fromID ? parameters.length : toID);
            String toString = getStringFromParameter(
                    parameters, toID + 1, fromID < toID ? parameters.length : fromID);

            LocalDateTime start = getDateTimeFromString(fromString);
            LocalDateTime end = getDateTimeFromString(toString);
            assertDateTimeOrder(start, end);

            return new AddCommand(new Event(description, start, end));
        default:
            throw new PyriteException(UNKNOWN_COMMAND_ERROR_MESSAGE);
        }

    }


}

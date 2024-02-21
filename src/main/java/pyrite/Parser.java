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
import pyrite.command.UnknownCommand;
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
    public static final String MISSING_DEADLINE_ERROR_MESSAGE = "Incomplete command. Add deadline using '/by'.";
    public static final String INVALID_DATETIME_FORMAT_ERROR_MESSAGE = "Invalid datetime format. Use yyyy-mm-ddThh:mm.";
    public static final String MISSING_START_OR_END_DATETIME_ERROR_MESSAGE = "Incomplete Command. "
            + "Add start and end dates using '/from' and '/to'.";
    public static final String UNKNOWN_COMMAND_ERROR_MESSAGE = "Unknown command. "
            + "Valid commands are 'todo', 'deadline', 'event', 'mark', 'unmark', 'delete',"
            + " 'bye'";
    public static final String START_DATE_AFTER_END_ERROR_MESSAGE = "Start date cannot be after end date.";

    private static int parseID(String[] parameters) {
        int id;
        try {
            id = Integer.parseInt(parameters[1]) - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Provide a valid id to mark/unmark.");
        }
        return id;
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
        return IntStream.range(0, toSearch.length)
                .filter(i -> toSearch[i].equals(toFind))
                .findFirst()
                .orElse(-1);
    }

    // Solution below inspired by
    // https://stackoverflow.com/questions/31690570/java-scanner-command-system
    // https://stackoverflow.com/questions/4822256/java-is-there-an-easy-way-to-select-a-subset-of-an-array

    /**
     * Parses user input and returns a Command.
     *
     * @param commandString User input.
     * @return Command to be executed.
     */
    public static Command parse(String commandString) {
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
            String keyword = String.join(" ",
                    Arrays.copyOfRange(parameters, 1, parameters.length));
            return new FilteredListCommand(keyword);
        case "todo":
        case "deadline":
        case "event":
            int byID = findCommand(parameters, "/by");
            int fromID = findCommand(parameters, "/from");
            int toID = findCommand(parameters, "/to");
            int descEnd = parameters.length;
            if (byID != -1) {
                if (fromID == -1 && toID == -1) {
                    descEnd = byID;
                } else if (fromID != -1 || toID != -1) {
                    return new UnknownCommand(commandString,
                            "Wrong use of '/from' or '/to' or '/by' together.");
                }
            }
            if (fromID != -1 && toID != -1) {
                descEnd = Math.min(fromID, toID);
            }
            String description = String.join(
                    " ",
                    Arrays.copyOfRange(parameters, 1, descEnd)
            );
            if (description.equals("")) {
                return new UnknownCommand(commandString,
                        EMPTY_DESCRIPTION_ERROR_MESSAGE);
            }
            switch (commandType) {
            case "todo":
                return new AddCommand(new ToDo(description));
            case "deadline":
                if (byID == -1) {
                    return new UnknownCommand(commandString,
                            MISSING_DEADLINE_ERROR_MESSAGE);
                }
                String byString = String.join("",
                        Arrays.copyOfRange(parameters,
                                byID + 1,
                                parameters.length));
                LocalDateTime by;
                try {
                    by = LocalDateTime.parse(byString);
                } catch (DateTimeParseException e) {
                    return new UnknownCommand(commandString,
                            INVALID_DATETIME_FORMAT_ERROR_MESSAGE);
                }
                return new AddCommand(new Deadline(description, by));
            case "event":
                if (fromID == -1 | toID == -1) {
                    return new UnknownCommand(commandString,
                            MISSING_START_OR_END_DATETIME_ERROR_MESSAGE);
                }
                String fromString;
                String toString;
                if (fromID < toID) {
                    fromString = String.join("",
                            Arrays.copyOfRange(parameters,
                                    fromID + 1,
                                    toID));
                    toString = String.join("",
                            Arrays.copyOfRange(parameters,
                                    toID + 1,
                                    parameters.length));
                } else {
                    fromString = String.join("",
                            Arrays.copyOfRange(parameters,
                                    fromID + 1,
                                    parameters.length));
                    toString = String.join("",
                            Arrays.copyOfRange(parameters,
                                    toID + 1,
                                    fromID));
                }
                LocalDateTime start;
                LocalDateTime end;
                try {
                    start = LocalDateTime.parse(fromString);
                    end = LocalDateTime.parse(toString);
                } catch (DateTimeParseException e) {
                    return new UnknownCommand(commandString,
                            INVALID_DATETIME_FORMAT_ERROR_MESSAGE);
                }
                if (start.isAfter(end)) {
                    return new UnknownCommand(commandString,
                            START_DATE_AFTER_END_ERROR_MESSAGE);
                }
                return new AddCommand(new Event(description, start, end));
            default:
                return new UnknownCommand(commandString,
                        UNKNOWN_COMMAND_ERROR_MESSAGE);
            }
        default:
            return new UnknownCommand(commandString,
                    UNKNOWN_COMMAND_ERROR_MESSAGE);
        }
    }
}

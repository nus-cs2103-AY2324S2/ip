package ciara.parser;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ciara.commands.AddCommand;
import ciara.commands.ArchiveCommand;
import ciara.commands.Command;
import ciara.commands.DeleteCommand;
import ciara.commands.ExitCommand;
import ciara.commands.FindCommand;
import ciara.commands.ListCommand;
import ciara.commands.MarkCommand;
import ciara.exceptions.CiaraException;
import ciara.exceptions.InvalidArgumentException;
import ciara.exceptions.InvalidCommandException;
import ciara.exceptions.MissingArgumentException;
import ciara.storage.Deadline;
import ciara.storage.Event;
import ciara.storage.Todo;

/**
 * The Parser class handles the making sense of user commands
 *
 * @author Ryan NgWH
 */
public class Parser {
    /**
     * Converts user input date (in format 'YYYY/MM/DD hh:mm') to Instant
     *
     * @param date Date to be converted (in format 'YYYY/MM/DD')
     * @param time Time to be converted (in format 'hh:mm')
     *
     * @return Instant of the specified datetime
     */
    public static Instant userDateToInstant(String date, String time)
            throws NumberFormatException, StringIndexOutOfBoundsException, DateTimeException {
        return LocalDateTime.of(
                Integer.parseInt(date.substring(0, 4)),
                Integer.parseInt(date.substring(5, 7)),
                Integer.parseInt(date.substring(8, 10)),
                Integer.parseInt(time.substring(0, 2)),
                Integer.parseInt(time.substring(3, 5)))
                .toInstant(ZoneOffset.of("+8"));
    }

    /**
     * Parses a string and return a command if valid
     *
     * @param input String to be parsed
     *
     * @return A valid command for the application
     */
    public static Command parse(String input) throws CiaraException {
        // Remove whitespaces from input
        input = input.strip();

        // Split input
        ArrayList<String> splitInput = new ArrayList<String>(Arrays.asList(input.split(" ")));

        // Remove unncessary whitespace
        splitInput.removeIf(item -> item == null || "".equals(item));

        switch (splitInput.get(0).toLowerCase()) {
        case "bye": // Exit
            return new ExitCommand();

        case "list": // List tasks
            return parseList(splitInput);

        case "mark": // Mark task
            return parseMark(splitInput);

        case "unmark": // Unmark task
            return parseUnmark(splitInput);

        case "delete": // Delete task
            return parseDelete(splitInput);

        case "todo":
            return parseTodo(splitInput);

        case "deadline":
            return parseDeadline(splitInput);

        case "event":
            return parseEvent(splitInput);

        case "find":
            return parseFind(splitInput);

        case "archive":
            return parseArchive(splitInput);

        case "unarchive":
            return parseUnarchive(splitInput);

        default:
            throw new InvalidCommandException(String.format("Unknown command '%s'", splitInput.get(0)));
        }
    }

    /**
     * Parses a user input to list tasks in the application
     *
     * @param splitInput Options from the user input
     *
     * @return Application command associated with the user input
     *
     * @throws InvalidArgumentException Invalid argument was entered by the user
     */
    private static Command parseList(ArrayList<String> splitInput) throws InvalidArgumentException {
        if (splitInput.size() > 1) { // List filtered tasks
            boolean isArchived = false;
            if (splitInput.get(1).equals("/archive")) {
                isArchived = true;
                splitInput.remove(1);
            }

            if (splitInput.size() <= 1) {
                return new ListCommand(isArchived);
            }

            switch (splitInput.get(1)) {
            case "/date": // Filter by date
                try {
                    Instant filterDate = userDateToInstant(splitInput.get(2), "00:00");

                    return new ListCommand(filterDate, isArchived);
                } catch (NumberFormatException | DateTimeException | IndexOutOfBoundsException e) {
                    throw new InvalidArgumentException(
                            "Date/time format is invalid. Please enter the date/time in the format 'YYYY/MM/DD'");
                }

            default: // Invalid filter
                throw new InvalidArgumentException(
                        String.format("Unknown argument '%s' for the 'list' command", splitInput.get(1)));
            }
        } else { // Return full list
            return new ListCommand(false);
        }
    }

    /**
     * Parses a user input to mark tasks in the application
     *
     * @param splitInput Options from the user input
     *
     * @return Application command associated with the user input
     *
     *
     * @throws MissingArgumentException Missing argument in the user input
     * @throws InvalidArgumentException Invalid argument was entered by the user
     */
    private static Command parseMark(ArrayList<String> splitInput)
            throws MissingArgumentException, InvalidArgumentException {
        if (splitInput.size() <= 1) {
            throw new MissingArgumentException("Missing argument - Index of task required");
        }

        try {
            return new MarkCommand(Integer.parseInt(splitInput.get(1)) - 1, true);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Index to mark is not an integer");
        }
    }

    /**
     * Parses a user input to unmark tasks in the application
     *
     * @param splitInput Options from the user input
     *
     * @return Application command associated with the user input
     *
     *
     * @throws MissingArgumentException Missing argument in the user input
     * @throws InvalidArgumentException Invalid argument was entered by the user
     */
    private static Command parseUnmark(ArrayList<String> splitInput)
            throws MissingArgumentException, InvalidArgumentException {
        if (splitInput.size() <= 1) {
            throw new MissingArgumentException("Missing argument - Index of task required");
        }

        try {
            return new MarkCommand(Integer.parseInt(splitInput.get(1)) - 1, false);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Index to unmark is not an integer");
        }
    }

    /**
     * Parses a user input to delete tasks in the application
     *
     * @param splitInput Options from the user input
     *
     * @return Application command associated with the user input
     *
     *
     * @throws MissingArgumentException Missing argument in the user input
     * @throws InvalidArgumentException Invalid argument was entered by the user
     */
    private static Command parseDelete(ArrayList<String> splitInput)
            throws MissingArgumentException, InvalidArgumentException {
        if (splitInput.size() <= 1) {
            throw new MissingArgumentException("Missing argument - Index of task required");
        }

        try {
            return new DeleteCommand(Integer.parseInt(splitInput.get(1)) - 1, false);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Index to delete is not an integer");
        }
    }

    /**
     * Parses a user input to create a todo task in the application
     *
     * @param splitInput  Options from the user input
     * @param description Description of the task
     *
     * @return Application command associated with the user input
     *
     *
     * @throws MissingArgumentException Missing argument in the user input
     */
    private static Command parseTodo(ArrayList<String> splitInput) throws MissingArgumentException {
        if (splitInput.size() <= 1) {
            throw new MissingArgumentException("Missing argument - Description of a todo cannot be empty");
        }

        String description = String.join(" ", splitInput.subList(1, splitInput.size()));

        // Return new add todo command
        return new AddCommand(new Todo(description));
    }

    /**
     * Parses a user input to create a deadline task in the application
     *
     * @param splitInput  Options from the user input
     * @param description Description of the task
     *
     * @return Application command associated with the user input
     *
     * @throws MissingArgumentException Missing argument in the user input
     * @throws InvalidArgumentException Invalid argument was entered by the user
     */
    private static Command parseDeadline(ArrayList<String> splitInput)
            throws MissingArgumentException, InvalidArgumentException {
        // Get arguments
        List<String> arguments = splitInput.subList(1, splitInput.size());

        // Get index of '/by' argument
        int byIndex = -1;
        for (int i = 0; i < arguments.size(); i++) {
            if (arguments.get(i).equals("/by")) {
                byIndex = i;
                break;
            }
        }

        if (byIndex == -1) {
            throw new MissingArgumentException("Missing Argument - Argument '/by' missing");
        }

        // Extract task description & due date
        String description = String.join(" ", arguments.subList(0, byIndex));

        try {
            String date = arguments.get(byIndex + 1);
            String time = arguments.get(byIndex + 2);

            // Create new add deadline command
            return new AddCommand(new Deadline(description, Parser.userDateToInstant(date, time)));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentException(
                    "Date/time format is invalid. Please enter the date/time in the format 'YYYY/MM/DD hh:mm'");
        }
    }

    /**
     * Parses a user input to create an event task in the application
     *
     * @param splitInput  Options from the user input
     * @param description Description of the task
     *
     * @return Application command associated with the user input
     *
     * @throws MissingArgumentException Missing argument in the user input
     * @throws InvalidArgumentException Invalid argument was entered by the user
     */
    private static Command parseEvent(ArrayList<String> splitInput)
            throws MissingArgumentException, InvalidArgumentException {
        // Get arguments
        List<String> arguments = splitInput.subList(1, splitInput.size());

        // Get index of '/from' and '/to' argument
        int fromIndex = -1;
        int toIndex = -1;
        for (int i = 0; i < arguments.size(); i++) {
            if (fromIndex != -1 && toIndex != -1) {
                break;
            }

            if (fromIndex == -1 && arguments.get(i).equals("/from")) {
                fromIndex = i;
            }

            if (toIndex == -1 && arguments.get(i).equals("/to")) {
                toIndex = i;
            }
        }

        if (fromIndex == -1) {
            throw new MissingArgumentException("Missing Argument - Argument '/from' missing");
        } else if (toIndex == -1) {
            throw new MissingArgumentException("Missing Argument - Argument '/to' missing");
        }

        // Extract task description
        String description = String.join(" ", arguments.subList(0, fromIndex));

        try {
            // Extract start date
            String fromDate = arguments.get(fromIndex + 1);
            String fromTime = arguments.get(fromIndex + 2);

            // Extract end date
            String toDate = arguments.get(toIndex + 1);
            String toTime = arguments.get(toIndex + 2);

            // Create new add event command
            return new AddCommand(new Event(description, Parser.userDateToInstant(fromDate, fromTime),
                    Parser.userDateToInstant(toDate, toTime)));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentException(
                    "Date/time format is invalid. Please enter the date/time in the format 'YYYY/MM/DD hh:mm'");
        }
    }

    /**
     * Parses a user input to search for a task in the application
     *
     * @param splitInput  Options from the user input
     * @param description Description of the task
     *
     * @return Application command associated with the user input
     */
    private static Command parseFind(ArrayList<String> splitInput) {
        if (splitInput.size() <= 1) { // no arguments
            return new FindCommand(false);
        }

        String keyword = String.join(" ", splitInput.subList(1, splitInput.size()));

        // Return new add todo command
        return new FindCommand(keyword, false);
    }

    /**
     * Parses a user input to archive a task in the application
     *
     * @param splitInput  Options from the user input
     * @param description Description of the task
     *
     * @return Application command associated with the user input
     *
     * @throws MissingArgumentException Missing argument in the user input
     * @throws InvalidArgumentException Invalid argument was entered by the user
     */
    private static Command parseArchive(ArrayList<String> splitInput)
            throws MissingArgumentException, InvalidArgumentException {
        if (splitInput.size() <= 1) {
            throw new MissingArgumentException("Missing argument - Index of task required");
        }

        try {
            return new ArchiveCommand(Integer.parseInt(splitInput.get(1)) - 1, true);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Index to archive is not an integer");
        }
    }

    /**
     * Parses a user input to unarchive a task in the application
     *
     * @param splitInput  Options from the user input
     * @param description Description of the task
     *
     * @return Application command associated with the user input
     *
     * @throws MissingArgumentException Missing argument in the user input
     * @throws InvalidArgumentException Invalid argument was entered by the user
     */
    private static Command parseUnarchive(ArrayList<String> splitInput)
            throws MissingArgumentException, InvalidArgumentException {
        if (splitInput.size() <= 1) {
            throw new MissingArgumentException("Missing argument - Index of task required");
        }

        try {
            return new ArchiveCommand(Integer.parseInt(splitInput.get(1)) - 1, false);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Index to unarchive is not an integer");
        }
    }
}

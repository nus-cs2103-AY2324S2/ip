package bmo.util;

import bmo.command.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser class to handle parsing of user input.
 */
public class Parser {


    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input String containing the user input.
     * @return Command object corresponding to the user input.
     * @throws IOException If there is an error reading the file.
     */
    public static Command parse(String input) throws IOException {
        String[] inputArr = input.split(" ");
        String keyword = inputArr[0];
        switch (keyword) {
            case "hi":
                return new GreetCommand();
            case "bye":
                return new ExitCommand();
            case "log":
                return new LogCommand();
            case "sort":
                return new SortCommand();
            case "done":
                return parseDoneCommand(input);
            case "redo":
                return parseRedoCommand(input);
            case "delete":
                return parseDeleteCommand(input);
            case "find":
                return parseFindCommand(input);
            case "todo":
                return parseToDoCommand(input);
            case "due":
                return parseDueCommand(input);
            case "event":
                return parseEventCommand(input);
            case "commands":
                return new GuideCommand();
            default:
                return new DefaultCommand(0);
        }
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input String containing the user input.
     * @return Command object corresponding to the user input.
     */
    private static Command parseDoneCommand(String input) {
        Command output;

        String doneFormat = "^done\\s+(\\d+)$";
        Pattern donePattern = Pattern.compile(doneFormat);
        Matcher doneMatcher = donePattern.matcher(input);

        if (doneMatcher.matches()) {
            String index = doneMatcher.group(1);
            output = new DoneCommand(index);
        } else {
            output = new DefaultCommand(0);
        }

        return output;
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input String containing the user input.
     * @return Command object corresponding to the user input.
     */
    private static Command parseRedoCommand(String input) {
        Command output;

        String redoFormat = "^redo\\s+(\\d+)$";
        Pattern redoPattern = Pattern.compile(redoFormat);
        Matcher redoMatcher = redoPattern.matcher(input);

        if (redoMatcher.matches()) {
            String index = redoMatcher.group(1);
            output = new RedoCommand(index);
        } else {
            output = new DefaultCommand(0);
        }

        return output;
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input String containing the user input.
     * @return Command object corresponding to the user input.
     */
    private static Command parseDeleteCommand(String input) {
        Command output;

        String deleteFormat = "^delete\\s+(\\d+)$";
        Pattern deletePattern = Pattern.compile(deleteFormat);
        Matcher deleteMatcher = deletePattern.matcher(input);

        if (deleteMatcher.matches()) {
            String index = deleteMatcher.group(1);
            output = new DeleteCommand(index);
        } else {
            output = new DefaultCommand(0);
        }

        return output;
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input String containing the user input.
     * @return Command object corresponding to the user input.
     */
    private static Command parseFindCommand(String input) {
        Command output;
     
        String findFormat = "^find\\s+(\\S+(\\s+\\w+)*)$";
        Pattern findPattern = Pattern.compile(findFormat);
        Matcher findMatcher = findPattern.matcher(input);

        if (findMatcher.matches()) {
            String key = findMatcher.group(1);
            output = new FindCommand(key);

        } else {
            output = new DefaultCommand(1);
        }

        return output;
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input String containing the user input.
     * @return Command object corresponding to the user input.
     */
    private static Command parseToDoCommand(String input) {
        Command output;

        String toDoFormat = "^todo\\s+(\\S+(\\s+\\w+)*)$";
        Pattern toDoPattern = Pattern.compile(toDoFormat);
        Matcher toDoMatcher = toDoPattern.matcher(input);

        if (toDoMatcher.matches()) {
            String desc = toDoMatcher.group(1);
            output = new ToDoCommand(desc);
        } else {
            output = new DefaultCommand(1);
        }

        return output;
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input String containing the user input.
     * @return Command object corresponding to the user input.
     */
    private static Command parseDueCommand(String input) {
        Command output;

        String dueFormat = "^due\\s+(\\w+(\\s+\\w+)*)\\s+/by\\s+(\\S+(\\s+\\w+|/)*)$";
        Pattern duePattern = Pattern.compile(dueFormat);
        Matcher dueMatcher = duePattern.matcher(input);

        if (dueMatcher.matches()) {
            String desc = dueMatcher.group(1);
            String by = dueMatcher.group(3);

            LocalDateTime byDateTime = formatDateTime(by);

            if (byDateTime == null) {
                output = new DefaultCommand(2);
            } else {
                output = new DueCommand(desc, byDateTime);
            }
        } else {
            output = new DefaultCommand(1);
        }

        return output;
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input String containing the user input.
     * @return Command object corresponding to the user input.
     */
    private static Command parseEventCommand(String input) {
        Command output;

        String eventFormat = "^event\\s+(\\w+(\\s+\\w+)*)\\s+/from\\s+(\\S+(\\s+\\w+|/)*)\\s+/to\\s+(\\S+(\\s+\\w+|/)*)$";
        Pattern eventPattern = Pattern.compile(eventFormat);
        Matcher eventMatcher = eventPattern.matcher(input);

        if (eventMatcher.matches()) {
            String desc = eventMatcher.group(1);
            String start = eventMatcher.group(3);
            String end = eventMatcher.group(5);

            LocalDateTime startDateTime = formatDateTime(start);
            LocalDateTime endDateTime = formatDateTime(end);

            if (startDateTime == null || endDateTime == null) {
                output = new DefaultCommand(2);
            } else {
                output = new EventCommand(desc, startDateTime, endDateTime);
            }
        } else {
            output = new DefaultCommand(1);
        }

        return output;
    }

    /**
     * Formats the input string into a LocalDateTime object.
     *
     * @param input String containing the date and time.
     * @return LocalDateTime object containing the date and time.
     */
    public static LocalDateTime formatDateTime(String input) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(input, formatter);
        } catch (Exception e) {
            return null;
        }
    }

}

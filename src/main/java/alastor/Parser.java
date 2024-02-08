package alastor;

import alastor.command.*;
import alastor.task.Deadline;
import alastor.task.Event;
import alastor.task.Task;
import alastor.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a parser that parses the user input and file input.
 */
public class Parser {

    public enum ParseType {
        FILE {
            @Override
            public String getErrorMessage() {
                return "I'm afraid the file I tried reading is corrupted, my dear.\n";
            }
        },
        COMMAND {
            @Override
            public String getErrorMessage() {
                return "I'm afraid I've encountered an error while parsing the command, my dear.\n";
            }
        };

        public abstract String getErrorMessage();
    }

    /**
     * Converts a string to an integer.
     *
     * @param integer The string to be converted.
     * @return The integer value of the string.
     * @throws AlastorException If the string is not in the correct format.
     */
    public static Integer stringToInt(String integer) throws AlastorException {
        try{
            return Integer.parseInt(integer);
        } catch (NumberFormatException e) {
            throw new AlastorException(ParseType.COMMAND.getErrorMessage() +
                    "The number might be in an incorrect format, it should be an integer");
        }
    }

    /**
     * Converts a string to a LocalDateTime object.
     *
     * @param dateTime The string to be converted.
     * @param type The type of the string.
     * @return The LocalDateTime object of the string.
     * @throws AlastorException If the string is not in the correct format.
     */
    public static LocalDateTime stringToDateTime(String dateTime, ParseType type) throws AlastorException {
        try{
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        } catch (DateTimeParseException e) {
            throw new AlastorException(type.getErrorMessage() +
                    "The date and time might be in an incorrect format, it should be dd-MM-yyyy HH:mm");
        }
    }

    /**
     * Checks if the parameters are in the correct format.
     *
     * @param arguments The parameters to be checked.
     * @param expectedNumber The expected number of parameters.
     * @param type The type of the parameters.
     * @throws AlastorException If the parameters are not in the correct format.
     */
    public static void checkParameters(String[] arguments, int expectedNumber, ParseType type) throws AlastorException {
        if (arguments.length != expectedNumber) {
            if (arguments.length < expectedNumber) {
                throw new AlastorException(type.getErrorMessage()
                        + "There might be some missing parameters or invalid separators");
            } else {
                throw new AlastorException(type.getErrorMessage()
                        + "There might be some extra invalid parameters");
            }
        }
        for (String i : arguments) {
            if (i.isBlank()) {
                throw new AlastorException(type.getErrorMessage()
                        + "There might be some empty parameters");
            }
        }
    }

    /**
     * Parses the file input and adds the tasks to the list.
     *
     * @param line The line to be parsed.
     * @param list The list to add the tasks to.
     * @param index The index of the task.
     * @throws AlastorException If the file input is not in the correct format.
     */
    public static void parseFile(String line, ArrayList<Task> list, int index) throws AlastorException {
        ParseType type = ParseType.FILE;
        String[] tasks = line.split("\\| ", 5);
        switch (tasks[0].trim()) {
            case "T":
                checkParameters(tasks, 3, type);
                list.add(new ToDo(tasks[2].trim()));
                break;
            case "D":
                checkParameters(tasks, 4, type);
                list.add(new Deadline(tasks[2].trim(), stringToDateTime(tasks[3].trim(), ParseType.FILE)));
                break;
            case "E":
                checkParameters(tasks, 5, type);
                list.add(new Event(tasks[2].trim(), stringToDateTime(tasks[3].trim(), ParseType.FILE),
                        stringToDateTime(tasks[4].trim(), ParseType.FILE)));
                break;
            default:
                throw new AlastorException(type.getErrorMessage()
                        + "There might be some invalid task types or separators");
        }
        String marking = tasks[1].trim();
        if (marking.equals("0") || marking.equals("1")) {
            if (marking.equals("1")) {
                list.get(index).mark();
            }
        } else {
            throw new AlastorException(type.getErrorMessage()
                    + "There might be some invalid marking values");
        }
    }

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param command The user input to be parsed.
     * @return The corresponding command.
     * @throws AlastorException If the user input is not in the correct format.
     */
    public static Command parseCommand(String command) throws AlastorException {
        ParseType type = ParseType.COMMAND;
        String[] tasks = command.trim().split(" ", 2);
        switch (tasks[0]) {
            case "list":
                return new ListCommand();
            case "mark":
                checkParameters(tasks, 2, type);
                return new MarkCommand(stringToInt(tasks[1]), true);
            case "unmark":
                checkParameters(tasks , 2, type);
                return new MarkCommand(stringToInt(tasks[1]), false);
            case "todo":
                checkParameters(tasks, 2, type);
                return new AddCommand(new ToDo(tasks[1]));
            case "deadline":
                checkParameters(tasks, 2, type);
                String[] deadline = tasks[1].split("/by ", 2);
                checkParameters(deadline, 2, type);
                return new AddCommand(new Deadline(deadline[0].trim(), stringToDateTime(deadline[1].trim(),
                        ParseType.COMMAND)));
            case "event":
                checkParameters(tasks, 2, type);
                String[] event = tasks[1].split("/from|/to", 3);
                checkParameters(event, 3, type);
                return new AddCommand(new Event(event[0].trim(), stringToDateTime(event[1].trim(), ParseType.COMMAND),
                        stringToDateTime(event[2].trim(), ParseType.COMMAND)));
            case "delete":
                checkParameters(tasks, 2, type);
                return new DeleteCommand(stringToInt(tasks[1]));
            case "bye":
                return new ExitCommand();
            default:
                return new InvalidCommand();
        }
    }
}

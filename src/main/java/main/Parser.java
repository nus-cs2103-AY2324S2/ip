package main;

import static utils.InputUtil.getCommandType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import commands.Command;
import commands.CommandError;
import commands.Constants;
import commands.CreateDeadline;
import commands.CreateEvent;
import commands.CreateTodo;
import commands.DeleteTask;
import commands.Find;
import commands.Help;
import commands.ListTasks;
import commands.MarkTask;
import commands.Snooze;
import commands.UnmarkTask;
import exception.*;
import objects.TaskList;

/**
 * The Parser class is responsible for interpreting user input and creating corresponding Command objects.
 * It parses user commands and executes the appropriate actions, interacting with the TaskList and displaying
 * messages through the EncaseLines view.
 */
public class Parser {
    /**
     * Parses the user input and executes the corresponding command.
     *
     * @param input The user input to be parsed.
     * @param tasks The TaskList to perform operations on.
     * @return
     */
    public static Command parse(String input, TaskList tasks) {
        Command command;
        try {
            input = input.trim().toLowerCase();
            String commandType = getCommandType(input);
            assert commandType != null : "Command type cannot be null";

            switch (commandType) {
            case Constants.LIST:
                command = new ListTasks(tasks);
                break;

            case Constants.MARK:
                command = new MarkTask(tasks, parseIndex(input));
                break;

            case Constants.UNMARK:
                command = new UnmarkTask(tasks, parseIndex(input));
                break;

            case Constants.DELETE:
                command = new DeleteTask(tasks, parseIndex(input));
                break;


            case Constants.TODO:
                command = new CreateTodo(tasks, parseToDo(input));
                break;

            case Constants.DEADLINE:
                command = new CreateDeadline(tasks, parseDeadline(input));
                break;

            case Constants.EVENT:
                command = new CreateEvent(tasks, parseEvent(input));
                break;

            case Constants.HELP:
                command = new Help();
                break;

            case Constants.FIND:
                command = new Find(tasks, parseName(input));
                break;

            case Constants.SNOOZE:
                command = new Snooze(tasks, parseIndex(input));
                break;

            default:
                throw new InvalidCommandException();
            }

        } catch (DukeException e) {
            command = new CommandError(e.getMessage());
        }

        return command;
    }

    /**
     * Parses the index from the user input to be used in commands requiring an index.
     *
     * @param input The user input containing the command and index.
     * @return The parsed index.
     * @throws InvalidIndexException If the index is invalid or missing.
     */
    public static int parseIndex(String input) throws InvalidIndexException, InvalidIndexInputException {
        String[] parts = input.split("\\s+");

        if (parts.length < 2) {
            throw new InvalidIndexException();
        }

        try {
            return Integer.parseInt(parts[1]) - 1;

        } catch (NumberFormatException e) {
            throw new InvalidIndexInputException();
        }
    }

    /**
     * Parses the description from the user input for creating a new Todo task.
     *
     * @param input The user input containing the command and description.
     * @return The parsed description.
     * @throws InvalidTodoException   If the description is missing.
     * @throws InvalidCommandException If the command is invalid.
     */
    public static String parseName(String input) throws InvalidTodoException, InvalidCommandException {
        String[] details = input.split(" ", 2);
        assert details.length >= 2 : "Invalid command format";

        if (details.length < 2) {
            throw new InvalidCommandException();
        }

        return details[1].trim();
    }

    /**
     * Parses the input string to extract the details of a ToDo task.
     *
     * @param input The input string containing the details of the ToDo task.
     * @return The details of the ToDo task.
     * @throws InvalidTodoException If the input string is not in the correct format.
     */
    public static String parseToDo(String input) throws InvalidTodoException {
        String[] details = input.split(" ", 2);
        assert details.length >= 2 : "Invalid todo format";

        if (details.length < 2) {
            throw new InvalidTodoException();
        }

        return details[1].trim();
    }

    /**
     * Parses the name and date from the user input for creating a new Deadline task.
     *
     * @param input The user input containing the command, name, and date.
     * @return An array containing the parsed name and date.
     * @throws InvalidDeadlineException If the name or date is missing or if the format is incorrect.
     * @throws InvalidDateException     If the date is invalid.
     */
    public static String[] parseDeadline(String input) throws InvalidDeadlineException, InvalidDateException {
        String[] parts = input.trim().split("\\s+");
        assert parts.length >= 4 && input.contains("/by") : "Invalid deadline format";

        if (parts.length < 4 || !input.contains("/by")) {
            throw new InvalidDeadlineException();
        }

        parts = input.split("/by", 2);
        String name = parts[0].trim().split("\\s+", 2)[1];
        String date = parseDate(parts[1].trim());

        return new String[]{name, date};
    }

    /**
     * Parses the name, start date, and end date from the user input for creating a new Event task.
     *
     * @param input The user input containing the command, name, start date, and end date.
     * @return An array containing the parsed name, start date, and end date.
     * @throws InvalidEventException If the name or dates are missing or if the format is incorrect.
     * @throws InvalidDateException  If the dates are invalid.
     */
    public static String[] parseEvent(String input) throws InvalidEventException, InvalidDateException {
        String[] parts = input.trim().split("\\s+");
        assert parts.length >= 6 && input.contains("/from") && input.contains("/to") : "Invalid event format";

        if (parts.length < 6 || !input.contains("/from") || !input.contains("/to")) {
            throw new InvalidEventException();
        }

        parts = input.split("/from", 2);
        String name = parts[0].trim().split("\\s+", 2)[1];
        String[] dates = parts[1].split("/to");

        String fromDate = parseDate(dates[0].trim());
        String toDate = parseDate(dates[1].trim());

        return new String[]{name, fromDate, toDate};
    }

    /**
     * Parses the date from the user input for tasks that require a date.
     *
     * @param inputDate The date string to be parsed.
     * @return The parsed date.
     * @throws InvalidDateException If the date format is incorrect or the date is invalid.
     */
    public static String parseDate(String inputDate) throws InvalidDateException {
        DateFormat sdf = new SimpleDateFormat("d/M/yyyy HHmm");
        sdf.setLenient(false);

        try {
            sdf.parse(inputDate);

        } catch (ParseException e) {
            throw new InvalidDateException();

        }
        return inputDate;
    }
}

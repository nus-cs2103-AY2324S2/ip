package chipchat.parser;

import chipchat.action.Action;
import chipchat.action.AddTask;
import chipchat.action.Bye;
import chipchat.action.CommandType;
import chipchat.action.Delete;
import chipchat.action.Find;
import chipchat.action.ListTasks;
import chipchat.action.Mark;
import chipchat.action.Unmark;
import chipchat.exception.ArgumentException;
import chipchat.exception.InvalidArgumentException;
import chipchat.exception.MissingArgumentException;
import chipchat.task.Deadline;
import chipchat.task.Event;
import chipchat.task.Task;
import chipchat.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a utility class used to parse user inputs and data inputs given to the main Chipchat application.
 */
public class Parser {

    /**
     * Returns an action parsed from the user input.
     *
     * @param userInput user input given from standard input
     * @return an action that can be executed on-demand
     * @throws ArgumentException
     */
    public static Action parseAction(String userInput) throws ArgumentException {
        String[] tokens = userInput.trim().split(" ");
        if (tokens.length == 0) {
            throw new MissingArgumentException("Error: Please enter something");
        }

        CommandType command = parseCommand(tokens[0]);
        switch(command) {
        case BYE:
            return new Bye();
        case LIST:
            return new ListTasks();
        case FIND:
            return parseFindAction(command, tokens);
        case DELETE:
        case MARK:
        case UNMARK:
            return parseEditAction(command, tokens);
        case TODO:
        case DEADLINE:
        case EVENT:
            return parseTask(command, tokens);
        }
        return null;
    }

    private static CommandType parseCommand(String command) throws InvalidArgumentException {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException exc) {
            throw new InvalidArgumentException("Error: Sorry. I don't know what that means");
        }
    }

    private static int parseIndex(String index) throws InvalidArgumentException {
        try {
            return Integer.parseInt(index);
        } catch (NumberFormatException exc) {
            throw new InvalidArgumentException("Error: Please enter the index (number) of the task you would like to edit");
        }
    }

    private static ArrayList<String> parseArguments(String[] tokens) {
        ArrayList<String> args = new ArrayList<>();
        StringBuilder currArg = new StringBuilder();
        for (String token : tokens) {
            if (!token.startsWith("/")) {
                currArg.append(token)
                        .append(" ");
            } else if (currArg.length() > 0) {
                args.add(currArg.toString().trim());
                currArg.setLength(0);
            }
        }
        // Add remaining currArg to args list
        args.add(currArg.toString().trim());
        return args;
    }

    private static Action parseFindAction(CommandType command, String[] tokens) {
        if (tokens.length < 2) {
            throw new MissingArgumentException("Error: Missing query\nPlease enter the query (text) of the task to find");
        }
        return new Find(tokens[1]);
    }

    private static Action parseEditAction(CommandType command, String[] tokens) {
        if (tokens.length < 2) {
            throw new MissingArgumentException("Error: Missing Index\nPlease enter the index (number) of the task to edit");
        }

        int index = parseIndex(tokens[1]);
        switch(command) {
        case DELETE:
            return new Delete(index);
        case MARK:
            return new Mark(index);
        case UNMARK:
            return new Unmark(index);
        }
        return null;
    }

    private static Action parseTask(CommandType command, String[] tokens) {
        if (tokens.length < 2) {
            throw new MissingArgumentException("Error: Missing description\nPlease enter the description of the task to add");
        } else {
            tokens = Arrays.copyOfRange(tokens, 1, tokens.length);
        }

        ArrayList<String> args = parseArguments(tokens);
        String description = args.get(0);
        switch(command) {
        case TODO:
            return AddTask.addTodo(description, false);
        case DEADLINE:
            LocalDate dueBy = parseDate(args.get(1));
            return AddTask.addDeadline(description, false, dueBy);
        case EVENT:
            LocalDate dateFrom = parseDate(args.get(1));
            LocalDate dateTo = parseDate(args.get(2));
            return AddTask.addEvent(description, false, dateFrom, dateTo);
        }
        return null;
    }

    /**
     * Returns a task parsed from saved data.
     *
     * @param line the data-format string retrieved from storage file
     * @return parsed action that has been initialized based on stored data
     */
    public static Task parseLoadedTask(String line) {
        String[] tokens = line.split(" ");
        CommandType taskType = parseCommand(tokens[1]);
        ArrayList<String> args = parseArguments(Arrays.copyOfRange(tokens, 2, tokens.length));
        boolean isDone = args.get(0).equals("true");
        String description = args.get(1);

        switch (taskType) {
        case TODO:
            return new Todo(description, isDone);
        case DEADLINE:
            LocalDate dueBy = parseDate(args.get(2));
            return new Deadline(description, isDone, dueBy);
        case EVENT:
            LocalDate dateFrom = parseDate(args.get(2));
            LocalDate dateTo = parseDate(args.get(3));
            return new Event(description, isDone, dateFrom, dateTo);
        }
        return null;
    }

    /**
     * A simple data parser that uses yyyy-MM-dd format.
     *
     * @param date a string representing a date
     * @return parsed date
     */
    public static LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return LocalDate.parse(date, formatter);
    }
}

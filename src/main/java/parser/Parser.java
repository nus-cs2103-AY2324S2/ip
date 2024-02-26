package parser;

import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkDoneCommand;
import commands.MarkNotDoneCommand;
import commands.UpdateCommand;
import exceptions.ArgumentException;
import exceptions.CommandException;
import tasks.Task;

/**
 * Contains methods to parse Strings.
 */
public class Parser {

    /**
     * Returns a Command based on the user input.
     * Throws if the user input is of an invalid format.
     *
     * @param input String to be read.
     * @return Command corresponding to input.
     * @throws CommandException if the first word in input does not match the list of command words.
     * @throws ArgumentException if the arguments supplied are invalid.
     */
    public static Command parseInput(String input) throws CommandException, ArgumentException {
        String[] inputArgs = input.trim().split(" ", 2);
        switch (inputArgs[0]) {
        case "todo":
        case "deadline":
        case "event":
            throwIfInsufficientArgs(inputArgs.length, 2, "Please put a description for the task");
            return new AddCommand(Task.makeTask(inputArgs[0].trim(), inputArgs[1].trim()));
        case "delete":
            throwIfInsufficientArgs(inputArgs.length, 2, "Please provide an index to delete");
            throwIfNotInteger(inputArgs[1]);
            return new DeleteCommand(Integer.parseInt(inputArgs[1].trim()));
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            throwIfInsufficientArgs(inputArgs.length, 2, "Please provide an index to mark");
            throwIfNotInteger(inputArgs[1]);
            return new MarkDoneCommand(Integer.parseInt(inputArgs[1].trim()));
        case "unmark":
            throwIfInsufficientArgs(inputArgs.length, 2, "Please provide an index to unmark");
            throwIfNotInteger(inputArgs[1]);
            return new MarkNotDoneCommand(Integer.parseInt(inputArgs[1].trim()));
        case "find":
            throwIfInsufficientArgs(inputArgs.length, 2, "Please provide a word to find");
            return new FindCommand(inputArgs[1].trim());
        case "update":
            throwIfInsufficientArgs(inputArgs.length, 2, "Please provide an index to update");
            String[] updateArgs = parseUpdateArgument(inputArgs[1].trim());
            return new UpdateCommand(Integer.parseInt(updateArgs[0].trim()), updateArgs[1].trim());
        default:
            throw new CommandException("Please input a valid command");
        }
    }

    /**
     * Returns a Task read from the line.
     * To be used by the Storage class to read lines from saved file.
     *
     * @param line String from a line in saved data format.
     * @return Generated Task.
     * @throws ArgumentException if line cannot be converted to a task.
     */
    public static Task parseLine(String line) throws ArgumentException {
        String[] taskData = line.split(" ", 3);
        return Task.makeTask(taskData);
    }

    /**
     * Parses a String into an array of String of size 2 with arguments for constructor of Deadline
     *
     * @param arg String containing the arguments for Deadline.
     * @return array of String of size 2.
     * @throws ArgumentException if less than 2 arguments are provided.
     */
    public static String[] parseDeadlineArgument(String arg) throws ArgumentException {
        String[] result = arg.split("\\/by", 2);
        throwIfInsufficientArgs(result.length, 2, "Insufficient arguments provided for deadline task");
        return result;
    }

    /**
     * Parses a String into an array of String of size 3 with arguments for constructor of Event.
     * @param arg String containing the arguments for Event.
     * @return array of String of size 3.
     * @throws ArgumentException if less than 3 arguments are provided.
     */
    public static String[] parseEventArgument(String arg) throws ArgumentException {
        String[] result = arg.split("\\/from|\\/to", 3);
        throwIfInsufficientArgs(result.length, 3, "Insufficient arguments provided for event task");
        return result;
    }

    /**
     * Parses a string into an array of String of size 2, with the first element being a String representation
     * of an integer.
     * @param arg String input.
     * @return array of String of size 2.
     * @throws ArgumentException if less than 2 arguments are provided or the first element of the return array
     *                           cannot be converted to an int.
     */
    public static String[] parseUpdateArgument(String arg) throws ArgumentException {
        String[] result = arg.split(" ", 2);
        throwIfInsufficientArgs(result.length, 2, "Insufficient arguments provided");
        throwIfNotInteger(result[0]);
        return result;
    }

    /**
     * Returns true if the target String contains the matcher String.
     *
     * @param target String to be checked.
     * @param matcher String to search for.
     * @return true if target has substring equal to matcher, false otherwise.
     */
    public static boolean matchStrings(String target, String matcher) {
        return target.toLowerCase().contains(matcher.trim().toLowerCase());
    }

    private static void throwIfInsufficientArgs(int actual, int required, String message) throws ArgumentException {
        if (actual < required) {
            throw new ArgumentException(message);
        }
    }

    private static void throwIfNotInteger(String input) throws ArgumentException {
        try {
            Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new ArgumentException("Please input an integer");
        }
    }
}

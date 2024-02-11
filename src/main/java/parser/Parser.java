package parser;

import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.ListCommand;
import commands.FindCommand;
import commands.MarkDoneCommand;
import commands.MarkNotDoneCommand;
import exceptions.ArgumentException;
import exceptions.CommandException;
import tasks.Task;

/**
 * Class containing methods relating to input string from user or data file
 */
public class Parser {

    /**
     * Parses input to corresponding Command
     * @param input String to be read
     * @return Command corresponding to input
     * @throws CommandException if the first word in input does not match the list of command words
     * @throws ArgumentException
     */
    public static Command parseInput(String input) throws CommandException, ArgumentException {
        String[] inputArgs = input.trim().split(" ", 2);
        switch (inputArgs[0]) {
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(Task.makeTask(inputArgs[0].trim(), inputArgs[1].trim()));
        case "delete":
            return new DeleteCommand(Integer.parseInt(inputArgs[1].trim()));
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkDoneCommand(Integer.parseInt(inputArgs[1].trim()));
        case "unmark":
            return new MarkNotDoneCommand(Integer.parseInt(inputArgs[1].trim()));
        case "find":
            return new FindCommand(inputArgs[1].trim());
        default:
            throw new CommandException("Please input a valid command");
        }
    }

    /**
     * Parses a line in data file to a Task
     * @param line String from a line in saved data format
     * @return Generated Task
     * @throws ArgumentException
     */
    public static Task parseLine(String line) throws ArgumentException {
        String[] taskData = line.split(" ", 3);
        return Task.makeTask(taskData);
    }

    /**
     * Parses a string into an array of String of size 2 with arguments for constructor of Deadline
     * @param arg String containing the arguments for Deadline
     * @return array of String of size 2
     * @throws ArgumentException if less than 2 arguments are provided
     */
    public static String[] parseDeadlineArgument(String arg) throws ArgumentException {
        String[] result = arg.split("\\/by", 2);
        if (result.length < 2) {
            throw new ArgumentException("Insufficient argument provided for deadline task");
        }
        return result;
    }

    /**
     * Parses a string into an array of String of size 3 with arguments for constructor of Event
     * @param arg String containing the arguments for Event
     * @return array of String of size 3
     * @throws ArgumentException if less than 3 arguments are provided
     */
    public static String[] parseEventArgument(String arg) throws ArgumentException {
        String[] result = arg.split("\\/from|\\/to", 3);
        if (result.length < 3) {
            throw new ArgumentException("Insufficient argument provided for event task");
        }
        return result;
    }

    public static boolean matchStrings(String target, String matcher) {
        return target.toLowerCase().contains(matcher.toLowerCase());
    }
}

package parser;

import commands.*;
import exceptions.ArgumentException;
import exceptions.CommandException;
import tasks.Task;

public class Parser {
    public static Command parseInput(String input) throws CommandException, ArgumentException {
        String[] inputArgs = input.split(" ", 2);
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
        default:
            throw new CommandException("Please input a valid command");
        }
    }

    public static Task parseLine(String line) throws ArgumentException {
        String[] taskData = line.split(" ", 3);
        return Task.makeTask(taskData);
    }

    public static String[] parseDeadlineArgument(String arg) throws ArgumentException {
        String[] result = arg.split("\\/by", 2);
        if (result.length < 2) {
            throw new ArgumentException("Insufficient argument provided for deadline task");
        }
        return result;
    }

    public static String[] parseEventArgument(String arg) throws ArgumentException {
        String[] result = arg.split("\\/from|\\/to", 3);
        if (result.length < 3) {
            throw new ArgumentException("Insufficient argument provided for event task");
        }
        return result;
    }
}

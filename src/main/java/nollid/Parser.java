package nollid;

import nollid.commands.*;
import nollid.exceptions.InvalidCommandException;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    public static Command parse(String fullCommand) throws InvalidCommandException {
        if (fullCommand.isBlank()) {
            throw new InvalidCommandException("nollid.commands.Command is blank.");
        }
        // Split user input into individual words
        // e.g. "i am user input" -> ["i", "am", "user", "input"]
        ArrayList<String> argsList = new ArrayList<>(Arrays.asList(fullCommand.split(" ")));
        String commandKeyword = argsList.get(0);

        if (commandKeyword.equalsIgnoreCase("bye")) {
            return new ByeCommand();
        } else if (commandKeyword.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (commandKeyword.equalsIgnoreCase("mark")) {
            return new MarkCommand(argsList);
        } else if (commandKeyword.equalsIgnoreCase("unmark")) {
            return new UnmarkCommand(argsList);
        } else if (commandKeyword.equalsIgnoreCase("todo")) {
            return new TodoCommand(argsList);
        } else if (commandKeyword.equalsIgnoreCase("deadline")) {
            return new DeadlineCommand(argsList);
        } else if (commandKeyword.equalsIgnoreCase("event")) {
            return new EventCommand(argsList);
        } else if (commandKeyword.equalsIgnoreCase("delete")) {
            return new DeleteCommand(argsList);
        } else if (commandKeyword.equalsIgnoreCase("help")) {
            return new HelpCommand();
        } else {
            throw new InvalidCommandException("No valid command detected.");
        }
    }
}

package nollid;

import java.util.ArrayList;
import java.util.Arrays;

import nollid.commands.ByeCommand;
import nollid.commands.Command;
import nollid.commands.DeadlineCommand;
import nollid.commands.DeleteCommand;
import nollid.commands.EventCommand;
import nollid.commands.HelpCommand;
import nollid.commands.ListCommand;
import nollid.commands.MarkCommand;
import nollid.commands.TodoCommand;
import nollid.commands.UnmarkCommand;
import nollid.exceptions.InvalidCommandException;

/**
 * Parser class provides a static method to parse user input and return the corresponding Command object.
 */
public class Parser {
    /**
     * Parses the full user command and returns the appropriate Command object.
     *
     * @param fullCommand The full user command input.
     * @return The corresponding Command object based on the parsed input.
     * @throws InvalidCommandException If the user command is invalid or not recognized.
     */
    public static Command parse(String fullCommand) throws InvalidCommandException {
        if (fullCommand.isBlank()) {
            throw new InvalidCommandException("Command is blank.");
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

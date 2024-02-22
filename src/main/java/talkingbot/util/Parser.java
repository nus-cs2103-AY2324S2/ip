package talkingbot.util;

import talkingbot.command.ByeCommand;
import talkingbot.command.Command;
import talkingbot.command.DeleteCommand;
import talkingbot.command.FindCommand;
import talkingbot.command.InvalidCommand;
import talkingbot.command.ListCommand;
import talkingbot.command.ModifyMarkCommand;
import talkingbot.command.SaveCommand;
import talkingbot.command.TaskCommand;

/**
 * Class representing the parser to process user commands.
 */
public class Parser {

    /**
     * Creates a new Parser instance.
     */
    public Parser() {}

    /**
     * Parses the current command and returns the appropriate
     * Command object.
     *
     * @return A Command corresponding to the user input.
     */
    public Command parseCommand(String text) {
        String[] curCommand = text.split(" ");
        switch (curCommand[0]) {
        case "list":
            return new ListCommand(curCommand);
        case "mark":
            // Fallthrough
        case "unmark":
            return new ModifyMarkCommand(curCommand);
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            // Fallthrough
        case "do_within_period":
            return new TaskCommand(curCommand);
        case "delete":
            return new DeleteCommand(curCommand);
        case "save":
            return new SaveCommand(curCommand);
        case "bye":
            return new ByeCommand(curCommand);
        case "find":
            return new FindCommand(curCommand);
        default:
            return new InvalidCommand(curCommand);
        }
    }
}

package bytebuddy.parser;

import bytebuddy.commands.*;
import bytebuddy.exceptions.ByteBuddyException;

/**
 * Parser class responsible for parsing user input and generating corresponding Command objects.
 */
public class Parser {

    /**
     * Parses the given full input to create and return the appropriate Command object.
     *
     * @param fullInput The full input provided by the user.
     * @return The Command object corresponding to the parsed input.
     * @throws ByteBuddyException If the input cannot be parsed into a valid Command.
     */
    public static Command parse(String fullInput) throws ByteBuddyException {
        String[] commandParts = fullInput.split(" ", 2);
        String commandType = commandParts[0].toLowerCase();
        String commandInfo = commandParts.length > 1 ? commandParts[1].trim() : "";

        switch (commandType) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(commandInfo);
        case "unmark":
            return new UnmarkCommand(commandInfo);
        case "delete":
            return new DeleteCommand(commandInfo);
        case "todo":
            return new TodoCommand(commandInfo);
        case "deadline":
            return new DeadlineCommand(commandInfo);
        case "event":
            return new EventCommand(commandInfo);
        default:
            throw new ByteBuddyException("Sorry but this command does not exist~");
        }
    }

}

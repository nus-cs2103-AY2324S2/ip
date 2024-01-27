package parser;

import commands.*;
import exceptions.DukeException;


public class Parser {

    public static Command parse(String fullInput) throws DukeException {
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
            throw new DukeException("Sorry but this command does not exist~");
        }
    }

}

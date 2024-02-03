package Duke;

import Duke.Command.AddDeadlineCommand;
import Duke.Command.AddEventCommand;
import Duke.Command.AddTodoCommand;
import Duke.Command.ListCommand;
import Duke.Command.ByeCommand;
import Duke.Command.DeleteCommand;
import Duke.Command.MarkCommand;
import Duke.Command.UnmarkCommand;


import Duke.Command.Command;
import Duke.Exception.DukeException;
import Duke.Exception.InvalidArgumentException;
import Duke.Exception.InvalidCommandException;
import Duke.Ui;

public class Parser {
    public enum CommandType {
        BYE, LIST, DELETE, MARK, UNMARK, TODO, EVENT, DEADLINE
    }

    public static Command parseCommand(String userInput) throws InvalidCommandException, InvalidArgumentException {
        Command command = null;

        try {
            String[] components = userInput.split(" ", 2);
            String description;
            CommandType commandType = CommandType.valueOf(components[0].toUpperCase());

            switch (commandType) {
            case LIST:
                command = new ListCommand();
                break;

            case BYE:
                command = new ByeCommand();
                break;

            case DELETE:
                description = components[1];
                command = new DeleteCommand(description);
                break;

            case MARK:
                description = components[1];
                command = new MarkCommand(description);
                break;

            case UNMARK:
                description = components[1];
                command = new UnmarkCommand(description);
                break;

            case TODO:
                description = components[1];
                command = new AddTodoCommand(description);
                break;

            case DEADLINE:
                description = components[1];
                command = new AddDeadlineCommand(description);
                break;

            case EVENT:
                description = components[1];
                command = new AddEventCommand(description);
                break;

            default:
                throw new InvalidCommandException("Invalid Command");
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("Invalid Command");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("Description Cannot be Empty");
        }
        return command;
    }

}

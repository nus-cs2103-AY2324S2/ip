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


public class Parser {
    public enum CommandType {
        BYE, LIST, DELETE, MARK, UNMARK, TODO, EVENT, DEADLINE
    }

    public static Command parseCommand(String userInput) {
        Command command = null;

        try {
            String[] components = userInput.split(" ", 2);
            String description;

            // Validate non-empty Duke.Command.Duke.Command
            validateNonEmptyCommand(components);
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
                throw new IllegalArgumentException("Invalid Duke.Command.Duke.Command");
            }

        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Duke.Command.Duke.Command");
        }

        return command;
    }

    private static void validateNonEmptyCommand(String[] components) throws DukeException {
        if (components.length < 1 || components[0].isBlank()) {
            throw new DukeException("Invalid command");
        }
    }

    private static void validateValidCommand(String command) throws DukeException {
        try {
            CommandType convertCommand = CommandType.valueOf(command);
        } catch (IllegalArgumentException e) {
            throw new DukeException("Invalid command");
        }
    }
}

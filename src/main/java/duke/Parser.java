package duke;

import duke.command.*;
import duke.exception.DukeException;

public class Parser {

    private enum CommandType {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, HELP
    }

    public static Command parse(String userInput) throws DukeException {
        String[] input = userInput.split(" ", 2);
        CommandType c;

        try {
            c = CommandType.valueOf(input[0].toUpperCase());  // handle case-insensitive

            switch (c) {
                case BYE:
                    return new ExitCommand();
                case LIST:
                    return new ListCommand();
                case HELP:
                    return new HelpCommand();
                case DELETE:
                    return parseDeleteCommand(input);
                case MARK:
                    return parseMarkCommand(input);
                case UNMARK:
                    return parseUnmarkCommand(input);
                case TODO:
                    return parseTodoCommand(input);
                case DEADLINE:
                    return parseDeadlineCommand(input);
                case EVENT:
                    return parseEventCommand(input);
                default:
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("An error occurred: Invalid command.\n" +
                    "Please enter 'help' for a list of valid commands.");
        }
        return null;


    }

    private static Command parseTodoCommand(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("OOPS! The description of a todo cannot be left blank.\n" +
                    "Please enter 'help' command to find out more.");
        }
        String description = input[1];
        return new ToDoCommand(description);
    }

    private static Command parseDeleteCommand(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("Please indicate the index of task you want to delete.\n" +
                    "Please enter 'help' command to find out more.");
        }
        int index = Integer.parseInt(input[1]);
        return new DeleteCommand(index);
    }

    private static Command parseMarkCommand(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("Please indicate the index of task you want to mark as done.\n" +
                    "Please enter 'help' command to find out more.");
        }
        int index = Integer.parseInt(input[1]);
        return new MarkCommand(index);
    }

    private static Command parseUnmarkCommand(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("Please indicate the index of task you want to mark as not done.\n" +
                    "Please enter 'help' command to find out more.");
        }
        int index = Integer.parseInt(input[1]);
        return new UnmarkCommand(index);
    }

    private static Command parseDeadlineCommand(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("OOPS! The description of a deadline cannot be left blank.\n" +
                    "Please enter 'help' command to find out more.");
        } else if (!input[1].contains("/by")) {
            throw new DukeException("OOPS! The date/time for the deadline cannot be left blank.\n" +
                    "Please enter 'help' command to find out more.");
        }
        String[] description = input[1].split("/by ");
        return new DeadlineCommand(description[0], description[1]);
    }

    private static Command parseEventCommand(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("OOPS! The description of an event cannot be left blank.\n" +
                    "Please enter 'help' command to find out more.");
        } else if (!input[1].contains("/from") && !input[1].contains("/to")) {
            throw new DukeException("OOPS! The start time and end time cannot be left blank.\n" +
                    "Please enter 'help' command to find out more.");
        }
        String[] description = input[1].split("/from|/to");
        return new EventCommand(description[0], description[1], description[2]);
    }

}

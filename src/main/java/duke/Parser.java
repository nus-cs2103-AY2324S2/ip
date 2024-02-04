package duke;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;

/**
 * The Parser class is responsible for parsing user input and converting it into executable commands.
 * It recognizes different command types and returns the corresponding Command objects.
 */
public class Parser {

    private enum CommandType {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, HELP
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param userInput The user-entered command.
     * @return A Command object representing the parsed command.
     * @throws DukeException When the command is invalid.
     */
    public static Command parse(String userInput) throws DukeException {
        String[] input = userInput.split(" ", 2);
        CommandType c;

        try {
            // handle case-insensitive
            c = CommandType.valueOf(input[0].toUpperCase());

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
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("An error occurred: Invalid command.\n"
                    + "Please enter 'help' for a list of valid commands.");
        }
        return null;
    }

    /**
     * Parses a todo command and returns the corresponding ToDoCommand.
     *
     * @param input The string array containing the user input.
     * @return A ToDoCommand.
     * @throws DukeException If the description of the todo is missing.
     */
    private static Command parseTodoCommand(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("OOPS! The description of a todo cannot be left blank.\n"
                    + "Please enter 'help' command to find out more.");
        }
        String description = input[1];
        return new ToDoCommand(description);
    }

    /**
     * Parses a delete command and returns the corresponding DeleteCommand.
     *
     * @param input The string array containing the user input.
     * @return A DeleteCommand.
     * @throws DukeException If the index of the task to delete is missing.
     */
    private static Command parseDeleteCommand(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("Please indicate the index of task you want to delete.\n"
                    + "Please enter 'help' command to find out more.");
        }
        int index = Integer.parseInt(input[1]);
        return new DeleteCommand(index);
    }

    /**
     * Parses the user input to create a MarkCommand.
     *
     * @param input The string array containing the user input.
     * @return A MarkCommand.
     * @throws DukeException If the index is not provided.
     */
    private static Command parseMarkCommand(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("Please indicate the index of task you want to mark as done.\n"
                    + "Please enter 'help' command to find out more.");
        }
        int index = Integer.parseInt(input[1]);
        return new MarkCommand(index);
    }

    /**
     * Parses the user input to create an UnmarkCommand.
     *
     * @param input The string array containing the user input.
     * @return An UnmarkCommand.
     * @throws DukeException If the index is not provided.
     */
    private static Command parseUnmarkCommand(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("Please indicate the index of task you want to mark as not done.\n"
                    + "Please enter 'help' command to find out more.");
        }
        int index = Integer.parseInt(input[1]);
        return new UnmarkCommand(index);
    }

    /**
     * Parses the user input to create a DeadlineCommand.
     *
     * @param input The string array containing the user input.
     * @return A DeadlineCommand.
     * @throws DukeException If the description or date/time is missing in the input.
     */
    private static Command parseDeadlineCommand(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("OOPS! The description of a deadline cannot be left blank.\n"
                    + "Please enter 'help' command to find out more.");
        } else if (!input[1].contains("/by")) {
            throw new DukeException("OOPS! The date/time for the deadline cannot be left blank.\n"
                    + "Please enter 'help' command to find out more.");
        }
        String[] description = input[1].split("/by ");
        return new DeadlineCommand(description[0], description[1]);
    }

    /**
     * Parses the user input to create an EventCommand.
     *
     * @param input The string array containing the user input.
     * @return An EventCommand.
     * @throws DukeException If the description or start/end time is missing in the input.
     */
    private static Command parseEventCommand(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("OOPS! The description of an event cannot be left blank.\n"
                    + "Please enter 'help' command to find out more.");
        } else if (!input[1].contains("/from") && !input[1].contains("/to")) {
            throw new DukeException("OOPS! The start time and end time cannot be left blank.\n"
                    + "Please enter 'help' command to find out more.");
        }
        String[] description = input[1].split("/from|/to");
        return new EventCommand(description[0], description[1], description[2]);
    }

}

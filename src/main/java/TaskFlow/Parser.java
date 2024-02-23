package TaskFlow;

import TaskFlow.command.ArchiveCommand;
import TaskFlow.command.Command;
import TaskFlow.command.DeadlineCommand;
import TaskFlow.command.DeleteCommand;
import TaskFlow.command.EventCommand;
import TaskFlow.command.ExitCommand;
import TaskFlow.command.FindCommand;
import TaskFlow.command.HelpCommand;
import TaskFlow.command.ListArchiveCommand;
import TaskFlow.command.ListCommand;
import TaskFlow.command.MarkCommand;
import TaskFlow.command.ToDoCommand;
import TaskFlow.command.UnarchiveCommand;
import TaskFlow.command.UnmarkCommand;
import TaskFlow.exception.TaskFlowException;

/**
 * The Parser class is responsible for parsing user input and converting it
 * into executable commands.
 * It recognizes different command types and returns the corresponding Command
 * objects.
 */
public class Parser {

    private enum CommandType {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, ARCHIVE,
        UNARCHIVE, HELP
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param userInput The user-entered command.
     * @return A Command object representing the parsed command.
     * @throws TaskFlowException When the command is invalid.
     */
    public static Command parse(String userInput) throws TaskFlowException {
        String[] inputs = userInput.split(" ", 2);

        try {
            // handle case-insensitive
            CommandType c = CommandType.valueOf(inputs[0].toUpperCase());

            switch (c) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return parseListCommand(inputs);
            case HELP:
                return new HelpCommand();
            case DELETE:
                return parseDeleteCommand(inputs);
            case MARK:
                return parseMarkCommand(inputs);
            case UNMARK:
                return parseUnmarkCommand(inputs);
            case TODO:
                return parseToDoCommand(inputs);
            case DEADLINE:
                return parseDeadlineCommand(inputs);
            case EVENT:
                return parseEventCommand(inputs);
            case FIND:
                return parseFindCommand(inputs);
            case ARCHIVE:
                return parseArchiveCommand(inputs);
            case UNARCHIVE:
                return parseUnarchiveCommand(inputs);
            default:
                return null;
            }
        } catch (IllegalArgumentException e) {
            throw new TaskFlowException("An error occurred: Invalid command.\n"
                    + "Please enter 'help' for a list of valid commands.\n");
        }
    }

    /**
     * To check if the string array contains an empty string.
     *
     * @param inputs The string array containing the user input.
     * @return true if there is an empty string, false otherwise.
     */
    private static boolean containsEmptyString(String[] inputs) {
        boolean isEmpty = false;
        for (String str : inputs) {
            if (str.equals("")) {
                isEmpty = true;
            }
        }
        return isEmpty;
    }

    /**
     * Parses a todo command and returns the corresponding ToDoCommand.
     *
     * @param inputs The string array containing the user input.
     * @return A ToDoCommand.
     * @throws TaskFlowException If the description of the todo is missing.
     */
    private static Command parseToDoCommand(String[] inputs) throws TaskFlowException {
        if (inputs.length == 1 || containsEmptyString(inputs)) {
            assert inputs.length == 1 || containsEmptyString(inputs)
                    : "Description cannot be left blank";
            throw new TaskFlowException("OOPS! The description of a todo cannot be left blank.\n"
                    + "Please enter 'help' command to find out more.\n");
        }
        String description = inputs[1];
        return new ToDoCommand(description);
    }

    /**
     * Parses a delete command and returns the corresponding DeleteCommand.
     *
     * @param inputs The string array containing the user input.
     * @return A DeleteCommand.
     * @throws TaskFlowException If the index of the task to delete is missing.
     */
    private static Command parseDeleteCommand(String[] inputs) throws TaskFlowException {
        if (inputs.length == 1 || containsEmptyString(inputs)) {
            assert inputs.length == 1 || containsEmptyString(inputs)
                    : "Index of tasks to be deleted cannot be left blank";
            throw new TaskFlowException("Please indicate the index of task you want to delete.\n"
                    + "Please enter 'help' command to find out more.\n");
        }
        int index = Integer.parseInt(inputs[1]);
        return new DeleteCommand(index);
    }

    /**
     * Parses the user input to create a MarkCommand.
     *
     * @param inputs The string array containing the user input.
     * @return A MarkCommand.
     * @throws TaskFlowException If the index is not provided.
     */
    private static Command parseMarkCommand(String[] inputs) throws TaskFlowException {
        if (inputs.length == 1 || containsEmptyString(inputs)) {
            assert inputs.length == 1 || containsEmptyString(inputs)
                    : "Index of task to be marked cannot be left blank";
            throw new TaskFlowException("Please indicate the index of task you want to mark as done.\n"
                    + "Please enter 'help' command to find out more.\n");
        }
        int index = Integer.parseInt(inputs[1]);
        return new MarkCommand(index);
    }

    /**
     * Parses the user input to create an UnmarkCommand.
     *
     * @param inputs The string array containing the user input.
     * @return An UnmarkCommand.
     * @throws TaskFlowException If the index is not provided.
     */
    private static Command parseUnmarkCommand(String[] inputs) throws TaskFlowException {
        if (inputs.length == 1 || containsEmptyString(inputs)) {
            assert inputs.length == 1 || containsEmptyString(inputs)
                    : "Index of tasks to be unmarked cannot be left blank";
            throw new TaskFlowException("Please indicate the index of task you want to mark as not done.\n"
                    + "Please enter 'help' command to find out more.\n");
        }
        int index = Integer.parseInt(inputs[1]);
        return new UnmarkCommand(index);
    }

    /**
     * Parses the user input to create a DeadlineCommand.
     *
     * @param inputs The string array containing the user input.
     * @return A DeadlineCommand.
     * @throws TaskFlowException If the description or date/time is missing in the input.
     */
    private static Command parseDeadlineCommand(String[] inputs) throws TaskFlowException {
        if (inputs.length == 1 || containsEmptyString(inputs)) {
            assert inputs.length == 1 || containsEmptyString(inputs)
                    : "Description cannot be left blank";
            throw new TaskFlowException("OOPS! The description of a deadline cannot be left blank.\n"
                    + "Please enter 'help' command to find out more.\n");
        } else if (!inputs[1].contains("/by")) {
            assert !inputs[1].contains("/by") : "This is invalid format.";
            throw new TaskFlowException("OOPS! The date/time for the deadline cannot be left blank.\n"
                    + "Please enter 'help' command to find out more.\n");
        }
        String[] descriptions = inputs[1].split("/by ");
        return new DeadlineCommand(descriptions[0], descriptions[1]);
    }

    /**
     * Parses the user input to create an EventCommand.
     *
     * @param inputs The string array containing the user input.
     * @return An EventCommand.
     * @throws TaskFlowException If the description or start/end time is missing in the input.
     */
    private static Command parseEventCommand(String[] inputs) throws TaskFlowException {
        if (inputs.length == 1 || containsEmptyString(inputs)) {
            assert inputs.length == 1 || containsEmptyString(inputs)
                    : "Description cannot be left blank";
            throw new TaskFlowException("OOPS! The description of an event cannot be left blank.\n"
                    + "Please enter 'help' command to find out more.\n");
        } else if (!inputs[1].contains("/from") && !inputs[1].contains("/to")) {
            assert !inputs[1].contains("/from") && !inputs[1].contains("/to")
                    : "Invalid format.";
            throw new TaskFlowException("OOPS! The start time and end time cannot be left blank.\n"
                    + "Please enter 'help' command to find out more.\n");
        }
        String[] descriptions = inputs[1].split("/from|/to");
        return new EventCommand(descriptions[0], descriptions[1], descriptions[2]);
    }

    /**
     * Parses the user input to create a FindCommand for searching tasks by a keyword.
     *
     * @param inputs The string array containing the user inputs.
     * @return A FindCommand for searching tasks by the specified keyword.
     * @throws TaskFlowException If the keyword is left blank.
     */
    public static Command parseFindCommand(String[] inputs) throws TaskFlowException {
        if (inputs.length == 1 || containsEmptyString(inputs)) {
            assert inputs.length == 1 || containsEmptyString(inputs)
                    : "Keyword cannot be left blank";
            throw new TaskFlowException("OOPS! The keyword to find cannot be left blank.\n"
                    + "Please enter 'help' command to find out more.\n");
        }
        return new FindCommand(inputs[1]);
    }

    /**
     * Parses an archive command and returns the corresponding ArchiveCommand.
     *
     * @param inputs The string array containing the user input.
     * @return A ArchiveCommand.
     * @throws TaskFlowException If the index of the task to archive is missing.
     */
    public static Command parseArchiveCommand(String[] inputs) throws TaskFlowException {
        if (inputs.length == 1 || containsEmptyString(inputs)) {
            assert inputs.length == 1 || containsEmptyString(inputs)
                    : "Index of tasks to be archived cannot be left blank";
            throw new TaskFlowException("Please indicate the index of task you want to archive.\n"
                    + "Please enter 'help' command to find out more.\n");
        }
        int index = Integer.parseInt(inputs[1]);
        return new ArchiveCommand(index);
    }

    /**
     * Parses an unarchive command and returns the corresponding UnarchiveCommand.
     *
     * @param inputs The string array containing the user input.
     * @return A UnarchiveCommand.
     * @throws TaskFlowException If the index of the task to unarchive is missing.
     */
    public static Command parseUnarchiveCommand(String[] inputs) throws TaskFlowException {
        if (inputs.length == 1 || containsEmptyString(inputs)) {
            assert inputs.length == 1 || containsEmptyString(inputs)
                    : "Index of tasks to be archived cannot be left blank";
            throw new TaskFlowException("Please indicate the index of task you want to archive.\n"
                    + "Please enter 'help' command to find out more.\n");
        }
        int index = Integer.parseInt(inputs[1]);
        return new UnarchiveCommand(index);
    }

    /**
     * Parses a list command and returns the corresponding command.
     *
     * @param inputs The string array containing the user input.
     * @return A ListArchiveCommand or a ListCommand.
     * @throws TaskFlowException If it is an invalid command.
     */
    public static Command parseListCommand(String[] inputs) throws TaskFlowException {
        if (inputs.length == 1 || containsEmptyString(inputs)) {
            return new ListCommand();
        } else if (inputs[1].equals("archive")) {
            return new ListArchiveCommand();
        } else {
            throw new TaskFlowException("Invalid command. Please try again.\n");
        }
    }
}

package shon;

import shon.command.AddDeadlineCommand;
import shon.command.AddEventCommand;
import shon.command.AddTodoCommand;
import shon.command.Command;
import shon.command.DeleteTaskCommand;
import shon.command.ExitCommand;
import shon.command.FindCommand;
import shon.command.ListCommand;
import shon.command.MarkCommand;
import shon.command.UnmarkCommand;
import shon.exception.CommandException;
import shon.exception.ParameterException;

/**
 * Represents a Parser that makes sense of the user's inputs.
 */
public class Parser {
    /** The allowed set of actions that the user can do */
    private enum Action {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, BYE
    }

    /**
     * Parses the user's input into a command with the appropriate parameters.
     *
     * @param input User's input.
     * @return Command to be executed.
     * @throws CommandException If given user input is empty, or a command not allowed in <code>Action</code>.
     * @throws ParameterException If given parameters for that command is invalid.
     */
    public static Command parse(String input) throws CommandException, ParameterException {
        Action action = Parser.getAction(input);
        assert action != null : "Action in Parser#parse is null";
        switch (action) {
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(Parser.getIdx(input));
        case UNMARK:
            return new UnmarkCommand(Parser.getIdx(input));
        case TODO:
            return new AddTodoCommand(Parser.getDescription(input));
        case DEADLINE:
            return new AddDeadlineCommand(Parser.getDescription(input), Parser.getBy(input));
        case EVENT:
            return new AddEventCommand(Parser.getDescription(input), Parser.getFrom(input), Parser.getTo(input));
        case DELETE:
            return new DeleteTaskCommand(Parser.getIdx(input));
        case FIND:
            return new FindCommand(Parser.getKeyword(input));
        case BYE:
            return new ExitCommand();
        default:
            throw new CommandException("Sorry. I have a problem with that command.");
        }
    }

    /**
     * Returns the type of action/command to take. Identified by the first word of user's input.
     *
     * @param input User's input.
     * @return <code>Action</code> from the allowed set of actions.
     * @throws CommandException If input is empty or not a command not allowed in <code>Action</code>.
     */
    private static Action getAction(String input) throws CommandException {
        if (input.equals("")) {
            throw new CommandException("Please enter a command.");
        }
        try {
            // guaranteed to have at least one item in split as input is stripped and empty string checked
            return Action.valueOf(input.split(" ")[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CommandException("OOPS!!! I'm sorry, but I don't know what that means :-)");
        }
    }

    /**
     * Returns the index for <code>mark</code>, <code>unmark</code> and <code>delete</code> command.
     *
     * @param input User's input.
     * @return Index required for the action.
     * @throws ParameterException If no index is entered, multiple indexes are entered, or the value entered
     *     is not a number.
     */
    private static int getIdx(String input) throws ParameterException {
        String[] parameters = input.split(" ");
        if (parameters.length == 1) {
            throw new ParameterException("Please enter which task number to mark.");
        }
        if (parameters.length > 2) {
            throw new ParameterException("Please enter only one task number to mark.");
        }
        try {
            return Integer.parseInt(parameters[1].strip());
        } catch (NumberFormatException e) {
            throw new ParameterException("Please enter a valid number for task number.");
        }
    }

    /**
     * Returns the description of the task.
     *
     * @param input User's input.
     * @return Description of the task as a String.
     * @throws ParameterException If the description is empty.
     */
    private static String getDescription(String input) throws ParameterException {
        String[] parameters = input.split(" ", 2);
        if (parameters.length == 1) {
            throw new ParameterException("Description cannot be empty.");
        }
        String description = parameters[1].strip();
        // check empty description for deadline
        parameters = description.split("/by", 2);
        if (parameters.length == 0) {
            throw new ParameterException("Description cannot be empty.");
        }
        description = parameters[0].strip();
        // check empty description for event
        parameters = description.split("/to", 2);
        if (parameters.length == 0) {
            throw new ParameterException("Description cannot be empty.");
        }
        description = parameters[0].strip();
        parameters = description.split("/from", 2);
        if (parameters.length == 0) {
            throw new ParameterException("Description cannot be empty.");
        }
        description = parameters[0].strip();
        if (description.equals("")) {
            throw new ParameterException("Description cannot be empty");
        }
        return description;
    }

    /**
     * Returns the by parameter for <code>deadline</code> command.
     *
     * @param input User's input.
     * @return The by parameter as a String.
     * @throws ParameterException If the input is a deadline command but <code>"/by"</code> is not found in the input,
     *     or if the given <code>/by</code> datetime is empty.
     */
    private static String getBy(String input) throws ParameterException {
        if (!input.contains("/by")) {
            throw new ParameterException("Please indicate due date/time after \"/by\".");
        }
        // description is guaranteed to be not empty since getDescription() is called first
        if (input.endsWith("/by")) {
            throw new ParameterException("Deadline's due date/time cannot be empty.");
        }
        return input.split("/by", 2)[1].strip();
    }

    /**
     * Returns the from parameter for <code>event</code> command.
     *
     * @param input User's input
     * @return The from parameter as a String.
     * @throws ParameterException If the input is an event command but <code>"/from"</code> is not found in the input,
     *     or if the given <code>/from</code> datetime is empty.
     */
    private static String getFrom(String input) throws ParameterException {
        if (!input.contains("/from")) {
            throw new ParameterException("Please indicate from date/time after \"/from\".");
        }
        // drop the first word
        input = input.split(" ", 2)[1].strip();
        String[] parameters = input.split("/from", 2);
        // description is guaranteed to be not empty since getDescription() is called first
        if (parameters.length == 1) {
            throw new ParameterException("Event from date/time cannot be empty.");
        }
        String from = parameters[1].strip();
        parameters = from.split("/to", 2);
        if (parameters.length == 0) {
            throw new ParameterException("Event from date/time cannot be empty.");
        }
        from = parameters[0].strip();
        if (from.equals("")) {
            throw new ParameterException("Event from date/time cannot be empty.");
        }
        return from;
    }

    /**
     * Returns the to parameter for <code>event</code> command.
     *
     * @param input User's input.
     * @return The to parameter as a String.
     * @throws ParameterException If the input is an event command but <code>"/to"</code> is not found in the input,
     *     or if the given <code>/to</code> datetime is empty.
     */
    private static String getTo(String input) throws ParameterException {
        if (!input.contains("/to")) {
            throw new ParameterException("Please indicate to date/time after \"/to\".");
        }
        // split on "/from" is guaranteed to have at least 2 items since getFrom() is called first
        String to = input.split("/from", 2)[1].strip();
        if (!to.contains("/to")) {
            throw new ParameterException("\"/to\" must come after \"/from\".");
        }
        if (to.endsWith("/to")) {
            throw new ParameterException("Event to date/time cannot be empty.");
        }
        return to.split("/to", 2)[1].strip();
    }

    /**
     * Returns the keyword of the <code>find</code> command.
     *
     * @param input User's input.
     * @return The keyword to look for in task descriptions.
     * @throws ParameterException If no keyword is given.
     */
    private static String getKeyword(String input) throws ParameterException {
        String[] parameters = input.split(" ", 2);
        if (parameters.length == 1) {
            throw new ParameterException("Please enter a keyword to find by.");
        }
        return parameters[1].strip();
    }
}

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

public class Parser {

    private enum Action {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, BYE
    }

    public static Command parse(String input) throws CommandException, ParameterException {
        Action action = Parser.getAction(input);
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

    private static int getIdx(String input) throws ParameterException {
        String[] split = input.split(" ");
        if (split.length == 1) {
            throw new ParameterException("Please enter which task number to mark.");
        }
        if (split.length > 2) {
            throw new ParameterException("Please enter only one task number to mark.");
        }
        try {
            return Integer.parseInt(split[1].strip());
        } catch (NumberFormatException e) {
            throw new ParameterException("Please enter a valid number for task number.");
        }
    }

    private static String getDescription(String input) throws ParameterException {
        String[] split = input.split(" ", 2);
        if (split.length == 1) {
            throw new ParameterException("Description cannot be empty.");
        }
        String description = split[1].strip();
        // check empty description for deadline
        split = description.split("/by", 2);
        if (split.length == 0) {
            throw new ParameterException("Description cannot be empty.");
        }
        description = split[0].strip();
        // check empty description for event
        split = description.split("/to", 2);
        if (split.length == 0) {
            throw new ParameterException("Description cannot be empty.");
        }
        description = split[0].strip();
        split = description.split("/from", 2);
        if (split.length == 0) {
            throw new ParameterException("Description cannot be empty.");
        }
        description = split[0].strip();
        if (description.equals("")) {
            throw new ParameterException("Description cannot be empty");
        }
        return description;
    }

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

    private static String getFrom(String input)  throws ParameterException {
        if (!input.contains("/from")) {
            throw new ParameterException("Please indicate from date/time after \"/from\".");
        }
        // drop the first word
        input = input.split(" ", 2)[1].strip();
        String[] split = input.split("/from", 2);
        // description is guaranteed to be not empty since getDescription() is called first
        if (split.length == 1) {
            throw new ParameterException("Event from date/time cannot be empty.");
        }
        String from = split[1].strip();
        split = from.split("/to", 2);
        if (split.length == 0) {
            throw new ParameterException("Event from date/time cannot be empty.");
        }
        from = split[0].strip();
        if (from.equals("")) {
            throw new ParameterException("Event from date/time cannot be empty.");
        }
        return from;
    }

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
        String[] split = input.split(" ", 2);
        if (split.length == 1) {
            throw new ParameterException("Please enter a keyword to find by.");
        }
        return split[1].strip();
    }
}
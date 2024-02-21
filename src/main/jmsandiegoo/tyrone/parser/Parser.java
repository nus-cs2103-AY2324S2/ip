package jmsandiegoo.tyrone.parser;

import jmsandiegoo.tyrone.commands.*;
import jmsandiegoo.tyrone.common.DateTime;
import jmsandiegoo.tyrone.common.Messages;
import jmsandiegoo.tyrone.task.Deadline;
import jmsandiegoo.tyrone.task.Event;
import jmsandiegoo.tyrone.task.ToDo;
import jmsandiegoo.tyrone.exceptions.IncorrectCommandException;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the parser handling the parsing of the raw user inputs.
 */
public class Parser {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Pattern TODO_ARGS_FORMAT = Pattern.compile("(?<description>.+)");
    private static final Pattern DEADLINE_ARGS_FORMAT = Pattern.compile(
            "(?<description>[^/]+)\\s+/by\\s+(?<dateTime>[^/]+)");
    private static final Pattern EVENT_ARGS_FORMAT = Pattern.compile(
            "(?<description>[^/]+)\\s+/from\\s+(?<fromDateTime>[^/]+)\\s+/to\\s+(?<toDateTime>[^/]+)");
    private static final Pattern INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>\\d+)");
    private static final Pattern FIND_ARGS_FORMAT = Pattern.compile("(?<keyword>.+)");

    /**
     * Parses the raw user input into command and arguments respectively.
     * Also, redirects it to the command handler. If raw input is not in expected format,
     * IncorrectCommandException is thrown.
     *
     * @param rawUserInput - the raw input from the System.in.
     * @return Command - returns the correct Command handler for the parsed input.
     * @throws IncorrectCommandException - thrown if raw user input is not in the expected format.
     */
    public Command parseRawUserCommand(String rawUserInput) throws IncorrectCommandException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(rawUserInput.trim());
        if (!matcher.matches()) {
            throw new IncorrectCommandException(Messages.MESSAGE_NOT_EXIST_CMD);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        
        return getCommand(commandWord, arguments);
    }

    private Command getCommand(String commandWord, String arguments) throws IncorrectCommandException {
        Command command;
        switch (commandWord) {
        case ByeCommand.COMMAND_WORD:
            command = parseByeCommandArgs(arguments);
            break;
        case ListCommand.COMMAND_WORD:
            command = parseListCommandArgs(arguments);
            break;
        case UndoCommand.COMMAND_WORD:
            command = parseUndoCommandArgs(arguments);
            break;
        case TodoCommand.COMMAND_WORD:
            command = parseTodoCommandArgs(arguments);
            break;
        case DeadlineCommand.COMMAND_WORD:
            command = parseDeadlineCommandArgs(arguments);
            break;
        case EventCommand.COMMAND_WORD:
            command = parseEventCommandArgs(arguments);
            break;
        case MarkCommand.COMMAND_WORD:
            command = parseMarkupCommandArgs(arguments);
            break;
        case UnmarkCommand.COMMAND_WORD:
            command = parseUnmarkCommandArgs(arguments);
            break;
        case DeleteCommand.COMMAND_WORD:
            command = parseDeleteCommandArgs(arguments);
            break;
        case FindCommand.COMMAND_WORD:
            command = parseFindCommandArgs(arguments);
            break;
        default:
            throw new IncorrectCommandException(Messages.MESSAGE_NOT_EXIST_CMD);
        }

        return command;
    }

    private Command parseByeCommandArgs(String arguments) throws IncorrectCommandException {
        if (!arguments.trim().isEmpty()) {
            throw new IncorrectCommandException(
                    String.format(Messages.MESSAGE_INCORRECT_COMMAND_FORMAT, "bye", "no arguments allowed"));
        }
        return new ByeCommand();
    }

    private Command parseListCommandArgs(String arguments) throws IncorrectCommandException {
        if (!arguments.trim().isEmpty()) {
            throw new IncorrectCommandException(
                    String.format(Messages.MESSAGE_INCORRECT_COMMAND_FORMAT, "list", "no arguments allowed"));
        }

        return new ListCommand();
    }

    private Command parseUndoCommandArgs(String arguments) throws IncorrectCommandException {
        if (!arguments.trim().isEmpty()) {
            throw new IncorrectCommandException(
                    String.format(Messages.MESSAGE_INCORRECT_COMMAND_FORMAT, "undo", "no arguments allowed"));
        }

        return new UndoCommand();
    }

    private Command parseTodoCommandArgs(String arguments) throws IncorrectCommandException {
        final Matcher matcher = TODO_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new IncorrectCommandException(String.format(Messages.MESSAGE_TODO_EMPTY_DESC));
        }

        try {
            ToDo item = new ToDo(matcher.group("description"));
            return new TodoCommand(item);
        } catch(IllegalArgumentException e) {
            throw new IncorrectCommandException(
                    String.format(Messages.MESSAGE_INCORRECT_COMMAND_FORMAT, "todo", "todo <description>"));
        }
    }

    private Command parseDeadlineCommandArgs(String arguments) throws IncorrectCommandException {
        final Matcher matcher = DEADLINE_ARGS_FORMAT.matcher(arguments.trim());

        if (!matcher.matches()) {
            throw new IncorrectCommandException(Messages.MESSAGE_DEADLINE_INCORRECT);
        }

        try {
            Deadline item = new Deadline(
                    matcher.group("description"),
                    new DateTime(matcher.group("dateTime")));
            return new DeadlineCommand(item);
        } catch (IllegalArgumentException | DateTimeParseException e) {
            throw new IncorrectCommandException(Messages.MESSAGE_DEADLINE_INCORRECT);
        }
    }

    private Command parseEventCommandArgs(String arguments) throws IncorrectCommandException {
        final Matcher matcher = EVENT_ARGS_FORMAT.matcher(arguments.trim());

        if (!matcher.matches()) {
            throw new IncorrectCommandException(Messages.MESSAGE_EVENT_INCORRECT);
        }

        try {
            Event item = new Event(
                    matcher.group("description"),
                    new DateTime(matcher.group("fromDateTime")),
                    new DateTime(matcher.group("toDateTime"))
            );

            return new EventCommand(item);
        } catch (IllegalArgumentException | DateTimeParseException e) {
            throw new IncorrectCommandException(Messages.MESSAGE_EVENT_INCORRECT);
        }
    }

    private Command parseMarkupCommandArgs(String arguments) throws IncorrectCommandException {
        final Matcher matcher = INDEX_ARGS_FORMAT.matcher(arguments.trim());
        final String MESSAGE_INCORRECT_MARK_INDEX = String.format(Messages.MESSAGE_INCORRECT_COMMAND_INDEX, "mark");

        if (!matcher.matches()) {
            throw new IncorrectCommandException(MESSAGE_INCORRECT_MARK_INDEX);
        }

        try {
            return new MarkCommand(Integer.parseInt(matcher.group("targetIndex")) - 1);
        } catch (IllegalArgumentException e) {
            throw new IncorrectCommandException(MESSAGE_INCORRECT_MARK_INDEX);
        }
    }

    private Command parseUnmarkCommandArgs(String arguments) throws IncorrectCommandException {
        final Matcher matcher = INDEX_ARGS_FORMAT.matcher(arguments.trim());
        final String MESSAGE_INCORRECT_UNMARK_INDEX = String.format(Messages.MESSAGE_INCORRECT_COMMAND_INDEX, "unmark");

        if (!matcher.matches()) {
            throw new IncorrectCommandException(MESSAGE_INCORRECT_UNMARK_INDEX);
        }

        try {
            return new UnmarkCommand(Integer.parseInt(matcher.group("targetIndex")) - 1);
        } catch (IllegalArgumentException e) {
            throw new IncorrectCommandException(MESSAGE_INCORRECT_UNMARK_INDEX);
        }
    }

    private Command parseDeleteCommandArgs(String arguments) throws IncorrectCommandException {
        final Matcher matcher = INDEX_ARGS_FORMAT.matcher(arguments.trim());
        final String MESSAGE_INCORRECT_DELETE_INDEX = String.format(Messages.MESSAGE_INCORRECT_COMMAND_INDEX, "delete");

        if (!matcher.matches()) {
            throw new IncorrectCommandException(MESSAGE_INCORRECT_DELETE_INDEX);
        }

        try {
            return new DeleteCommand(Integer.parseInt(matcher.group("targetIndex")) - 1);
        } catch (IllegalArgumentException e) {
            throw new IncorrectCommandException(MESSAGE_INCORRECT_DELETE_INDEX);
        }
    }

    private Command parseFindCommandArgs(String arguments) throws IncorrectCommandException {
        final Matcher matcher = FIND_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new IncorrectCommandException(Messages.MESSAGE_FIND_INCORRECT);
        }

        try {
            return new FindCommand(matcher.group("keyword"));
        } catch(IllegalArgumentException e) {
            throw new IncorrectCommandException(Messages.MESSAGE_FIND_INCORRECT);
        }
    }
}

package jerome.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jerome.commands.ByeCommand;
import jerome.commands.Command;
import jerome.commands.DeadlineCommand;
import jerome.commands.DeleteCommand;
import jerome.commands.EventCommand;
import jerome.commands.FindCommand;
import jerome.commands.IncorrectCommand;
import jerome.commands.ListCommand;
import jerome.commands.MarkCommand;
import jerome.commands.SetPriorityCommand;
import jerome.commands.TodoCommand;
import jerome.commands.UnmarkCommand;
import jerome.common.Messages;
import jerome.exception.MalformedUserInputException;
import jerome.tasklist.Priority;

/**
 * Represents a Parser class that parses user input and converts it into executable commands.
 *
 * @@author se-edu
 * Reuse from https://github.com/se-edu/addressbook-level2
 * with modifications to suit the context of my program.
 */
public class Parser {

    /**
     * Represents Regex used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param userInput the user input string
     * @return Command object based on user input
     */
    public Command parseCommand(String userInput) {
        assert userInput != null : "User input should not be null";
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());

        if (!matcher.matches()) {
            return new IncorrectCommand(Messages.MESSAGE_INCORRECT);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case TodoCommand.COMMAND_WORD:
            return prepareTodo(arguments);

        case EventCommand.COMMAND_WORD:
            return prepareEvent(arguments);

        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(arguments);

        case MarkCommand.COMMAND_WORD:
            return prepareMarkCommand(arguments);

        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmarkCommand(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDeleteCommand(arguments);

        case FindCommand.COMMAND_WORD:
            return prepareFindCommand(arguments);

        case SetPriorityCommand.COMMAND_WORD:
            return prepareSetPriorityCommand(arguments);

        default:
            return new IncorrectCommand(Messages.MESSAGE_INCORRECT);
        }
    }

    private Command prepareSetPriorityCommand(String arguments) {

        final Matcher matcher = SetPriorityCommand.SET_PRIORITY_ARGUMENTS_FORMAT.matcher(arguments.trim());

        if (!matcher.matches()) {
            return new IncorrectCommand(SetPriorityCommand.MESSAGE_INVALID_ID);
        }

        try {
            final int targetIndex = Integer.parseInt(matcher.group("targetIndex")) - 1;
            final Priority priorityLevel = Priority.valueOf(matcher.group("priorityLevel"));

            return new SetPriorityCommand(targetIndex, priorityLevel);
        } catch (NumberFormatException e) {
            return new IncorrectCommand(SetPriorityCommand.MESSAGE_INVALID_ID);
        } catch (IllegalArgumentException e) {
            // Handle the case where the enum constant is not valid
            return new IncorrectCommand(SetPriorityCommand.MESSAGE_INVALID_PRIORITY);
        }
    }


    private Command prepareFindCommand(String arguments) {
        if (arguments.isEmpty()) {
            return new IncorrectCommand(FindCommand.MESSAGE_EMPTY_SEARCH_TERM);
        }
        return new FindCommand(arguments.trim());
    }

    private Command prepareMarkCommand(String arguments) {
        if (arguments.trim().isEmpty()) {
            return new IncorrectCommand(MarkCommand.MESSAGE_INVALID_ID);
        }
        try {
            int targetIndex = Integer.valueOf(arguments.trim()) - 1;
            return new MarkCommand(targetIndex);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MarkCommand.MESSAGE_INVALID_ID);
        }

    }

    private Command prepareUnmarkCommand(String arguments) {
        if (arguments.trim().isEmpty()) {
            return new IncorrectCommand(UnmarkCommand.MESSAGE_INVALID_ID);
        }
        try {
            int targetIndex = Integer.valueOf(arguments.trim()) - 1;
            return new UnmarkCommand(targetIndex);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(UnmarkCommand.MESSAGE_INVALID_ID);
        }

    }

    private Command prepareDeleteCommand(String arguments) {
        if (arguments.trim().isEmpty()) {
            return new IncorrectCommand(DeleteCommand.MESSAGE_INVALID_ID);
        }
        try {
            int targetIndex = Integer.valueOf(arguments.trim()) - 1;
            return new DeleteCommand(targetIndex);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(DeleteCommand.MESSAGE_INVALID_ID);
        }

    }


    private Command prepareDeadline(String arguments) {
        final Matcher matcher = DeadlineCommand.DEADLINE_ARGUMENTS_FORMAT.matcher(arguments.trim());

        if (!matcher.matches()) {
            return new IncorrectCommand(DeadlineCommand.MESSAGE_USAGE);
        }

        final String eventName = matcher.group("eventName");
        final String endTime = matcher.group("endTime");

        if (eventName.isEmpty()) {
            return new IncorrectCommand(DeadlineCommand.MESSAGE_BLANK_EVENT);
        } else if (endTime.isEmpty()) {
            return new IncorrectCommand(DeadlineCommand.MESSAGE_BLANK_END_TIME);
        }
        try {
            return new DeadlineCommand(eventName, endTime);
        } catch (MalformedUserInputException e) {
            return new IncorrectCommand(e.getMessage());
        }

    }

    private Command prepareEvent(String arguments) {
        final Matcher matcher = EventCommand.EVENT_ARGUMENTS_FORMAT.matcher(arguments.trim());

        if (!matcher.matches()) {
            return new IncorrectCommand(EventCommand.MESSAGE_USAGE);
        }

        final String startTime = matcher.group("startTime");
        final String endTime = matcher.group("endTime");
        final String eventName = matcher.group("eventName");

        if (startTime.isEmpty()) {
            return new IncorrectCommand(EventCommand.MESSAGE_BLANK_START_TIME);
        } else if (eventName.isEmpty()) {
            return new IncorrectCommand(EventCommand.MESSAGE_BLANK_EVENT);
        } else if (endTime.isEmpty()) {
            return new IncorrectCommand(EventCommand.MESSAGE_BLANK_END_TIME);
        }
        try {
            return new EventCommand(eventName, startTime, endTime);
        } catch (MalformedUserInputException e) {
            return new IncorrectCommand(e.getMessage());
        }

    }

    private Command prepareTodo(String arguments) {
        String eventName = arguments.trim();
        if (eventName.isEmpty()) {
            return new IncorrectCommand(TodoCommand.MESSAGE_BLANK_EVENT);
        }
        return new TodoCommand(eventName);

    }
}

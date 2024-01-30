package parser;

import commands.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.Messages;
import exception.MalformedUserInputException;
import tasklist.Event;

import static commands.EventCommand.EVENT_ARGUMENTS_FORMAT;
import static commands.TodoCommand.MESSAGE_BLANK_EVENT;

public class Parser {

    /**
     * Used for initial separation of command word and args.
     * TODO: fill in the reference
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public Command parseCommand(String userInput) {
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

            default:
                return new IncorrectCommand(Messages.MESSAGE_INCORRECT);
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
        } else {
            try {
                return new EventCommand(eventName, startTime, endTime);
            } catch (MalformedUserInputException e) {
                return new IncorrectCommand(e.getMessage());
            }
        }
    }

    private Command prepareTodo(String arguments) {
        String eventName = arguments.trim();
        if (eventName.isEmpty()) {
            return new IncorrectCommand(TodoCommand.MESSAGE_BLANK_EVENT);
        } else {
            return new TodoCommand(eventName);
        }
    }
}

package parser;

import commands.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.Messages;
import exception.MalformedUserInputException;

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

            case ByeCommand.COMMAND_WORD:
                return new ByeCommand();

            case ListCommand.COMMAND_WORD:
                return new ListCommand();

            default:
                return new IncorrectCommand(Messages.MESSAGE_INCORRECT);
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

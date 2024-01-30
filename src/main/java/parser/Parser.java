package parser;

import commands.ByeCommand;
import commands.Command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commands.TodoCommand;

public class Parser {

    /**
     * Used for initial separation of command word and args.
     * TODO: fill in the reference
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new ByeCommand();
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
            case TodoCommand.COMMAND_WORD:
                return new TodoCommand(arguments);
            default:
                return new ByeCommand();
        }

    }
}

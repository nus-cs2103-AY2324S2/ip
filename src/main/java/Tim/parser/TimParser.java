package Tim.parser;

import Tim.commands.*;
import Tim.exception.TimException;

import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Tim.exception.ErrorMessages.MESSAGE_UNKNOWN_COMMAND;
import static Tim.exception.ErrorMessages.MESSAGE_INVALID_COMMAND_FORMAT;

public class TimParser {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(\\w+)\\s*(.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws TimException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput, Path filePath) throws TimException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new TimException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        final String commandWord = matcher.group(1).trim();
        final String arguments = matcher.group(2);

        switch (commandWord) {

            case AddTodoTaskCommand.COMMAND_WORD:
                return new AddTodoTaskCommandParser().parse(arguments, filePath);

            case ListCommand.COMMAND_WORD:
                return new ListCommand();

            case AddDeadlineTaskCommand.COMMAND_WORD:
                return new AddDeadlineTaskCommandParser().parse(arguments, filePath);

            case AddEventTaskCommand.COMMAND_WORD:
                return new AddEventTaskCommandParser().parse(arguments, filePath);

            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();

            case MarkTaskCommand.COMMAND_WORD:
                return new MarkTaskCommandParser().parse(arguments, filePath);

            case UnmarkTaskCommand.COMMAND_WORD:
                return new UnmarkTaskCommandParser().parse(arguments, filePath);

            case DeleteTaskCommand.COMMAND_WORD:
                return new DeleteTaskCommandParser().parse(arguments, filePath);

            case FindCommand.COMMAND_WORD:
                return new FindCommandParser().parse(arguments, filePath);

            case CheckDatesCommand.COMMAND_WORD:
                return new CheckDatesCommandParser().parse(arguments, filePath);

            default:
                throw new TimException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}

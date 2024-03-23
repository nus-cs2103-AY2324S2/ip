package Tim.parser;

import Tim.commands.UnmarkTaskCommand;
import Tim.exception.TimException;

import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Tim.exception.ErrorMessages.MESSAGE_INVALID_COMMAND_FORMAT;


public class UnmarkTaskCommandParser implements Parser<UnmarkTaskCommand> {
    private static final Pattern UNMARK_COMMAND_FORMAT = Pattern.compile("(\\d+)");

    /**
     * Parse arguments into UnmarkTaskCommand.
     * @param args index of task to be unmarked
     * @param filePath file where tasks are to be saved
     * @return UnmarkTaskCommand object
     * @throws TimException
     */
    @Override
    public UnmarkTaskCommand parse(String args, Path filePath) throws TimException{
        Matcher matcher = UNMARK_COMMAND_FORMAT.matcher(args);
        int index = 0;
        if (matcher.matches()) {
            index = Integer.parseInt(matcher.group(1));
        } else {
            throw new TimException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        return new UnmarkTaskCommand(index, filePath);
    }
}
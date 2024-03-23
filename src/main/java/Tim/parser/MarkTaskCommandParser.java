package Tim.parser;

import Tim.commands.MarkTaskCommand;
import Tim.exception.TimException;

import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Tim.exception.ErrorMessages.MESSAGE_INVALID_COMMAND_FORMAT;


public class MarkTaskCommandParser implements Parser<MarkTaskCommand> {
    private static final Pattern MARK_COMMAND_FORMAT = Pattern.compile("(\\d+)");

    /**
     * Parse arguments into MarkTaskCommand.
     * @param args index of task to be marked
     * @param filePath file where tasks are to be saved
     * @return MarkTaskCommand object
     * @throws TimException
     */
    @Override
    public MarkTaskCommand parse(String args, Path filePath) throws TimException{
        Matcher matcher = MARK_COMMAND_FORMAT.matcher(args);
        int index = 0;
        if (matcher.matches()) {
            index = Integer.parseInt(matcher.group(1));
        } else {
            throw new TimException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        return new MarkTaskCommand(index, filePath);
    }
}

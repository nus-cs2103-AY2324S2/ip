package Tim.parser;

import Tim.commands.DeleteTaskCommand;
import Tim.exception.TimException;

import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Tim.exception.ErrorMessages.MESSAGE_INVALID_COMMAND_FORMAT;


public class DeleteTaskCommandParser implements Parser<DeleteTaskCommand> {
    private static final Pattern DELETE_COMMAND_FORMAT = Pattern.compile("(\\d+)");

    /**
     * Parse arguments into DeleteTaskCommand.
     * @param args index of task to be deleted
     * @param filePath file where tasks are to be saved
     * @return DeleteTaskCommand object
     * @throws TimException
     */
    @Override
    public DeleteTaskCommand parse(String args, Path filePath) throws TimException{
        assert filePath != null : "File path must not be null";
        Matcher matcher = DELETE_COMMAND_FORMAT.matcher(args);
        int index = 0;
        if (matcher.matches()) {
            index = Integer.parseInt(matcher.group(1));
        } else {
            throw new TimException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        return new DeleteTaskCommand(index, filePath);
    }
}
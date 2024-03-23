package Tim.parser;

import Tim.commands.FindCommand;
import Tim.exception.TimException;

import java.nio.file.Path;

import static Tim.exception.ErrorMessages.MESSAGE_INVALID_COMMAND_FORMAT;

public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parse arguments into FindCommand.
     * @param args String to be matched
     * @param filePath file where tasks are to be saved
     * @return FindCommand object
     * @throws TimException
     */
    @Override
    public FindCommand parse(String args, Path filePath) throws TimException {
        if (args.isEmpty()) {
            throw new TimException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        return new FindCommand(args, filePath);
    }
}

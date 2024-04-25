package Tim.parser;

import Tim.commands.CheckDatesCommand;
import Tim.exception.TimException;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Tim.exception.ErrorMessages.MESSAGE_INVALID_COMMAND_FORMAT;

public class CheckDatesCommandParser implements Parser<CheckDatesCommand> {
    private static final Pattern CHECKDATES_COMMAND_FORMAT = Pattern.compile(
            "/from (\\d+/\\d+/\\d+) /to (\\d+/\\d+/\\d+)(.*)");

    /**
     * Parse arguments into CheckDatesCommand.
     * @param args Specified date period
     * @param filePath file where tasks are to be saved
     * @return FindCommand object
     * @throws TimException
     */
    @Override
    public CheckDatesCommand parse(String args, Path filePath) throws TimException {
        assert filePath != null : "File path must not be null";
        final Matcher matcher = CHECKDATES_COMMAND_FORMAT.matcher(args);
        LocalDate fromDate;
        LocalDate toDate;
        if (matcher.matches()) {
            fromDate = Parser.getDate(matcher.group(1));
            toDate = Parser.getDate(matcher.group(2));
        } else {
            throw new TimException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        return new CheckDatesCommand(fromDate, toDate, filePath);
    }


}

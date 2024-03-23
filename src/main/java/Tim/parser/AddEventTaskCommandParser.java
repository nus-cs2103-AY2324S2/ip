package Tim.parser;

import Tim.commands.AddEventTaskCommand;
import Tim.exception.TimException;
import Tim.task.Event;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Tim.exception.ErrorMessages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input argument and create a new AddEventTaskCommand object.
 */
public class AddEventTaskCommandParser implements Parser<AddEventTaskCommand> {

    private static final Pattern EVENT_COMMAND_FORMAT = Pattern.compile(
            "(.+) /from (\\d+/\\d+/\\d+)(.*) /to (\\d+/\\d+/\\d+)(.*)");

    /**
     * Parse arguments into AddEventTaskCommand.
     * @param args contents of Event task
     * @param filePath file where tasks are to be saved
     * @return AddEventTaskCommand object
     * @throws TimException
     */
    @Override
    public AddEventTaskCommand parse(String args, Path filePath) throws TimException {
        assert filePath != null : "File path must not be null";
        final Matcher matcher = EVENT_COMMAND_FORMAT.matcher(args);
        Event task;
        if (matcher.matches()) {
            LocalDate fromDate = Parser.getDate(matcher.group(2));
            LocalDate toDate = Parser.getDate(matcher.group(4));
            task = new Event(matcher.group(1), fromDate, toDate);
        } else {
            throw new TimException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        return new AddEventTaskCommand(task, filePath);
    }

}

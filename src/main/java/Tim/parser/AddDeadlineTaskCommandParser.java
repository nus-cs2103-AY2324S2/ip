package Tim.parser;

import Tim.commands.AddDeadlineTaskCommand;
import Tim.exception.TimException;
import Tim.task.Deadline;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Tim.exception.ErrorMessages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input argument and create a new AddDeadlineTaskCommand object.
 */
public class AddDeadlineTaskCommandParser implements Parser<AddDeadlineTaskCommand> {

    private static final Pattern DEADLINE_COMMAND_FORMAT = Pattern.compile("(.+) /by (\\d+/\\d+/\\d+)(.*)");

    /**
     * Parse arguments into AddDeadlineTaskCommand.
     * @param args contents of deadline task
     * @param filePath file where tasks are to be saved
     * @return AddDeadlineTaskCommand object
     * @throws TimException
     */
    @Override
    public AddDeadlineTaskCommand parse(String args, Path filePath) throws TimException {
        assert filePath != null : "File path must not be null";
        final Matcher matcher = DEADLINE_COMMAND_FORMAT.matcher(args);
        Deadline task;
        if (matcher.matches()) {
            LocalDate date = Parser.getDate(matcher.group(2));
            task = new Deadline(matcher.group(1), date);
        } else {
            throw new TimException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        return new AddDeadlineTaskCommand(task, filePath);
    }
}

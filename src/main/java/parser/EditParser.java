package parser;

import static parser.Parser.DATE_TIME_PARSE_FORMAT;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import command.EditCommand;
import exception.BuddyException;

/**
 * Represents EditParser used to help parse edit commands.
 */
public class EditParser {
    protected int taskIndex;
    protected String query;
    protected String taskDescription;
    protected LocalDateTime by;
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates an EditParser with index of task to edit and query.
     *
     * @param taskIndex index of task to edit.
     * @param query parts of task to be edited in command syntax.
     */
    public EditParser(int taskIndex, String query) {
        this.taskIndex = taskIndex;
        this.query = query;
    }

    /**
     * Parses the query. Information not edited are left as null.
     *
     * @return EditCommand with information to be edited.
     * @throws BuddyException
     */
    public EditCommand parseEdit() throws BuddyException {
        try {
            Pattern pattern = Pattern.compile(
                    "(/task\\s[\\s\\w]*)?"
                            + "(\\s?/by\\s\\d{2}/\\d{2}/\\d{4}\\s[0-2][0-9][0-5][0-9])?"
                            + "(\\s?/from\\s\\d{2}/\\d{2}/\\d{4}\\s[0-2][0-9][0-5][0-9])?"
                            + "(\\s?/to\\s\\d{2}/\\d{2}/\\d{4}\\s[0-2][0-9][0-5][0-9])?");

            Matcher matcher = pattern.matcher(query);

            matcher.find();

            String taskQuery = matcher.group(1);
            taskDescription = getQuery(taskQuery);

            String deadlineQuery = matcher.group(2);
            by = formatStringToDateTime(deadlineQuery);

            String eventStartQuery = matcher.group(3);
            from = formatStringToDateTime(eventStartQuery);

            String eventEndQuery = matcher.group(4);
            to = formatStringToDateTime(eventEndQuery);

            return new EditCommand(taskIndex, taskDescription, by, from, to);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new BuddyException("Task cannot be empty!");
        }
    }

    private String getQuery(String input) {
        if (input == null) {
            return null;
        } else {
            return input.trim().split(" ", 2)[1];
        }
    }

    private LocalDateTime formatStringToDateTime(String input) throws BuddyException {
        if (input == null) {
            return null;
        } else {
            try {
                String dateTime = input.trim().split(" ", 2)[1];
                return LocalDateTime.parse(dateTime, DATE_TIME_PARSE_FORMAT);
            } catch (DateTimeException dte) {
                throw new BuddyException("Not a valid date format!");
            }
        }
    }
}

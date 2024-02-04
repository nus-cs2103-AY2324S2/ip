package earl.util;

import earl.exceptions.EarlException;
import earl.tasks.Deadline;
import earl.tasks.Event;
import earl.tasks.Task;
import earl.tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class responsible for parsing user and stored entries.
 */
public class Parser {

    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter
            .ofPattern("dd/MM/yyyy HHmm");

    /**
     * Returns a new {@code Task} object based on stored entry.
     *
     * @param entry           a line of text stored in the data file
     * @return                a {@code Task} object of the relevant type
     * @throws EarlException  if stored entry is of unexpected format
     */
    public static Task parseStorageEntry(String entry) throws EarlException {
        String[] task = entry.split(",");
        switch (task[0]) {
        case "T":
            return new Todo(task[1], task[2]);
        case "D":
            return new Deadline(task[1], task[2],
                    LocalDateTime.parse(task[3], DATETIME_FORMAT));
        case "E":
            return new Event(task[1], task[2],
                    LocalDateTime.parse(task[3], DATETIME_FORMAT),
                    LocalDateTime.parse(task[4], DATETIME_FORMAT));
        default:
            throw new EarlException("Storage file is corrupted... "
                    + "starting with empty list.");
        }
    }

    /**
     * Returns the command and the command arguments given the
     * user's input.
     * <p>
     * Splits the user's input at the first space in order to obtain
     * an array of two strings, the first of which is the command.
     * @param input  a line input by the user
     * @return       an array of two {@code String} objects
     */
    public static String[] parseUserInput(String input) {
       return input.split("\\s", 2);
    }

    /**
     * Returns a {@code LocalDateTime} object representing date and time
     * information in the form {@code dd/MM/yyyy HHmm}.
     *
     * @param dateTime  a {@code String} input by the user representing
     *                  date and time in the form {@code dd/MM/yyyy HHmm}
     * @return          a {@code LocalDateTime} object
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DATETIME_FORMAT);
    }

    /** Returns the integer index equivalent of the user's selection */
    public static int parseIndex(String index) {
        return Integer.parseInt(index) - 1;
    }
}

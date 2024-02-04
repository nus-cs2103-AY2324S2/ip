package earl.util;

import earl.exceptions.EarlException;
import earl.tasks.Deadline;
import earl.tasks.Event;
import earl.tasks.Task;
import earl.tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter
            .ofPattern("dd/MM/yyyy HHmm");

    public static Task parseStorageEntry(String entry) throws EarlException {
        String[] task = entry.split(",");
        // as each case returns, break would be unreachable
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

    public static String[] parseUserInput(String input) {
       return input.split("\\s", 2);
    }

    public static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DATETIME_FORMAT);
    }

    public static int parseIndex(String index) {
        return Integer.parseInt(index) - 1;
    }
}

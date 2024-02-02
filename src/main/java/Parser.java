import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter
            .ofPattern("dd/MM/yyyy HHmm");

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

    public static Handler parseUserInput(String input) {
        String[] command = input.split("\\s", 2);
        return Handler.dispatch(command);
    }

    public static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime);
    }

    public static int parseIndex(String index) {
        return Integer.parseInt(index) - 1;
    }
}

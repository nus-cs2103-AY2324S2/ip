import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parseCommand(String commandStart) {
        Command c;
        switch (commandStart) {
        case "bye":
            c = Command.BYE;
            break;
        case "list":
            c = Command.LIST;
            break;
        case "mark":
            c = Command.MARK;
            break;
        case "unmark":
            c = Command.UNMARK;
            break;
        case "todo":
            c = Command.TODO;
            break;
        case "deadline":
            c = Command.DEADLINE;
            break;
        case "event":
            c = Command.EVENT;
            break;
        case "delete":
            c = Command.DELETE;
            break;
        default:
            c = Command.UNKNOWN;
            break;
        }
        return c;
    }

    public static LocalDate parseDate(String dateString) {
        LocalDate date = null;
        // check if in yyyy-mm-dd format
        try {
            date = LocalDate.parse(dateString);
        } catch (Exception e) {
            // do nothing
        }
        return date;
    }
}
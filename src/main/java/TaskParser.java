import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class TaskParser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static Task parseTask(String taskString) throws YapperException {

        String[] parts = taskString.split("\\|");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            try {
                LocalDateTime by = LocalDateTime.parse(parts[3], DATE_TIME_FORMATTER);
                return new Deadline(description, isDone, by);
            } catch (DateTimeParseException e) {
                throw new YapperException("Sorry the date format you yapped is invalid :(");
            }
        case "E":
            try{
                LocalDateTime from = LocalDateTime.parse(parts[3], DATE_TIME_FORMATTER);
                LocalDateTime to = LocalDateTime.parse(parts[4], DATE_TIME_FORMATTER);
                return new Event(description, isDone, from, to);
            } catch (DateTimeParseException e) {
                throw new YapperException("Sorry the date format you yapped is invalid :(");
            }
        default:
            throw new YapperException("The task type you're yapping about is invalid.");
        }
    }
}

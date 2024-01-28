import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

// Adapted from partial solution provided on CS2103 website
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }
    public String getStatusNumber() {
        return (this.isDone ? "1" : "0"); // mark done task with X
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a LocalDateTime object from a date (and time) string.
     *
     * @throws DateTimeParseException if unable to retrieve a date (and time) from the string.
     */
    public static LocalDateTime getLocalDateTimeFromString(String deadlineString) throws DateTimeParseException {
        ArrayList<String> deadlineList = new ArrayList<>(Arrays.asList(deadlineString.split(" ")));

        LocalDate deadlineDate = LocalDate.parse(deadlineList.get(0), Deadline.DATE_INPUT_FORMAT);

        // If only date provided, use the default time
        if (deadlineList.size() == 1) {
            return LocalDateTime.of(deadlineDate, Deadline.DEFAULT_TIME);
        } else {
            LocalTime deadlineTime = LocalTime.parse(deadlineList.get(1), Deadline.TIME_FORMAT);
            return LocalDateTime.of(deadlineDate, deadlineTime);
        }

    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
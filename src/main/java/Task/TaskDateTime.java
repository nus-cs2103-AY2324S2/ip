package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class TaskDateTime {
    private LocalDateTime dateTime;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("EEE, MMM d yyyy HH:mm");

    public TaskDateTime(String dateTime) {
        this.dateTime = LocalDateTime.parse(dateTime, INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return OUTPUT_FORMATTER.format(dateTime);
    }

    public String serialize() {
        return INPUT_FORMATTER.format(dateTime);
    }
}

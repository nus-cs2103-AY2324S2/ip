package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskDateTime {
    private LocalDateTime dateTime;
    private static final String INPUT_FORMAT = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("EEE, MMM d yyyy HH:mm");

    public TaskDateTime(String dateTime) throws DukeDateTimeParseException {
        try {
            this.dateTime = LocalDateTime.parse(dateTime, INPUT_FORMATTER);
        } catch (Exception e) {
            throw new DukeDateTimeParseException("Wrong date and time format: " + dateTime,
                    "Please enter the date and time in the format: " + INPUT_FORMAT);
        }
    }

    @Override
    public String toString() {
        return OUTPUT_FORMATTER.format(dateTime);
    }

    public String serialize() {
        return INPUT_FORMATTER.format(dateTime);
    }
}

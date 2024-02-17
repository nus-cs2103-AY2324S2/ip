package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a date and time used by tasks.
 */
public class TaskDateTime {
    /** The format of the date and time used by the tasks. */
    private static final String INPUT_FORMAT = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    /**
     * The format of the date and time used when displaying the tasks to the user.
     */
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("EEE, MMM d yyyy HH:mm");

    private LocalDateTime dateTime;

    /**
     * Parses the given string into a date and time.
     *
     * @param dateTime The date and time string.
     * @throws DukeDateTimeParseException If the date and time is not in the correct
     *                                    format.
     */
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

    /**
     * Returns the serialized string of this date and time. The serialized string
     * will be stored in the data file.
     *
     * @return The serialized string of the date and time.
     */
    public String serialize() {
        return INPUT_FORMATTER.format(dateTime);
    }
}

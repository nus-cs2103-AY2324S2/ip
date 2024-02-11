package duke;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * TimeBlock is a class that extends Task. It represents a task that has a
 * specific time block
 */
public class TimeBlock extends Task {
    // The start time of the task.
    protected String fromTime;
    // The end time of the task.
    protected String toTime;

    /**
     * Constructs a new TimeBlock object
     *
     * @param description The description of the task
     * @param fromTime    The start time of the task
     * @param toTime      The end time of the task
     */
    public TimeBlock(String description, String fromTime, String toTime) throws BotException {
        super(description);
        // The various formats that the time can be in
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("HHmm");

        // Check if the time is in the correct format
        try {
            LocalDateTime.parse(fromTime, formatter1);
            this.fromTime = fromTime;
        } catch (DateTimeParseException e1) {
            try {
                LocalDateTime.parse(fromTime, formatter2);
                this.fromTime = fromTime;
            } catch (DateTimeParseException e2) {
                try {
                    LocalTime.parse(fromTime, formatter3);
                    this.fromTime = fromTime;
                } catch (DateTimeParseException e3) {
                    throw new BotException(
                            "Invalid fromTime format. Please use either 'd/M/yyyy HHmm', 'MMM dd yyyy HH:mm' or 'HHmm'.");
                }
            }
        }
        try {
            LocalDateTime.parse(toTime, formatter1);
            this.toTime = toTime;
        } catch (DateTimeParseException e1) {
            try {
                LocalDateTime.parse(toTime, formatter2);
                this.toTime = toTime;
            } catch (DateTimeParseException e2) {
                try {
                    LocalTime.parse(toTime, formatter3);
                    this.toTime = toTime;
                } catch (DateTimeParseException e3) {
                    throw new BotException(
                            "Invalid toTime format. Please use either 'd/M/yyyy HHmm', 'MMM dd yyyy HH:mm' or 'HHmm'.");
                }
            }
        }
    }

    /**
     * Returns a string representation of the TimeBlock object
     *
     * @return A string representation of the TimeBlock object, including the task
     *         type (E), the task description,
     *         the start time, and the end time
     */
    @Override
    public String toString() {
        return "E" + super.toString() + " | from: " + this.fromTime + " to: " + this.toTime + "";
    }
}
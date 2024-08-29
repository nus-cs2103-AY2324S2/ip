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
        this.fromTime = parseTime(fromTime);
        this.toTime = parseTime(toTime);
    }

    /**
     * Parses the given time string into a LocalDateTime or LocalTime object.
     * The time string should be in one of the following formats:
     * 'd/M/yyyy HHmm', 'MMM dd yyyy HH:mm', or 'HHmm'.
     *
     * @param time The time string to parse.
     * @return The original time string if it is in a valid format.
     * @throws BotException If the time string is not in a valid format.
     */
    private String parseTime(String time) throws BotException {
        assert time != null : "Time should not be null";
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("HHmm");

        try {
            LocalDateTime.parse(time, formatter1);
            return time;
        } catch (DateTimeParseException e1) {
            try {
                LocalDateTime.parse(time, formatter2);
                return time;
            } catch (DateTimeParseException e2) {
                try {
                    LocalTime.parse(time, formatter3);
                    return time;
                } catch (DateTimeParseException e3) {
                    throw new BotException(
                            "Invalid time format." +
                                    "\nPlease use either 'd/M/yyyy HHmm', 'MMM dd yyyy HH:mm' or 'HHmm'."
                                    + "\n For example, '2/12/2020 1800', 'Dec 2 2025 18:00' or '1800'.");
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
package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime END_TIME;

    /**
     * Constructs a new Deadline instance.
     *
     * @param description The description of the deadline task.
     * @param endTime     The end time of the deadline task.
     */
    public Deadline(String description, LocalDateTime endTime) {
        super(description);
        this.END_TIME = endTime;
    }

    /**
     * Returns the string representation of the deadline task,
     * including the description and end time which is formatted as "MMM dd yyyy".
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "D |" + super.toString().substring(1) + "| " + END_TIME.format(formatter);
    }

    /**
     * Gets the end time of the deadline task.
     *
     * @return The end time of the deadline task.
     */
    public LocalDateTime getEndTime() {
        return END_TIME;
    }
}
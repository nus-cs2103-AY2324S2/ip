package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate START_TIME;
    protected LocalDate END_TIME;

    /**
     * Constructs an Event object.
     *
     * @param description The description of the event task.
     * @param startTime   The start date of the event.
     * @param endTime     The end date of the event.
     */
    public Event(String description, LocalDate startTime, LocalDate endTime) {
        super(description);
        this.START_TIME = startTime;
        this.END_TIME = endTime;
    }

    /**
     * Returns a string representation of the event task, including its status,
     * description, start time, and end time
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedStartTime = START_TIME.format(formatter);
        String formattedEndTime = END_TIME.format(formatter);
        return "E |" + super.toString().substring(1) + "| " + formattedStartTime + " to " + formattedEndTime;
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time of the event.
     */
    public LocalDate getStartTime() {
        return START_TIME;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time of the event.
     */
    public LocalDate getEndTime() {
        return END_TIME;
    }
}
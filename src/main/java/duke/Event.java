package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate startTime;
    protected LocalDate endTime;

    /**
     * Constructs an Event object.
     *
     * @param description The description of the event task.
     * @param startTime   The start date of the event.
     * @param endTime     The end date of the event.
     */
    public Event(String description, LocalDate startTime, LocalDate endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
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
        String formattedStartTime = startTime.format(formatter);
        String formattedEndTime = endTime.format(formatter);
        return "E |" + super.toString().substring(1) + "| " + formattedStartTime + " to " + formattedEndTime;
        //return "E |" + super.toString().substring(1) + "| " + getExactTime();
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time of the event.
     */
    public LocalDate getStartTime() {
        return startTime;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time of the event.
     */
    public LocalDate getEndTime() {
        return endTime;
    }
}

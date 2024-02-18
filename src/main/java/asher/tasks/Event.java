package asher.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a type of task called Event.
 */
public class Event extends Task {
    protected LocalDate startDate;
    protected LocalTime startTime;
    protected LocalDate endDate;
    protected LocalTime endTime;

    /**
     * Constructs an Event with the given start date and end date.
     *
     * @param description The description of the event.
     * @param startDate The start date of the event.
     * @param startTime The start time of the event.
     * @param endDate The end date of the event.
     * @param endTime The end time of the event.
     */
    public Event(String description, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        super(description);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    /**
     * Retrieves the start date of the task.
     *
     * @return The start date in LocalDate.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Retrieves the end date of the task.
     *
     * @return The end date in LocalDate.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Retrieves the start date of the task.
     *
     * @return The start date in LocalTime.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Retrieves the end date of the task.
     *
     * @return The end date in LocalTime.
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public String writeToString() {
        String status = isDone ? "1" : "0";
        return "E" + " | " + status + " | " + description + " | " + startDate + " | " + startTime + " | " + endDate + " | " + endTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formattingDate = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter formattingTime = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedStartDate = startDate.format(formattingDate);
        String formattedEndDate = endDate.format(formattingDate);
        String formattedStartTime = startTime.format(formattingTime);
        String formattedEndTime = endTime.format(formattingTime);
        return " [E]" + super.toString() + " (from: " + formattedStartDate + "," + " " + formattedStartTime + " to: " + formattedEndDate + "," + " " + formattedEndTime + ")";
    }
}

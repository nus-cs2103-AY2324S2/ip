package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Event Class is a type of task the user can create.
 * Two sets of date and time is required as an input for the event.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Constructor for Event Class.
     * @param Task
     * @param taskType
     * @param start
     * @param end
     */
    public Event(String Task, String taskType, String start, String end) {
        super(Task, taskType);
        this.start = LocalDateTime.parse(start,formatter);
        this.end = LocalDateTime.parse(end,formatter);
    }

    /**
     * Returns a string representation of the Event for the user to view.
     * @return String representation of the Event.
     */
    public String toString() {
        return this.getTaskType() + this.getStatus() + " " + this.getTask() + this.getPeriod();
    }

    /**
     * Returns a string representation of date and time of the deadline.
     * @return String representation of date and time of the deadline.
     */
    public String getPeriod() {
        return " (From: " + startDateToString() + " To: " + endDateToString() + ")";
    }

    /**
     * Informs the user a new Event has been created.
     * @return String informing a new Event has been created.
     */
    public String announcement() {
        return "New Event created!";
    }

    /**
     * Returns a string representation of the Event for txt file saving purposes.
     * @return String representation of the Event for txt file saving purposes.
     */
    public String saveString() {
        return this.getTaskTypeSingle() + "|" + this.getStatusBinary() + "|" + this.getTask() + "|"
                + this.startDateToString() + "|" + this.endDateToString();
    }

    /**
     * Returns a string representation of start the date and time of the event.
     * @return String representation of start the date and time of event.
     */
    public String startDateToString() {
        return this.start.format(formatter).replace("T", " ");
    }

    /**
     * Returns a string representation of the end date and time of the event.
     * @return String representation of the end date and time of the event.
     */
    public String endDateToString() {
        return this.end.format(formatter).replace("T", " ");
    }
}

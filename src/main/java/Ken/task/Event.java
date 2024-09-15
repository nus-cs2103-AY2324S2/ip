package ken.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task with a specific start and end time.
 *
 * It extends the Task class and includes additional information about the event duration.
 */

public class Event extends Task {

    /**
     * The start time of the event.
     */
    protected LocalDateTime from;

    /**
     * The end time of the event.
     */
    protected LocalDateTime to;


    /**
     * Constructs an Event object with the given description and event duration.
     *
     * @param description The description of the event task.
     * @param from The start time of the event in the format "yyyy-MM-dd HHmm".
     * @param to The end time of the event in the format "yyyy-MM-dd HHmm".
     */
    public Event(String description, String from, String to) {
        super(description);
        assert from != null;
        assert to != null;
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns a string representation of the event from.
     *
     * @return A string representation of the event from.
     */
    public String getFrom() {
        return " \n(from: " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    /**
     * Returns a string representation of the event to.
     *
     * @return A string representation of the event to.
     */
    public String getTo() {
        return " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }



    /**
     * Returns a string representation of the event task.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + getFrom() + getTo();
    }

    /**
     * Returns a string representation of the event from for file storage.
     *
     * @return A string representation of the event from for file storage.
     */
    public String toFileStringFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns a string representation of the event to for file storage.
     *
     * @return A string representation of the event to for file storage.
     */
    public String toFileStringTo() {
        return this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns a string representation of the event task for file storage.
     *
     * @return A string representation of the event task for file storage.
     */
    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | " + toFileStringFrom() +
                " | " + toFileStringTo();
    }

}


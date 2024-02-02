package tasks;

import java.time.LocalDate;

/**
 * Represents an event task.
 * An event is a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {

    protected LocalDate from; //The start date of the event
    protected LocalDate to; //The end date of the event

    /**
     * Constructs an Event task with the specified description, start date, and end date.
     *
     * @param description The description of the event task.
     * @param from The start date of the event in the format of a String that can be parsed as a LocalDate.
     * @param to The end date of the event in the format of a String that can be parsed as a LocalDate.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Returns a string representation of the event task, including its type,
     * status, description, start date, and end date.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.getMonth() + " " + from.getDayOfMonth() + " " + from.getYear()
                + " to " + to.getMonth() + " " + to.getDayOfMonth() + " " + to.getYear() + ")";
    }
    
    /**
     * Returns a string formatted for file storage, including the task type,
     * completion status, description, start date, and end date.
     *
     * @return A string suitable for storing the task in a file.
     */
    public String toFileFormat() {
        String completed = this.isDone ? "1" : "0";
        return "E | " + completed + " | " + this.description + " | " + this.from + " | " + this.to;
    }
}
package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class which extends the Task class and includes a start day and end day
 * represented by LocalDateTime objects.
 */
public class Event extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    /**
     * Constructor for Event class
     * @param title
     * @param startTime
     * @param endTime
     */
    public Event(String title, LocalDateTime startTime, LocalDateTime endTime) {
        super(title);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Helper function to generate a string representation of the Event object for saving purposes.
     *
     * @return A string representation of the Event object for saving to a file.
     */
    @Override
    public String save() {
        if (this.getMarked()) {
            return "E | Done | " + this.title;
        } else {
            return "E | Not Done | " + this.title;
        }
    }

    @Override
    public String toString() {
        if (this.getMarked()) {
            return "[E][X] " + this.title + " (from: "
                    + this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " to: " + this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[E][ ] " + this.title + " (from: "
                    + this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " to: " + this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }

    @Override
    public LocalDateTime getDate() {
        return this.startTime;
    }

}

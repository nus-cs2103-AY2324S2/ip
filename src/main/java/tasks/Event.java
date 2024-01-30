package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class that encapsulates the ToDo tasks, a type of Task.
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class Event extends Task {
    /** A String value that represent the type of Task, Efor Event. */
    private static final String TYPE = "E";

    /** A LocalDateTime value that represents the time of the start of event. */
    private LocalDate startTime;

    /** A LocalDateTime value that represents the time of the end of event. */
    private LocalDate endTime;

    private static final DateTimeFormatter STORAGEFORMAT = DateTimeFormatter.ofPattern("d-M-yy");
    private static final DateTimeFormatter PRINTFORMAT = DateTimeFormatter.ofPattern("MMM-d-yy");

    /**
     * Constructor for the Event.
     * 
     * @param name      A String value that states the name of the Task.
     * @param startTime A String representation of the start of the event.
     * @param endTime   A String representation of the end of the event.
     */
    public Event(String name, LocalDate startTime, LocalDate endTime) {
        super(name, TYPE, false);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String name, LocalDate startTime, LocalDate endTime, boolean completed) {
        super(name, TYPE, completed);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts the data (Event) here into a format to be stored in the file.
     * 
     * @return String representation of the Event to be store in local disk.
     */
    @Override
    public String convertToStorageFormat() {
        return super.convertToStorageFormat() + " | " + this.startTime.format(STORAGEFORMAT) + " | "
                + this.endTime.format(STORAGEFORMAT);
    }

    /**
     * String representation of an Event.
     * 
     * @return Returns the String representation of an Event.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + this.startTime.format(PRINTFORMAT) + " to: "
                + this.endTime.format(PRINTFORMAT) + ")";
        // [T][X] name (from: startTime to: endTime)
    }
}

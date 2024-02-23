package kbot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A class that encapsulates the ToDo tasks, a type of Task.
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class Event extends Task {
    /** A String value that represent the type of Task, Efor Event. */
    private static final String TYPE = "E";

    /** A LocalDate value that represents the date of the start of event. */
    private LocalDate startDate;

    /** A LocalDate value that represents the date of the end of event. */
    private LocalDate endDate;

    private static final DateTimeFormatter STORAGE_FORMAT = DateTimeFormatter.ofPattern("d-M-yy");
    private static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("MMM-d-yyyy");

    /**
     * Constructor for the Event.
     * 
     * @param name      A String value that states the name of the Task.
     * @param startDate A LocalDate representation of the start of the event.
     * @param endDate   A LocalDate representation of the end of the event.
     * @param tags      A list of tags to identify the task.
     */
    public Event(String name, LocalDate startDate, LocalDate endDate, ArrayList<String> tags) {
        super(name, TYPE, false, tags);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constuctor used when we are loading from storage.
     * 
     * @param name        A String value that states the name of the Task.
     * @param startDate   A LocalDateTime to state the start of event.
     * @param endDate     A LocalDateTime to state the end of event.
     * @param isCompleted Boolean of whether Task is completed.
     * @param tags        A list of tags to identify the task.
     */
    public Event(String name, LocalDate startDate, LocalDate endDate, boolean isCompleted, ArrayList<String> tags) {
        super(name, TYPE, isCompleted, tags);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Searches for a key in name.
     * 
     * @param key String to find in the name.
     * @return Boolean if key is found or not.
     */
    @Override
    public boolean containsWord(String key) {
        return super.containsWord(key);
    }

    /**
     * Converts the data (Event) here into a format to be stored in the file.
     * 
     * @return String representation of the Event to be store in local disk.
     */
    @Override
    public String convertToStorageFormat() {
        return super.convertToStorageFormat() + " | " + this.startDate.format(STORAGE_FORMAT) + " | "
                + this.endDate.format(STORAGE_FORMAT) + " | " + super.tagsToStorageFormat();
    }

    /**
     * String representation of an Event.
     * 
     * @return Returns the String representation of an Event.
     */
    @Override
    public String toString() {
        // [T][X] name (from: startTime to: endTime)
        return super.toString() + " (from: " + this.startDate.format(PRINT_FORMAT) + " to: "
                + this.endDate.format(PRINT_FORMAT) + ") Tags:" + super.getTags();
    }
}

package kbot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A class that encapsulates the Deadlines tasks, a type of Task.
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class Deadline extends Task {
    /** A String value that represent the type of Task, D for Deadline. */
    private static final String TYPE = "D";

    /** Storage date format. */
    private static final DateTimeFormatter STORAGE_FORMAT = DateTimeFormatter.ofPattern("d-M-yy");

    /** Output date format. */
    private static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("MMM-d-yyyy");

    /** A LocalDataTime value that represents the date of deadline. */
    private LocalDate endDate;

    /**
     * Constructor for the ToDo.
     * 
     * @param name    A String value that states the name of the Task.
     * @param endDate A LocalDate to state the date of deadline.
     * @param tags    A list of tags to identify the task.
     */
    public Deadline(String name, LocalDate endDate, ArrayList<String> tags) {
        super(name, TYPE, false, tags);
        this.endDate = endDate;
    }

    /**
     * Constuctor used when we are loading from storage.
     * 
     * @param name        A String value that states the name of the Task.
     * @param endDate     LocalDate of when the deadline is.
     * @param isCompleted Boolean of whether Task is completed.
     * @param tags        A list of tags to identify the task.
     */
    public Deadline(String name, LocalDate endDate, boolean IsCompleted, ArrayList<String> tags) {
        super(name, TYPE, IsCompleted, tags);
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
     * Converts the data (Deadline) here into a format to be stored in the file.
     * 
     * @return String representation of the Deadline to be store in local disk.
     */
    @Override
    public String convertToStorageFormat() {
        return super.convertToStorageFormat() + " | " + this.endDate.format(STORAGE_FORMAT) + " | "
                + super.tagsToStorageFormat();
    }

    /**
     * String representation of a Deadline.
     * 
     * @return Returns the String representation of a Deadline.
     */
    @Override
    public String toString() {
        // [T][X] name (by: time)
        String formattedDeadline = this.endDate.format(PRINT_FORMAT);
        return super.toString() + " (by: " + formattedDeadline + ") Tags:" + super.getTags(); // [T][X] name (by: time)
    }
}

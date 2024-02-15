package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private static final DateTimeFormatter STORAGEFORMAT = DateTimeFormatter.ofPattern("d-M-yy");

    /** Output date format. */
    private static final DateTimeFormatter PRINTFORMAT = DateTimeFormatter.ofPattern("MMM-d-yyyy");

    /** A LocalDataTime value that represents the date of deadline. */
    private LocalDate endDate;

    /**
     * Constructor for the ToDo.
     * 
     * @param name    A String value that states the name of the Task.
     * @param endTime A LocalDate to state the date of deadline.
     */
    public Deadline(String name, LocalDate endDate) {
        super(name, TYPE, false);
        this.endDate = endDate;
    }

    /**
     * Constuctor used when we are loading from storage.
     * 
     * @param name      A String value that states the name of the Task.
     * @param endDate   LocalDate of when the deadline is.
     * @param completed Boolean of whether Task is completed.
     */
    public Deadline(String name, LocalDate endDate, boolean IsCompleted) {
        super(name, TYPE, IsCompleted);
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
        return super.convertToStorageFormat() + " | " + this.endDate.format(STORAGEFORMAT);
    }

    /**
     * String representation of a Deadline.
     * 
     * @return Returns the String representation of a Deadline.
     */
    @Override
    public String toString() {
        // [T][X] name (by: time)
        String formattedDeadline = this.endDate.format(PRINTFORMAT);
        return super.toString() + " (by: " + formattedDeadline + ")"; // [T][X] name (by: time)
    }
}

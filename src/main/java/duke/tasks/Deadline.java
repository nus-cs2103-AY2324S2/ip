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

    private static final DateTimeFormatter STORAGEFORMAT = DateTimeFormatter.ofPattern("d-M-yy");
    private static final DateTimeFormatter PRINTFORMAT = DateTimeFormatter.ofPattern("MMM-d-yyyy");

    /** A LocalDataTime value that represents the time of deadline. */
    private LocalDate endTime;

    /**
     * Constructor for the ToDo.
     * 
     * @param name A String value that states the name of the Task.
     * @param time A LocalDateTime to state the time of deadline.
     */
    public Deadline(String name, LocalDate endTime) {
        super(name, TYPE, false);
        this.endTime = endTime;
    }

    public Deadline(String name, LocalDate endTime, boolean completed) {
        super(name, TYPE, completed);
        this.endTime = endTime;
    }

    public boolean find(String key) {
        return super.find(key);
    }

    /**
     * Converts the data (Deadline) here into a format to be stored in the file.
     * 
     * @return String representation of the Deadline to be store in local disk.
     */
    @Override
    public String convertToStorageFormat() {
        return super.convertToStorageFormat() + " | " + this.endTime.format(STORAGEFORMAT);
    }

    /**
     * String representation of a Deadline.
     * 
     * @return Returns the String representation of a Deadline.
     */
    @Override
    public String toString() {
        String formattedDeadline = this.endTime.format(PRINTFORMAT);
        return super.toString() + " (by: " + formattedDeadline + ")"; // [T][X] name (by: time)

    }
}

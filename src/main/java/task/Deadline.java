package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a type of task that has a deadline
 * Extends Task class
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Instantiates a deadline object
     * @param name Describes what the deadline task is
     * @param isDone A boolean to indicate if the deadline task is completed
     * @param by  The deadline of the deadline task
     */
    public Deadline(String name, boolean isDone, LocalDateTime by) {
        super(name, isDone);

        this.by = by;
    }

    /**
     * Gets the deadline of the deadline task
     *
     * @return The deadline of the task as a local date time object
     */
    public LocalDateTime getTime() {
        return this.by;
    }

    /**
     * Returns a string representation of the deadline object
     * @return the type, status, and deadline of the task
     */

    @Override
    public String toString(){
        return "[D] " +  super.toString() + " (by:" + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**\
     * Returns a string representation of the deadline object for storage
     * @return the type, status represented as a number and deadline of task in the format to be stored
     */
    @Override
    public String toFileString() {
        return "D" + " | " + getStatusNum() + " | " + this.name + " | " + by.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm")) ;
    }


}
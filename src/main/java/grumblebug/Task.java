package grumblebug;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is added by user. Can be of several different types.
 */
public class Task {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    protected String description;
    protected boolean isDone;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected char taskType;

    /**
     * Constructor for To-do type task.
     * @param done Doneness of task.
     * @param description Description of task.
     */
    public Task(boolean done, String description) {
        this.description = description;
        this.isDone = done;
        this.taskType = 'T';
    }

    /**
     * Constructor for Deadline type task.
     * @param done Doneness of task.
     * @param description Description of task.
     * @param end End date of task, aka deadline day.
     */
    public Task(boolean done, String description, LocalDate end) {
        this.description = description;
        this.isDone = done;
        this.endDate = end;
        this.taskType = 'D';
    }

    /**
     * Constructor for Event type task.
     * @param done Doneness of task.
     * @param description Description of task.
     * @param start Start date of Event.
     * @param end End date of Event.
     */
    public Task(boolean done, String description, LocalDate start, LocalDate end) {
        this.description = description;
        this.isDone = done;
        this.taskType = 'E';
        this.startDate = start;
        this.endDate = end;
    }

    /**
     * To return a representation of doneness of task.
     * @return X or no X, for doneness.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " "; // mark done task with X
    }

    /**
     * To return full status representing a task.
     * @return a String that is readable easily, showing task info.
     */
    public String getFullStatus() {
        String status = ". [" + this.getStatusIcon() + "] ["
                + this.taskType + "] "
                + this.description;
        if (this.taskType == 'E') {
            status += "\nfrom: " + this.startDate.format(formatter);
        }
        if (this.taskType == 'D' || this.taskType == 'E') {
            status += "\nend: " + this.endDate.format(formatter);
        }
        return status;
    }

    /**
     * Set the doneness state of a task.
     * @param doneness Boolean representing new state to set.
     */
    public void setDone(boolean doneness) {
        this.isDone = doneness;
    }

}


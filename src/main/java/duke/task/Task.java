package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Represents a task in the task list.
 */
public class Task {
    protected String description;
    protected int isDone;

    public Task(String description, int isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of the task.
     * @return The status of the task.
     */
    public int getIsDone() {
        return this.isDone;
    }
    /**
     * Returns the status icon of the task.
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        if (isDone == 0) {
            return " ";
        } else {
            return "X";
        }
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = 1;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = 0;
    }

    /**
     * Returns the string representation of the task.
     * @return The string representation of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

}

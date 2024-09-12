package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a description and a done status. This class serves as a base class for different types of tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    
    /**
     * Creates a new Task with the specified description. The task is initially not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    /**
     * Returns the status icon of the task, indicating whether it is done or not.
     *
     * @return A string representing the status icon ("X" for done, " " for not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    
    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }
    
    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }
    
    /**
     * Sets the done status of the task.
     *
     * @param status The new done status of the task.
     */
    public void setStatus(boolean status) {
        this.isDone = status;
    }
    
    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
    
    /**
     * Formats a given LocalDate for printing, using the format "MMM dd yyyy".
     *
     * @param date The date to be formatted.
     * @return A string representing the formatted date.
     */
    public static String formatDateForPrinting(LocalDate date) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(outputFormatter);
    }
}

package toothless.tasks;

import toothless.ToothlessException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This abstract class represents a task within the Toothless application.
 * This class provides the common structure and functionality for various types of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    /**
     * Returns a string representation of the task, typically the task's description.
     * @return The task's description.
     */
    @Override
    public String toString() {
        return " [" + getTaskIcon() + "][" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the status icon of the task, indicating whether it is done.
     * @return A string "X" if the task is done, or a space " " if it is not done.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " "; // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Formats the task data for writing into storage.
     * @return A string representation of the task's storage format.
     */
    public String toWrite() {
        return (isDone ? 1 : 0) + " | " + description;
    }

    /**
     * Checks if the task is marked as done.
     * @return true if the task is marked, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Formats a LocalDateTime into a different format, "MMM dd yyyy HH:mm".
     * @param dateTime The LocalDateTime object to format.
     * @return A string representation of the date and time in "MMM dd yyyy HH:mm" format.
     */
    public String dateTimeFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy hh:mm"));
    }

    public String dateTimeToWriteFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }


    /**
     * Checks if the task's description contains the keyword.
     * @param keyword A String representing the keyword.
     * @return true if the task's description contains keyword, false otherwise.
     */
    public boolean contains(String keyword){
        return description.contains(keyword);
    }
    
    /**
     * Returns the icon representing the type of the task.
     * @return A string representing the task's type icon.
     */
    public abstract String getTaskIcon();
}

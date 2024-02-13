package duke;

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
     * Converts a string to a date.
     * @param input The string to be converted to a date.
     * @return The date in the format MMM d yyyy.
     */
    public String stringToDate(String input) {
        try {
            LocalDate ld = LocalDate.parse(input);
            String date = ld.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return date;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter a date in yyyy-MM-dd format.");
            return null;
        }
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
        System.out.println("This task is marked as done:\n"
                + this);
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = 0;
        System.out.println("This task is marked as not done yet:\n"
                + this);
    }

    /**
     * Returns the string representation of the task.
     * @return The string representation of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

}

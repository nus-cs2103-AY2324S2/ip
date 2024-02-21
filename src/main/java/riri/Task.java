package riri;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parent class that deadline, todo and event inherit from.
 */
public class Task {
    private String task;
    private boolean isDone;
    /**
     * Constructor for the Task object.
     * @param task task inputted by user
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }
    /**
     * Marks the task as done if complete
     */
    public void markDone() {
        this.isDone = true;
        return;
    }
    /**
     * Marks the task as undone if incomplete
     */
    public void markUndone() {
        this.isDone = false;
    }
    /**
     * Function to help check if a task is done
     * @return true if task is done, false otherwise
     */
    public boolean getIsDone() {
        return this.isDone;
    }
    /**
     * Function returns the status of the object
     * @return X if task is done, otherwise " "
     */
    public String getStatus() {
        return (this.getIsDone()) ? "X" : " ";
    }
    /**
     * Function takes a String and parses it into a LocalDate format
     * @param date String to be parsed as a LocalDate
     */
    public LocalDate parseDate(String date) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("M/d/yyyy");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("MMM d yyyy");
        DateTimeFormatter[] formatters = {formatter1, formatter2, formatter3, formatter4, formatter5};

        for (DateTimeFormatter dateFormat : formatters) {
            try {
                return LocalDate.parse(date, dateFormat);
            } catch (DateTimeParseException e) {
                // Parsing failed for this pattern, try the next one
            }
        }

        throw new IllegalArgumentException("Try writing the date in this format: M/d/yyyy HHmm");
    }
    /**
     * Convert the LocalDate object to a string in a particular format
     */
    public String stringifyDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.task;
    }
}

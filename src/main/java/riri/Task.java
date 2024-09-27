package riri;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parent class that represents tasks with deadlines, todos, and events.
 */
public class Task {
    private String task;
    private boolean isDone;
    /**
     * Constructor for the Task object.
     *
     * @param task The task inputted by the user.
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
    }
    /**
     * Marks the task as undone if incomplete
     */
    public void markUndone() {
        this.isDone = false;
    }
    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }
    /**
     * Returns the status of the task.
     *
     * @return "X" if the task is done, otherwise " ".
     */
    public String getStatus() {
        return (this.getIsDone()) ? "X" : " ";
    }
    /**
     * Parses a string into a LocalDate object using multiple date formats.
     *
     * @param date The string to be parsed as a LocalDate.
     * @return The LocalDate object parsed from the string.
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
     * Converts a LocalDate object to a string in a particular format.
     *
     * @param date The LocalDate object to be converted.
     * @return The string representation of the date in the format "MMM d yyyy".
     */
    public String stringifyDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.task;
    }
}

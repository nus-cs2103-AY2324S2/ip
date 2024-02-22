package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that can store the description and status of the task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a Task with the description and status of not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        assert description != null & !description.isEmpty() : "Description should not be null";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Accepts a date in String format of "d-M-yyyy" and returns the date in LocalDate format.
     *
     * @param dateString date in String format of "d-M-yyyy"
     * @return date of the task
     */
    public static LocalDate getLocalDateInput(String dateString) throws DateTimeParseException {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d-M-yyyy"));
    }

    /**
     * Accepts a date in String format of "d-M-yyyy HHmm" and returns the date time in LocalDateTime format.
     *
     * @param dateTimeString datetime in String format of "d-M-yyyy HHmm"
     * @return date of the task
     */
    public static LocalDateTime getLocalDateTimeInput(String dateTimeString) throws DukeException {
        String[] splitDateTime = dateTimeString.split(" ");
        if (splitDateTime.length == 1) {
            return LocalDateTime.parse(dateTimeString + " " + LocalTime.MIDNIGHT.toString(),
                DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"));
        } else if (splitDateTime.length == 2) {
            return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("d-M-yyyy HHmm"));
        } else {
            throw new DukeException("Date and time should be in the format of dd-M-yyyy HHmm");
        }
    }

    /**
     * Accepts a date in LocalDate format and returns the date time in given pattern.
     *
     * @param date date in LocalDate format
     * @return date of the task in String format of given pattern
     */
    public static String getLocalDateOutput(LocalDate date, String datePattern) throws DateTimeParseException {
        return date.format(DateTimeFormatter.ofPattern(datePattern));
    }

    /**
     * Accepts a date in LocalDateTime format and returns the date time in given pattern.
     *
     * @param date date in LocalDateTime format
     * @param dateTimePtn the pattern for date time formatter
     * @return date of the task in String format of given pattern
     */
    public static String getLocalDateTimeOutput(LocalDateTime date, String dateTimePtn)throws DateTimeParseException {
        return date.format(DateTimeFormatter.ofPattern(dateTimePtn));
    }

    /**
     * Returns X if the task is done, else return empty space.
     *
     * @return status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done yet.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the type of the task.
     *
     * @return task type
     */
    public String getTaskType() {
        return " ";
    }

    /**
     * Returns all the information of the task in String format.
     *
     * @return all the information of the task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

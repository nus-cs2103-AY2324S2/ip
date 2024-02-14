package jayne.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jayne.JayneException;
/**
 * Represents a task with a deadline.
 * It contains all the functionality of a task with the addition
 * of a deadline date.
 */
public class Deadline extends Task {
    private static final String DATE_FORMAT_STRING = "MMM dd yyyy";
    private static final String NO_DATE_STRING = "no date";
    protected LocalDate by;
    /**
     * Constructs a Deadline task with a description and deadline date.
     *
     * @param description the description of the deadline task.
     * @param by the deadline date as a String.
     */
    public Deadline(String description, String by) throws JayneException {
        super(description);
        this.by = parseDate(by);
    }
    public LocalDate getBy() {
        return by;
    }
    /**
     * Parses the date string to LocalDate.
     * If the date string is not in a valid format, prints an error message and returns null.
     *
     * @param dateString the date string to be parsed.
     * @return the parsed LocalDate or null if parsing fails.
     */
    private LocalDate parseDate(String dateString) throws JayneException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new JayneException("Unable to parse the date: " + dateString);
        }
    }
    /**
     * Formats the given LocalDate to a more readable form.
     * If the given LocalDate is null, returns 'no date'.
     *
     * @param date the date to be formatted.
     * @return the formatted date string or 'no date' if the date is null.
     */
    private String formatDate(LocalDate date) {
        if (date == null) {
            return NO_DATE_STRING;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_STRING);
        return date.format(formatter);
    }
    /**
     * Returns the string representation of the deadline task, including its type, status, description, and deadline.
     *
     * @return a string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate(by) + ")";
    }
    /**
     * Returns the string representation of the deadline task in a format suitable for file storage.
     * The format includes the task type, status, description, and deadline date.
     *
     * @return a string representation of the deadline task for file storage.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return "D | " + super.toFileFormat() + " | " + (by != null ? by.format(formatter) : "");
    }


}

package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.InvalidDateException;
import exception.InvalidTaskFormatException;

/**
 * Represents a deadline task.
 * <p>
 * This class extends the {@link Task} class and is used to represent tasks that
 * have a deadline. It provides a constructor to create a new deadline task with
 * a description and a deadline date.
 * </p>
 */
public class Deadline extends Task {

    protected LocalDate by;
    protected LocalDate by;

    /**
     * Creates a new {@code Deadline} instance with the specified description and
     * deadline date.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline date of the deadline task.
     */
    public static Deadline createFromInput(String input) throws InvalidTaskFormatException, InvalidDateException {
        try {
            String[] parts = input.split(" /by ");
            String description = parts[0].split("deadline ")[1];
            String by = parts[1];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsedBy = LocalDate.parse(by, formatter);
            return new Deadline(description, parsedBy);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskFormatException(
                    "Invalid deadline format. Please use 'deadline description /by date'.");
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Invalid date format. Please use 'yyyy-MM-dd'.");
        }
    }

    /**
     * Constructs a new {@code Deadline} instance with the specified description and
     * deadline date.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline date of the deadline task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /*
     * Returs the string representation of the deadline task.
     * 
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /*
     * Returns the string representation of the deadline task to be saved in the
     * hard disk.
     * 
     * @return The string representation of the deadline task to be saved in the
     * hard
     * disk.
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}

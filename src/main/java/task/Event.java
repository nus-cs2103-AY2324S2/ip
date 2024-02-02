package task;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.InvalidDateException;
import exception.InvalidTaskFormatException;

import java.time.LocalDate;

/**
 * Represents an event task.
 * <p>
 * This class extends the {@link Task} class and is used to represent tasks that
 * have a start and end date. It provides a constructor to create a new event
 * task with a description, start date and end date.
 * </p>
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate by;

    /**
   * Creates a new {@code Event} instance with the specified description, start
   * date and end date.
   *
   * @param description The description of the event task.
   * @param from        The start date of the event task.
   * @param by          The end date of the event task.
   */
  public static Event createFromInput(String input) throws InvalidTaskFormatException, InvalidDateException {
        try {
            String[] parts = input.split(" /from | /to ");
            String description = parts[0].split("event ")[1];
            String at = parts[1];
            String by = parts[2];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsedAt = LocalDate.parse(at, formatter);
            LocalDate parsedBy = LocalDate.parse(by, formatter);
            return new Event(description, parsedAt, parsedBy);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskFormatException(
                    "Invalid event format. Please use 'event description /from date /to date'.");
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Invalid date format. Please use 'yyyy-MM-dd'.");
        }
    }

    /**
   * Constructs a new {@code Event} instance with the specified description, start
   * date and end date.
   *
   * @param description The description of the event task.
   * @param from        The start date of the event task.
   * @param by          The end date of the event task.
   */
  public Event(String description, LocalDate from, LocalDate by) {
        super(description);
        this.from = from;
        this.by = by;
    }

    /*
   * Returs the string representation of the event task.
   * 
   * @return The string representation of the event task.
   */
  @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /*
   * Returns the string representation of the event task to be saved in the hard
   * disk.
   * 
   * @return The string representation of the event task to be saved in the hard
   * disk.
   */
  @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + by;
    }
}

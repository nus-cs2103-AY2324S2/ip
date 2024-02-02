package task;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.InvalidDateException;
import exception.InvalidTaskFormatException;

import java.time.LocalDate;

public class Event extends Task {

  protected LocalDate from;
  protected LocalDate by;

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
      throw new InvalidTaskFormatException("Invalid event format. Please use 'event description /from date /to date'.");
    } catch (DateTimeParseException e) {
      throw new InvalidDateException("Invalid date format. Please use 'yyyy-MM-dd'.");
    }
  }

  public Event(String description, LocalDate from, LocalDate by) {
    super(description);
    this.from = from;
    this.by = by;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: "
        + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
  }

  @Override
  public String toFileString() {
    return "E | " + (this.isDone ? "1" : "0") + " | " + description + " | " + from + " | " + by;
  }
}

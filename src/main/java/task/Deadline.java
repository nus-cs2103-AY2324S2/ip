package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.DukeException;
import exception.InvalidDateException;
import exception.InvalidTaskFormatException;

public class Deadline extends Task {

  protected LocalDate by;

  public static Deadline createFromInput(String input) throws InvalidTaskFormatException, InvalidDateException {
    try {
      String[] parts = input.split("/by ");
      String description = parts[0].split("deadline ")[1];
      String by = parts[1];
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate parsedBy = LocalDate.parse(by, formatter);
      return new Deadline(description, parsedBy);
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new InvalidTaskFormatException("Invalid deadline format. Please use 'deadline description /by date'.");
    } catch (DateTimeParseException e) {
      throw new InvalidDateException("Invalid date format. Please use 'yyyy-MM-dd'.");
    }
  }

  public Deadline(String description, LocalDate by) {
    super(description);
    this.by = by;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
  }

  @Override
  public String toFileString() {
    return "D | " + (this.isDone ? "1" : "0") + " | " + description + " | " + by;
  }
}

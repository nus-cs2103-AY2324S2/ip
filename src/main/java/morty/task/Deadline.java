package morty.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class representing a Deadline task with a due date.
 * Inherits from the Task class and adds a deadline date.
 */
public class Deadline extends Task {
  private LocalDate by;

  /**
   * Constructor to create a new Deadline task.
   * Initializes the task with a title and a deadline date.
   *
   * @param title The title of the deadline task.
   * @param by The deadline date for the task.
   */
  public Deadline(String title, LocalDate by) {
    super(title);
    this.by = by;
  }

  /**
   * Constructor to create a new Deadline task.
   * Initializes the task with a title, a deadline date and a completion status.
   *
   * @param title The title of the deadline task.
   * @param by The deadline date for the task.
   * @param isDone The completion status of the task.
   */
  public Deadline(String title, LocalDate by, boolean isDone) {
    super(title, isDone);
    this.by = by;
  }

  /**
   * Provides a string representation of the Deadline task.
   * Format: "[D][\u2713 or \u2718] Title (by: deadline date)" for completed or
   * pending tasks respectively.
   *
   * @return The formatted string representation of the Deadline task.
   */
  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
  }

  /**
   * Provides a string representation of the Deadline task for saving to disk.
   * Format: "D | 0 or 1 | Title | deadline date" for pending or completed tasks
   * respectively.
   *
   * @return The formatted string representation of the Deadline task for saving
   *         to disk.
   */
  @Override
  public String serialize() {
    return "D | " + super.serialize() + " | " + by;
  }

  /**
   * Deserializes a Deadline task from a string representation.
   * Format: "D | 0 or 1 | Title | deadline date" for pending or completed tasks
   * respectively.
   *
   * @param text The string representation of the Deadline task.
   * @return The Deadline task represented by the string.
   */
  public static Deadline deserialize(String text) {
    String[] parts = text.split(" \\| ");
    return new Deadline(parts[2], LocalDate.parse(parts[3]), parts[1].equals("1"));
  }
}

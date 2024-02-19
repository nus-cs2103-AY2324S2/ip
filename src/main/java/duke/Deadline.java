package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline
 */
public class Deadline extends Task {

  protected LocalDateTime by;

  /**
   * Constructs a Deadline object with the specified description and deadline.
   *
   * @param description The description of the deadline task.
   * @param by The deadline for the task in this format: dd-MM-yyyy 24hours.
   */
  public Deadline(String description, LocalDateTime by) {
    super(description);
    this.by = by;
  }

  @Override
  public String toString() {
    String formattedDate = by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"));
    return "[D][" + getStatusIcon() + "] " + description + " (by: " + formattedDate + ")";
  }

  @Override
  public String toFileString() {
    return String.format("D | %d | %s | %s", isDone ? 1 : 0, description,
            by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")));
  }
}

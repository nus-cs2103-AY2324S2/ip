package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

  protected LocalDateTime by;

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

package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

  protected LocalDateTime from;
  protected LocalDateTime to;

  public Events(String description, LocalDateTime from, LocalDateTime to) {
    super(description);
    this.from = from;
    this.to = to;
  }

  @Override
  public String toString() {
    String formattedDateFrom = from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"));
    String formattedDateTo = to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"));
    return "[E][" + getStatusIcon() + "] " + description + " (from: " + formattedDateFrom + " to: " + formattedDateTo + ")";
  }

  @Override
  public String toFileString() {
    return String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description, from.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")), to.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")));
  }
}
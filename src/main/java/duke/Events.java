package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is an event occurring within a specific time and day frame
 */
public class Events extends Task {

  protected LocalDateTime from;
  protected LocalDateTime to;

  /**
   * Constructs an Events object with the specified description, start time, and end time.
   *
   * @param description The description of the event.
   * @param from The start time of the event in this format: dd-MM-yyyy 24hours.
   * @param to The end time of the event in this format: dd-MM-yyyy 24hours.
   */
  public Events(String description, LocalDateTime from, LocalDateTime to) {
    super(description);
    assert from != null : "Start date and time must not be null";
    assert to != null : "End date and time must not be null";
    this.from = from;
    this.to = to;
  }

  @Override
  public String toString() {
    assert description != null : "Description must not be null";
    assert from != null : "Start date and time must not be null";
    assert to != null : "End date and time must not be null";
    String formattedDateFrom = from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"));
    String formattedDateTo = to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"));
    return "[E][" + getStatusIcon() + "] " + description
            + " (from: " + formattedDateFrom + " to: " + formattedDateTo + ")";
  }

  @Override
  public String toFileString() {
    assert description != null : "Description must not be null";
    assert from != null : "Start date and time must not be null";
    assert to != null : "End date and time must not be null";
    return String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description,
            from.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")),
            to.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")));
  }
}